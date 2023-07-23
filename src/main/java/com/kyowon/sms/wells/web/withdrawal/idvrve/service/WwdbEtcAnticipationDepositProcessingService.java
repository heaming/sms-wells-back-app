package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import com.kyowon.sms.common.web.withdrawal.idvrve.converter.ZwdbEtcAnticipationDpProcsConvert;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCorporationDepositDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbEtcAnticipationDpProcsDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbEtcAnticipationDpProcsDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbCorporationDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwwdbEtcDepositMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalDepositCprDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaEtcAnticipationAmtDvo;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaBusinessAnticipationAmtService;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaEtcAnticipationAmtService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbEtcAnticipationDepositProcessingService {
    private final ZwdbEtcAnticipationDpProcsConvert convert;

    private final ZwwdbEtcDepositMapper etcDepositMapper;

    private final ZwdbCorporationDepositMapper zwdbCorporationDepositMapper;

    private final ZwdzWithdrawalService zwdzWithdrawalService;

    private final WdcaBusinessAnticipationAmtService wdcaBusinessAnticipationAmtService;

    private final WdcaEtcAnticipationAmtService edcaEtcAnticipationAmtService;

    public static String rveCd = "70440";

    //경로 16

    public int saveDepositProcs(ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingReq dto) throws Exception {
        int processCount = 0;

        ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingMainReq mainReq = dto.saveDepositProcessingMainReq();
        List<ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq> subReqs = dto.saveDepositProcessingSubReq();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        String depositPk = etcDepositMapper.selectEtcDepositPk();

        String sysDate = DateUtil.getNowString();
        String sysDateYmd = sysDate.substring(0, 8);

        ZwdbEtcAnticipationDpProcsDvo mainDvo = convert.mapSaveEtcAnticipationDpProcsDvo(mainReq);
        mainDvo.setDpCprcnfNo(depositPk);
        mainDvo.setDpCprcnfDtm(sysDateYmd);

        //통합입금기본 조회
        ZwdbCorporationDepositDto.SearchIntegrationDepositRes integrationRes = zwdbCorporationDepositMapper
            .selectIntegrationDepositInfo(mainDvo.getItgDpNo());

        //영업선수금 DVO
        List<WdcaBusinessAnticipationAmtDvo> wdcaBusinessAnticipationAmtDvos = new ArrayList<WdcaBusinessAnticipationAmtDvo>();

        //기타선수금 DVO
        List<WdcaEtcAnticipationAmtDvo> wdcaEtcAnticipationAmtDvos = new ArrayList<WdcaEtcAnticipationAmtDvo>();

        for (ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq list : subReqs) {
            //계약 번호 셋팅
            ZwdbCorporationDepositDto.SearchDepositContractReq contractRes = new ZwdbCorporationDepositDto.SearchDepositContractReq(
                list.cntrNo(), list.cntrSn()
            );

            //계약조회
            ZwdbCorporationDepositDto.SearchDepositContractRes searchDepositContractRes = zwdbCorporationDepositMapper
                .selectDepositContracts(contractRes);

            if (ObjectUtils.isEmpty(searchDepositContractRes)) {
                throw new BizException("존재하지 않는 계약번호 입니다.");
            }

            //수납요청기본 데이터 생성
            ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo = saveAskBaseDvo(session, sysDateYmd, list);

            //수납요청상세 데이터 생성
            processCount = saveAskDtlDvo(processCount, session, integrationRes, list, zwdzWithdrawalReceiveAskDvo);

            //수납기본 데이터 생성
            ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo = saveRveBase(
                session, sysDateYmd, list, zwdzWithdrawalReceiveAskDvo
            );

            //입금대사기본 데이터 생성
            String depositComparisonPk = saveDeposit(
                mainReq, session, sysDate, sysDateYmd, mainDvo, integrationRes, list
            );

            //수납상세 데이터 생성
            processCount = saveRveDtl(
                processCount, mainReq, session, sysDateYmd, integrationRes, list, zwdzWithdrawalReceiveDvo,
                depositComparisonPk, zwdzWithdrawalReceiveAskDvo.getReceiveAskNumber()
            );

            //영업선수금
            WdcaBusinessAnticipationAmtDvo edcaBusinessAnticipationAmtDvo = new WdcaBusinessAnticipationAmtDvo();

            //만약 당일입금이 아닌경우 기타선수금 데이터 생성
            if ("2".equals(mainReq.processingDivide())) {
                String etcAtamNo = etcDepositMapper.selectMaxEtcAnticipationNo(mainReq.itgDpNo());

                //기타선수금 데이터 생성
                WdcaEtcAnticipationAmtDvo etcAnticipationDvo = new WdcaEtcAnticipationAmtDvo();
                etcAnticipationDvo.setEtcAtamNo(etcAtamNo); //기타선수금번호
                etcAnticipationDvo.setEtcAtamOcDt(integrationRes.dpDtm().substring(0, 8)); //기타선수금발생일자
                etcAnticipationDvo.setItgDpNo(integrationRes.itgDpNo()); //통합입금번호
                etcAnticipationDvo.setEtcAtamTpCd("1"); //기타선수금유형코드 일단 미대사
                etcAnticipationDvo.setEtcAtamProcsCd("0"); //기타선수금처리코드
                etcAnticipationDvo.setEtcAtamPrcsdt(sysDateYmd); //기타선수금처리일자
                etcAnticipationDvo.setEtcAtamProcsAmt(Integer.parseInt(list.dpCprcnfAmt())); //기타선수금처리금액
                etcAnticipationDvo.setRveNo(zwdzWithdrawalReceiveDvo.getRveNo()); //수납번호
                etcAnticipationDvo.setRveSn(Integer.parseInt(zwdzWithdrawalReceiveDvo.getRveSn())); //수납일련번호
                //                etcAnticipationDvo.setRfndRcpNo(); //환불접수번호
                etcAnticipationDvo.setRveDt(zwdzWithdrawalReceiveDvo.getRveDt()); //수납일자
                //                etcAnticipationDvo.setBilTn(); //청구회차
                etcAnticipationDvo.setProcsDvCd(zwdzWithdrawalReceiveDvo.getProcsDvCd()); //처리구분코드
                etcAnticipationDvo.setDpDvCd(zwdzWithdrawalReceiveDvo.getDpDvCd()); //입금구분코드
                etcAnticipationDvo.setSellTpCd(searchDepositContractRes.sellTpCd()); //판매유형코드
                etcAnticipationDvo.setSellTpDtlCd(searchDepositContractRes.sellTpDtlCd()); //판매유형상세코드
                etcAnticipationDvo.setPdCd(searchDepositContractRes.basePdCd()); //상품코드
                etcAnticipationDvo.setPdHclsfId(searchDepositContractRes.pdHclsfId()); //상품대분류ID
                etcAnticipationDvo.setPdMclsfId(searchDepositContractRes.pdMclsfId()); //상품중분류ID
                etcAnticipationDvo.setPdLclsfId(searchDepositContractRes.pdLclsfId()); //상품소분류ID
                etcAnticipationDvo.setDpMesCd(zwdzWithdrawalReceiveDvo.getDpMesCd()); //입금수단코드
                etcAnticipationDvo.setDpTpCd(zwdzWithdrawalReceiveDvo.getDpTpCd()); //입금유형코드
                etcAnticipationDvo.setCdcoCd(integrationRes.crdcdFnitCd()); //카드사코드
                etcAnticipationDvo.setCardAprno(integrationRes.crdcdAprno()); //카드승인번호
                etcAnticipationDvo.setCrcdnoEncr(integrationRes.crcdnoEncr()); //신용카드번호암호화
                etcAnticipationDvo.setBnkCd(integrationRes.fnitCd()); //은행코드
                etcAnticipationDvo.setDprNm(integrationRes.dprNm()); //입금자명
                etcAnticipationDvo.setAcnoEncr(integrationRes.acnoEncr()); //계좌번호암호화
                etcAnticipationDvo.setVncoDvCd(integrationRes.vncoDvCd()); //VAN사구분코드
                etcAnticipationDvo.setCntrNo(list.cntrNo()); //계약번호
                etcAnticipationDvo.setCntrSn(Integer.parseInt(list.cntrSn())); //계약일련번호
                etcAnticipationDvo.setKwGrpCoCd(session.getCompanyCode()); //교원그룹회사코드
                etcAnticipationDvo.setRveCd(rveCd); //수납코드
                etcAnticipationDvo.setRveDvCd(zwdzWithdrawalReceiveDvo.getRveDvCd()); //수납구분코드
                etcAnticipationDvo.setRvePhCd(zwdzWithdrawalReceiveDvo.getRvePhCd()); //수납경로코드
                //                                            etcAnticipationDvo.setRveplcDvCd(); //수납처구분코드
                etcAnticipationDvo.setOgTpCd(session.getOgTpCd()); //조직유형코드
                etcAnticipationDvo.setIchrPrtnrNo(session.getEmployeeIDNumber()); //담당파트너번호
                etcAnticipationDvo.setCstNo(list.cstNo()); //고객번호

                edcaBusinessAnticipationAmtDvo.setEtcAtamNo(etcAtamNo); //   기타선수금번호

                wdcaEtcAnticipationAmtDvos.add(etcAnticipationDvo);
            } else {
                edcaBusinessAnticipationAmtDvo.setEtcAtamNo(""); //   기타선수금번호
            }

            // 영업선수금 데이터 생성

            edcaBusinessAnticipationAmtDvo.setInputGubun("1"); //입력구분-입출금
            edcaBusinessAnticipationAmtDvo.setRveNo(zwdzWithdrawalReceiveDvo.getRveNo()); //수납번호
            edcaBusinessAnticipationAmtDvo.setRveSn(Integer.parseInt(zwdzWithdrawalReceiveDvo.getRveSn())); //수납일련번호
            edcaBusinessAnticipationAmtDvo.setDpClDt(sysDateYmd); //입금마감일자
            edcaBusinessAnticipationAmtDvo.setCntrNo(zwdzWithdrawalReceiveDvo.getCntrNo()); //계약번호
            edcaBusinessAnticipationAmtDvo.setCntrSn(Integer.parseInt(zwdzWithdrawalReceiveDvo.getCntrSn())); //계약일련번호
            edcaBusinessAnticipationAmtDvo.setKwGrpCoCd(zwdzWithdrawalReceiveDvo.getKwGrpCoCd()); //교원그룹회사코드
            edcaBusinessAnticipationAmtDvo.setRveCd(zwdzWithdrawalReceiveDvo.getRveCd()); //수납코드
            edcaBusinessAnticipationAmtDvo.setOgTpCd(zwdzWithdrawalReceiveDvo.getOgTpCd()); //조직유형코드
            edcaBusinessAnticipationAmtDvo.setIchrPrtnrNo(zwdzWithdrawalReceiveDvo.getPrtnrNo()); //담당파트너번호
            edcaBusinessAnticipationAmtDvo.setRveAmt(Integer.parseInt(zwdzWithdrawalReceiveDvo.getRveAmt())); //수납금액
            edcaBusinessAnticipationAmtDvo.setCstNo(zwdzWithdrawalReceiveDvo.getCstNo()); //고객번호
            edcaBusinessAnticipationAmtDvo.setBnkCd(integrationRes.fnitCd()); //은행코드
            edcaBusinessAnticipationAmtDvo.setDpCprcnfNo(depositComparisonPk); //입금대사번호
            edcaBusinessAnticipationAmtDvo.setPdCd(zwdzWithdrawalReceiveDvo.getPdCd()); //상품코드
            edcaBusinessAnticipationAmtDvo.setPdHclsfId(searchDepositContractRes.pdHclsfId()); //상품대분류ID
            edcaBusinessAnticipationAmtDvo.setPdMclsfId(searchDepositContractRes.pdMclsfId()); //상품중분류ID
            edcaBusinessAnticipationAmtDvo.setPdLclsfId(searchDepositContractRes.pdLclsfId()); //상품소분류ID
            edcaBusinessAnticipationAmtDvo.setRveDt(zwdzWithdrawalReceiveDvo.getRveDt()); //수납일자
            edcaBusinessAnticipationAmtDvo.setPerfDt(zwdzWithdrawalReceiveDvo.getPerfDt()); //실적일자
            //            zwdbBusinessAnticipationDvo.setBilTn(); //청구회차
            edcaBusinessAnticipationAmtDvo.setProcsDvCd(zwdzWithdrawalReceiveDvo.getProcsDvCd()); //처리구분코드
            edcaBusinessAnticipationAmtDvo.setDpMesCd(zwdzWithdrawalReceiveDvo.getDpMesCd()); //입금수단코드
            edcaBusinessAnticipationAmtDvo.setDpTpCd(zwdzWithdrawalReceiveDvo.getDpTpCd()); //입금유형코드
            edcaBusinessAnticipationAmtDvo.setRveDvCd(zwdzWithdrawalReceiveDvo.getRveDvCd()); //수납구분코드
            edcaBusinessAnticipationAmtDvo.setRvePhCd(zwdzWithdrawalReceiveDvo.getRvePhCd()); //수납경로코드
            //            zwdbBusinessAnticipationDvo.setRveplcDvCd(zwdzWithdrawalReceiveDvo.); //수납처구분코드
            edcaBusinessAnticipationAmtDvo.setCdcoCd(integrationRes.crdcdFnitCd()); //카드사코드
            edcaBusinessAnticipationAmtDvo.setDpDvCd(zwdzWithdrawalReceiveDvo.getDpDvCd()); //입금구분코드
            edcaBusinessAnticipationAmtDvo.setSellTpCd(searchDepositContractRes.sellTpCd()); //판매유형코드
            edcaBusinessAnticipationAmtDvo.setSellTpDtlCd(searchDepositContractRes.sellTpDtlCd()); //판매유형상세코드
            edcaBusinessAnticipationAmtDvo.setCardAprno(integrationRes.crdcdAprno()); //카드승인번호
            edcaBusinessAnticipationAmtDvo.setCrcdnoEncr(integrationRes.crcdnoEncr()); //신용카드번호암호화
            edcaBusinessAnticipationAmtDvo.setDprNm(integrationRes.dprNm()); //입금자명
            edcaBusinessAnticipationAmtDvo.setAcnoEncr(integrationRes.acnoEncr()); //계좌번호암호화
            edcaBusinessAnticipationAmtDvo.setVncoDvCd(integrationRes.vncoDvCd()); //VAN사구분코드

            wdcaBusinessAnticipationAmtDvos.add(edcaBusinessAnticipationAmtDvo);

            mainDvo.setDpCprcnfAmt(list.dpCprcnfAmt()); //대사금액
            mainDvo.setRveAkNo(zwdzWithdrawalReceiveAskDvo.getReceiveAskNumber()); //요청번호
            mainDvo.setItgDpNo(mainDvo.getItgDpNo()); //통합입금번호
            mainDvo.setRveCd(rveCd); //수납코드

            //통합입금기본 데이터 업데이트
            processCount += etcDepositMapper.updateIntegrationDeposit(mainDvo);

            // 이력 넣어줘야함
        }

        //기타선수금 데이터 생성
        //        processCount += edcaEtcAnticipationAmtService.createEtcAnticipationAmt(wdcaEtcAnticipationAmtDvos);

        //영업선수금 데이터 생성
        processCount += wdcaBusinessAnticipationAmtService
            .createBusinessAnticipationAmt(wdcaBusinessAnticipationAmtDvos);

        return processCount;
    }

    private int saveRveDtl(
        int processCount, ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingMainReq mainReq, UserSessionDvo session,
        String sysDateYmd,
        ZwdbCorporationDepositDto.SearchIntegrationDepositRes integrationRes,
        ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq list,
        ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo, String depositComparisonPk, String receiveAskNumber
    ) {
        /*수납상세 인설트 데이터 입력*/
        zwdzWithdrawalReceiveDvo.setKwGrpCoCd(session.getCompanyCode());//            KW_GRP_CO_CD	교원그룹회사코드
        //            RVE_NO	수납번호
        zwdzWithdrawalReceiveDvo.setRveSn(Integer.toString(1));//                        RVE_SN	수납일련번호
        zwdzWithdrawalReceiveDvo.setDpDvCd(integrationRes.dpDvCd());//            DP_DV_CD	입금구분코드(1 입금)
        zwdzWithdrawalReceiveDvo.setDpMesCd(integrationRes.dpMesCd());//            DP_MES_CD	입금수단코드(01 현금)
        zwdzWithdrawalReceiveDvo.setDpTpCd(integrationRes.dpTpCd());//            DP_TP_CD	입금유형코드( )
        zwdzWithdrawalReceiveDvo.setRveDvCd("98");//            RVE_DV_CD	수납구분코드
        //            RVE_BIZ_DV_CD	수납업무구분코드
        zwdzWithdrawalReceiveDvo.setRveCd(rveCd);//            RVE_CD	수납코드
        zwdzWithdrawalReceiveDvo.setOgTpCd(session.getOgTpCd());//            OG_TP_CD	조직유형코드
        zwdzWithdrawalReceiveDvo.setPrtnrNo(session.getEmployeeIDNumber());//            PRTNR_NO	파트너번호
        zwdzWithdrawalReceiveDvo.setProcsDvCd(mainReq.processingDivide());//            PROCS_DV_CD	처리구분코드()
        zwdzWithdrawalReceiveDvo.setDpDt(integrationRes.dpDtm());//            DP_DT	입금일자
        //            zwdzWithdrawalReceiveDvo.setDpAmt(mainReq.);//            DP_AMT	입금금액
        //            zwdzWithdrawalReceiveDvo.setAlncFee();//            ALNC_FEE	제휴수수료
        zwdzWithdrawalReceiveDvo.setRveDt(sysDateYmd);//            RVE_DT	수납일자
        zwdzWithdrawalReceiveDvo.setRveAmt(list.dpCprcnfAmt());//            RVE_AMT	수납금액
        zwdzWithdrawalReceiveDvo.setRveProcsYn("Y");//            RVE_PROCS_YN	수납처리여부
        zwdzWithdrawalReceiveDvo.setPerfDt(sysDateYmd);//            PERF_DT	실적일자
        zwdzWithdrawalReceiveDvo.setRveAkNo(receiveAskNumber);//            RVE_AK_NO	수납요청번호
        zwdzWithdrawalReceiveDvo.setRveAkSn(Integer.toString(1));//            RVE_AK_SN	수납요청일련번호
        zwdzWithdrawalReceiveDvo.setItgDpNo(mainReq.itgDpNo());//            ITG_DP_NO	통합입금번호
        zwdzWithdrawalReceiveDvo.setDpCprcnfNo(depositComparisonPk);//            DP_CPRCNF_NO	입금대사번호
        //            MLG_USE_NO	마일리지사용번호
        zwdzWithdrawalReceiveDvo.setCntrNo(list.cntrNo());//            CNTR_NO	계약번호
        zwdzWithdrawalReceiveDvo.setCntrSn(list.cntrSn());//            CNTR_SN	계약일련번호
        zwdzWithdrawalReceiveDvo.setPdCd(list.pdCd());//            PD_CD	상품코드
        //            RVE_OJ_DRM_NO1	수납대상식별번호1
        //            RVE_OJ_DRM_NO2	수납대상식별번호2
        //            CHUM_REF_DRM_NO	채움몰참조식별번호
        zwdzWithdrawalReceiveDvo.setIncmdcYn(list.pdPrpVal01());//            INCMDC_YN	소득공제여부
        zwdzWithdrawalReceiveDvo.setRveCoCd(session.getCompanyCode());//            RVE_CO_CD	수납회사코드

        //수납상세 데이터 생성
        processCount += zwdzWithdrawalService.createReceiveDetail(zwdzWithdrawalReceiveDvo);
        return processCount;
    }

    private String saveDeposit(
        ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingMainReq mainReq, UserSessionDvo session, String sysDate,
        String sysDateYmd,
        ZwdbEtcAnticipationDpProcsDvo mainDvo, ZwdbCorporationDepositDto.SearchIntegrationDepositRes integrationRes,
        ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq list
    ) {
        /*입금대사 인설트 데이터 생성*/
        ZwdzWithdrawalDepositCprDvo depositCprDvo = new ZwdzWithdrawalDepositCprDvo();

        //            depositCprDvo.setDpCprcnfNo(); /*입금대사번호*/                                         // DP_CPRCNF_NO	입금대사번호
        depositCprDvo.setKwGrpCoCd(session.getCompanyCode()); /*교원그룹회사코드*/ // KW_GRP_CO_CD	교원그룹회사코드
        depositCprDvo.setRveCoCd(session.getCompanyCode()); /*수납회사코드*/ // RVE_CO_CD	수납회사코드
        depositCprDvo.setRveCd(rveCd); /*수납코드*/ // RVE_CD	수납코드
        depositCprDvo.setProcsDvCd(mainReq.processingDivide()); /*처리구분코드*/ // PROCS_DV_CD	처리구분코드()
        depositCprDvo.setDpDvCd(integrationRes.dpDvCd()); /*입금구분코드*/ // DP_DV_CD	입금구분코드()
        depositCprDvo.setDpMesCd(integrationRes.dpMesCd()); /*입금수단코드*/ // DP_MES_CD	입금수단코드()
        depositCprDvo.setDpTpCd(integrationRes.dpTpCd()); /*입금유형코드*/ // DP_TP_CD	입금유형코드()
        depositCprDvo.setRveDvCd("98"); /*수납구분코드*/ // RVE_DV_CD	수납구분코드 (98 기타선수)
        depositCprDvo.setIaDvCd("01"); /*입금항목구분코드*/ // IA_DV_CD	입금항목구분0코드 ??
        //            depositCprDvo.setDpCprcnfBizDvCd("03"); /*입금대사업무구분코드*/ // DP_CPRCNF_BIZ_DV_CD	입금대사업무구분코드
        depositCprDvo.setDpCprcnfBizCd("98"); /*입금대사업무코드*/ // DP_CPRCNF_BIZ_CD	입금대사업무코드(기타입금대사)
        //            depositCprDvo.setDpCprcnfPdClsfCd(); /*입금대사상품분류코드*/                               // DP_CPRCNF_PD_CLSF_CD	입금대사상품분류코드
        //            depositCprDvo.setDpCprcnfPdClsfId(); /*입금대사상품분류id*/                               // DP_CPRCNF_PD_CLSF_ID	입금대사상품분류ID
        //            depositCprDvo.setDpCprcnfSellTpCd(); /*입금대사판매유형코드*/                               // DP_CPRCNF_SELL_TP_CD	입금대사판매유형코드
        depositCprDvo.setDpCprcnfDtm(sysDate); /*입금대사일시*/ // DP_CPRCNF_DTM	입금대사일시 14
        depositCprDvo.setDpCprcnfPerfDt(sysDateYmd); /*입금대사실적일자*/ // DP_CPRCNF_PERF_DT	입금대사실적일자 9
        depositCprDvo.setDpCprcnfCanYn("N"); /*입금대사취소여부*/ // DP_CPRCNF_CAN_YN	입금대사취소여부
        //            depositCprDvo.setDpCprcnfCanDtm(); /*입금대사취소일시*/                                   // DP_CPRCNF_CAN_DTM	입금대사취소일시
        //            depositCprDvo.setDpCprcnfCanRsonCd(); /*입금대사취소사유코드*/                              // DP_CPRCNF_CAN_RSON_CD	입금대사취소사유코드
        //            depositCprDvo.setOrdpCprcnfNo(); /*원입금대사번호*/                                      // ORDP_CPRCNF_NO	원입금대사번호
        depositCprDvo.setDpCprcnfCnfmYn("Y"); /*입금대사확정여부*/ // DP_CPRCNF_CNFM_YN	입금대사확정여부
        depositCprDvo.setDpCprcnfCnfmDtm(sysDate); /*입금대사확정일시*/ // DP_CPRCNF_CNFM_DTM	입금대사확정일시 14
        depositCprDvo.setDpCprcnfAmt(list.dpCprcnfAmt()); /*입금대사금액*/ // DP_CPRCNF_AMT	입금대사금액 ??
        //            depositCprDvo.setDpCprcnfEtcFeeAmt(); /*입금대사기타수수료금액*/ // DP_CPRCNF_ETC_FEE_AMT	입금대사기타수수료금액 ??
        depositCprDvo.setDpCprcnfProcsAmt(list.dpCprcnfAmt()); /*입금대사처리금액*/ // DP_CPRCNF_PROCS_AMT	입금대사처리금액 ??
        //            depositCprDvo.setDpCprcnfBlam(); /*입금대사잔액*/ // DP_CPRCNF_BLAM	입금대사잔액 ??
        depositCprDvo.setDpCprcnfDstApyYn("N"); /*입금대사배분적용여부*/ // DP_CPRCNF_DST_APY_YN	입금대사배분적용여부 ??
        depositCprDvo.setItgDpNo(mainDvo.getItgDpNo()); /*통합입금번호*/ // ITG_DP_NO	통합입금번호
        //            depositCprDvo.setItgDpChSn(); /*통합입금변경일련번호*/                                      // ITG_DP_CH_SN	통합입금변경일련번호
        depositCprDvo.setCntrNo(list.cntrNo()); /*계약번호*/ // CNTR_NO	계약번호
        depositCprDvo.setCntrSn(list.cntrSn()); /*계약일련번호*/ // CNTR_SN	계약일련번호
        depositCprDvo.setPdCd(list.cntrSn()); /*상품코드*/ // PD_CD	상품코드
        //            depositCprDvo.setRveOjDrmNo1(); /*수납대상식별번호1*/                                     // RVE_OJ_DRM_NO1	수납대상식별번호1
        //            depositCprDvo.setRveOjDrmNo2(); /*수납대상식별번호2*/                                     // RVE_OJ_DRM_NO2	수납대상식별번호2
        //            depositCprDvo.setChumRefDrmNo(); /*채움몰참조식별번호*/                                    // CHUM_REF_DRM_NO	채움몰참조식별번호
        //            depositCprDvo.setOrdDpCd(dvo.getOrdDpCd()); /*주문입금코드*/
        //            depositCprDvo.setSapMstDvCd(dvo.getSapMstDvCd()); /*sap마스터구분코드*/
        //            depositCprDvo.setSapMstCd(dvo.getSapMstCd()); /*sap마스터코드*/
        //            depositCprDvo.setSapCoaCd(dvo.getSapCoaCd()); /*sap계정과목코드*/
        //            depositCprDvo.setSapBzCapamCd(dvo.getSapBzCapamCd()); /*sap사업자금코드*/
        //            depositCprDvo.setSapCscnCd(dvo.getSapCscnCd()); /*sap코스트센터코드*/
        //            depositCprDvo.setSapFeeAccCd(dvo.getSapFeeAccCd()); /*sap수수료계정코드*/
        //            //         depositCprDvo.setSapSpcDvCd(dvo.getSapSpcDvCd); /*sap특별구분코드*/
        //            depositCprDvo.setSapRefCd(dvo.getSapRefCd()); /*sap참조코드*/
        //            depositCprDvo.setSapBzTeryCd(dvo.getSapBzTeryCd()); /*sap사업영역코드*/
        //            depositCprDvo.setSapSmryCn(dvo.getSapSmryCn()); /*sap적요내용*/
        depositCprDvo.setIncmdcYn(list.pdPrpVal01()); /*소득공제여부*/ // INCMDC_YN	소득공제여부
        // RVE_BIZ_DV_CD	수납업무구분코드

        //입금대사 데이터 생성(리턴 입금대사번호)
        String depositComparisonPk = zwdzWithdrawalService.createDepositComparison(depositCprDvo);
        return depositComparisonPk;
    }

    private ZwdzWithdrawalReceiveDvo saveRveBase(
        UserSessionDvo session, String sysDateYmd, ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq list,
        ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo
    ) {
        /*수납기본 데이터 입력*/
        ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo = new ZwdzWithdrawalReceiveDvo();
        zwdzWithdrawalReceiveDvo.setKwGrpCoCd(session.getCompanyCode()); //        KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveDvo.setCstNo(list.cstNo()); //        CST_NO	고객번호
        zwdzWithdrawalReceiveDvo.setRveAkNo(zwdzWithdrawalReceiveAskDvo.getReceiveAskNumber()); //        RVE_AK_NO	수납요청번호
        zwdzWithdrawalReceiveDvo.setRvePhCd("16"); //        RVE_PH_CD	수납경로코드
        zwdzWithdrawalReceiveDvo.setRveDt(sysDateYmd); //        RVE_DT	수납일자
        zwdzWithdrawalReceiveDvo.setRveAmt(list.dpCprcnfAmt()); //        RVE_AMT	수납금액

        //수납기본 데이터 생성 (수납번호 리턴)
        String rveNo = zwdzWithdrawalService.createReceive(zwdzWithdrawalReceiveDvo);
        zwdzWithdrawalReceiveDvo.setRveNo(rveNo);
        return zwdzWithdrawalReceiveDvo;
    }

    private int saveAskDtlDvo(
        int processCount, UserSessionDvo session, ZwdbCorporationDepositDto.SearchIntegrationDepositRes integrationRes,
        ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq list,
        ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo
    ) {
        /*수납요청상세 데이터 입력*/
        zwdzWithdrawalReceiveAskDvo.setDepositDivideCode(integrationRes.dpDvCd());//입금구분코드
        zwdzWithdrawalReceiveAskDvo.setDepositMeansCode(integrationRes.dpMesCd());//입금수단코드
        zwdzWithdrawalReceiveAskDvo.setDepositTypeCode(integrationRes.dpTpCd());//입금유형코드
        zwdzWithdrawalReceiveAskDvo.setReceiveDivideCode("98");//수납구분코드 (기타선수) //일단 기타선수로 넣고 추후에 변경
        //                        zwdzWithdrawalReceiveAskDvo.set//수납업무구분코드
        zwdzWithdrawalReceiveAskDvo.setOrganizationId(session.getOgId());//조직ID
        zwdzWithdrawalReceiveAskDvo.setOrganizationTypeCode(session.getOgTpCd());//조직유형코드
        zwdzWithdrawalReceiveAskDvo.setPartnerNumber(session.getEmployeeIDNumber());//파트너번호
        zwdzWithdrawalReceiveAskDvo.setContractNumber(list.cntrNo());//계약번호
        zwdzWithdrawalReceiveAskDvo.setContractSerialNumber(list.cntrSn()); //계약일련번호
        zwdzWithdrawalReceiveAskDvo.setProductCode(list.pdCd());//상품코드
        zwdzWithdrawalReceiveAskDvo.setReceiveAskAmount(list.dpCprcnfAmt());//수납요청금액
        zwdzWithdrawalReceiveAskDvo.setReceiveAmount(list.dpCprcnfAmt());//수납금액
        zwdzWithdrawalReceiveAskDvo.setReceiveStatusCode("02"); //수납상태코드 수납완료(02)
        zwdzWithdrawalReceiveAskDvo.setFinancialInstitutionCd(integrationRes.fnitCd());//금융기관코드
        zwdzWithdrawalReceiveAskDvo.setAccountOwnerName(integrationRes.dprNm()); //계좌주명
        zwdzWithdrawalReceiveAskDvo.setAccountNumberEncr(integrationRes.acnoEncr()); //계좌번호암호화
        zwdzWithdrawalReceiveAskDvo.setCrdcdBryyMmdd(integrationRes.crdcdBryyMmdd()); //신용카드생년월일
        zwdzWithdrawalReceiveAskDvo.setCrdcdBzrno(integrationRes.crdcdBzrno());//신용카드사업자등록번호
        zwdzWithdrawalReceiveAskDvo.setCrdcdCopnDvCd(integrationRes.crdcdCopnDvCd()); //신용카드법인격구분코드
        zwdzWithdrawalReceiveAskDvo.setCreditCardNumberEncr(integrationRes.crcdnoEncr()); //신용카드번호암호화
        zwdzWithdrawalReceiveAskDvo.setCreditCardExpireDate(integrationRes.crdcdExpdtYm()); //신용카드유효기간년월
        zwdzWithdrawalReceiveAskDvo.setCreditCardIstmMcn(integrationRes.crdcdIstmMcn()); //신용카드할부개월수
        zwdzWithdrawalReceiveAskDvo.setIncmdcYn(list.pdPrpVal01()); //소득공제여부

        // 수납요청상세 데이터 생성
        processCount += zwdzWithdrawalService.createReceiveAskDetail(zwdzWithdrawalReceiveAskDvo);

        //수납요청상세 이력 생성
        processCount += zwdzWithdrawalService.createReceiveAskDetailHistory(zwdzWithdrawalReceiveAskDvo);
        return processCount;
    }

    private ZwdzWithdrawalReceiveAskDvo saveAskBaseDvo(
        UserSessionDvo session, String sysDateYmd, ZwdbEtcAnticipationDpProcsDto.SaveDepositProcessingSubReq list
    ) {
        /*수납요청기본 데이터 입력*/
        ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
        zwdzWithdrawalReceiveAskDvo.setKyowonGroupCompanyCd(session.getCompanyCode()); //KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveAskDvo.setCustomNumber(list.cstNo()); //CST_NO	고객번호
        zwdzWithdrawalReceiveAskDvo.setRveAkMthdCd("01"); //RVE_AK_MTHD_CD	수납요청방식코드 대면(01)
        zwdzWithdrawalReceiveAskDvo.setRveAkPhCd("16"); //RVE_AK_PH_CD	수납요청경로코드 영업부(05)
        zwdzWithdrawalReceiveAskDvo.setRvePrtnrOgTpCd(session.getOgTpCd()); //RVE_AK_PRTNR_OG_TP_CD	수납요청파트너조직유형코드
        zwdzWithdrawalReceiveAskDvo.setRvePrtnrNo(session.getEmployeeIDNumber()); //RVE_AK_PRTNR_NO	수납요청파트너번호
        zwdzWithdrawalReceiveAskDvo.setReceiveAskAmount(list.dpCprcnfAmt()); //RVE_AK_AMT	수납요청금액
        zwdzWithdrawalReceiveAskDvo.setReceiveAskDate(sysDateYmd); //RVE_RQDT	수납요청일자
        zwdzWithdrawalReceiveAskDvo.setReceiveAskStatusCode("03"); //RVE_AK_STAT_CD	수납요청상태코드
        zwdzWithdrawalReceiveAskDvo.setReceiveCompanyCode(session.getCompanyCode()); //RVE_CO_CD	수납회사코드

        /*수납요청기본 데이터 생성*/
        String receiveAskNumber = zwdzWithdrawalService.createReceiveAskBase(zwdzWithdrawalReceiveAskDvo);

        zwdzWithdrawalReceiveAskDvo.setReceiveAskNumber(receiveAskNumber);

        return zwdzWithdrawalReceiveAskDvo;
    }
}
