package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import com.kyowon.sms.common.web.closing.payment.service.ZdcaNumberingSlpnoService;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCorporationDepositDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbIntegrationDepositDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbWithdrawalReceiveAskReqDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwwdbEtcDepositProcessingDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbCorporationDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwdbIntegrationDepositMapper;
import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwwdbEtcDepositMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalDepositCprDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositContractDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositSlipProcessingDvo;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbBillDepositMgtConvert;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveMainDtlReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtSubDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbBillDepositMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbBillDepositMgtService {

    private final WwdbBillDepositMgtMapper mapper;

    private final WwdbBillDepositMgtConvert convert;

    private final ZwdbIntegrationDepositMapper zwdbIntegrationDepositMapper;

    private final ZwdzWithdrawalService zwdzWithdrawalService;

    private final ZwdbCorporationDepositMapper zwdbCorporationDepositMapper;

    private final ZwwdbEtcDepositMapper etcDepositMapper;

    private final ZdcaNumberingSlpnoService slpnoService;

    @Transactional
    public PagingResult<SearchRes> getRegistrationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationPages(dto, pageInfo);
    }

    @Transactional
    public List<SearchRes> getRegistrationExcels(SearchReq dto) {
        return mapper.selectRegistrationPages(dto);
    }

    @Transactional
    public PagingResult<SearchDetailRes> getRegistrationElectronicPages(SearchDetailReq dto, PageInfo pageInfo) {
        return mapper.selectRegistrationElectronics(dto, pageInfo);
    }

    @Transactional
    public List<SearchDetailRes> getRegistrationElectronicExcels(SearchDetailReq dto) {
        return mapper.selectRegistrationElectronics(dto);
    }

    @Transactional
    public WwdbBillDepositMgtDto.SearchItgNoRes getRegistrationPk() {
        return mapper.selectRegistrationPk();
    }

    @Transactional
    public int saveRegistrationElectronics(SaveReq dtos) throws Exception {

        int processCount = 0;

        SaveMainReq mainDto = dtos.saveMainReq();
        List<SaveMainDtlReq> subDto = dtos.SaveMainDtlReq();

        log.info("==============service");
        log.info(mainDto.toString());
        log.info("==============service");

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYmd = DateUtil.getNowDayString();

        WwdbBillDepositMgtDvo dvo = convert.mapSaveWwdbRegistrationListDvo(mainDto);

        switch (mainDto.state()) {
            case CommConst.ROW_STATE_CREATED -> {
                processCount += mapper.insertRegistrationMainElectronics(dvo);
            }
            case CommConst.ROW_STATE_UPDATED -> {
                processCount += mapper.updateRegistrationMainElectronics(dvo);
            }
            default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");

        }

        int sumAmt = 0;

        if (subDto.size() > 0) {
            if (CommConst.ROW_STATE_CREATED.equals(subDto.get(0).rowState()))
                processCount += mapper.deleteRegistrationSubElectronics(dvo);

            for (SaveMainDtlReq dto : subDto) {
                WwdbBillDepositMgtSubDvo subDvo = convert.mapSaveWwdbRegistrationSubListDvo(dto);
                sumAmt += Integer.parseInt(dto.billDpAmt());
                switch (dto.rowState()) {
                    case CommConst.ROW_STATE_CREATED -> {
                        processCount += mapper.insertRegistrationSubElectronics(subDvo);
                    }
                    case CommConst.ROW_STATE_UPDATED -> {
                        processCount += mapper.updateRegistrationSubElectronics(subDvo);
                    }
                    default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
                }

            }
        }

        //통합입금기본 데이터 생성
        ZwdbIntegrationDepositDvo depoDvo = new ZwdbIntegrationDepositDvo();
        depoDvo.setItgDpNo(dvo.getItgDpNo());    /*통합입금번호*/
        depoDvo.setKwGrpCoCd("2000");    /*교원그룹회사코드*/
        depoDvo.setRveCoCd("2000");    /*수납회사코드*/

//        depoDvo.setCstNo(dvo.);    /*고객번호*/
        depoDvo.setDpDvCd("1");    /*입금구분코드*/
        depoDvo.setDpMesCd("03");    /*입금수단코드*/
        depoDvo.setDpTpCd("0301");    /*입금유형코드*/
        depoDvo.setDpDtm(sysDateYmd);    /*입금일시*/
        depoDvo.setPerfDt(sysDate);    /*실적일자*/
//        depoDvo.setDprNm();    /*입금자명*/
        depoDvo.setDpAmt(Integer.toString(sumAmt));    /*입금금액*/

        depoDvo.setDpBlam(Integer.toString(sumAmt));    /*입금잔액*/

        depoDvo.setIncmdcYn("N");    /*소득공제여부*/


        zwdbIntegrationDepositMapper.insertIntegrationDeposit(depoDvo);
        zwdbIntegrationDepositMapper.insertIntegrationDepositHistory(depoDvo);

        return processCount;
    }

    @Transactional
    public PagingResult<SearchElectronicRes> getRegistrationElectronicDetailPages(
        SearchElectronicReq dto, PageInfo pageInfo
    ) {
        return mapper.selectRegistrationElectronicDetails(dto, pageInfo);
    }

    @Transactional
    public List<SearchElectronicRes> getRegistrationElectronicDetailExcels(
        SearchElectronicReq dto
    ) {
        return mapper.selectRegistrationElectronicDetails(dto);
    }

    /**
     * 입금전표 생성
     *
     * @param dto
     * @return
     */
    @Transactional
    public int saveRegistrationElectronicDepositSlip(List<WwdbBillDepositMgtDto.SaveDepositSlip> dto) throws Exception {
        int processCount = 0;

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYmd = DateUtil.getNowDayString();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보

        //통합입금기본 조회
        ZwdbCorporationDepositDto.SearchIntegrationDepositRes integrationDepositRes = zwdbCorporationDepositMapper
            .selectIntegrationDepositInfo(dto.get(0).itgDpNo());

        int sumResult = 0;

        String year = sysDateYmd.substring(0, 4);
        String month = sysDateYmd.substring(4, 6);

        //전표 PK
        String zzsnum = slpnoService.getNumberingSlpno("FE", Integer.parseInt(year), Integer.parseInt(month));

        for (WwdbBillDepositMgtDto.SaveDepositSlip list : dto) {
            /*수납요청기본*/
            ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
            zwdzWithdrawalReceiveAskDvo.setKyowonGroupCompanyCd(session.getCompanyCode()); //KW_GRP_CO_CD	교원그룹회사코드
            zwdzWithdrawalReceiveAskDvo.setCustomNumber(list.cntrCstNo()); //CST_NO	고객번호
            zwdzWithdrawalReceiveAskDvo.setRveAkMthdCd("01"); //RVE_AK_MTHD_CD	수납요청방식코드 대면(01)
            zwdzWithdrawalReceiveAskDvo.setRveAkPhCd("20"); //RVE_AK_PH_CD	수납요청경로코드
            zwdzWithdrawalReceiveAskDvo.setRvePrtnrOgTpCd(session.getOgTpCd()); //RVE_AK_PRTNR_OG_TP_CD	수납요청파트너조직유형코드
            zwdzWithdrawalReceiveAskDvo.setRvePrtnrNo(session.getEmployeeIDNumber()); //RVE_AK_PRTNR_NO	수납요청파트너번호
            zwdzWithdrawalReceiveAskDvo.setReceiveAskAmount(list.billDpAmt()); //RVE_AK_AMT	수납요청금액
            zwdzWithdrawalReceiveAskDvo.setReceiveAskDate(sysDateYmd); //RVE_RQDT	수납요청일자
            zwdzWithdrawalReceiveAskDvo.setReceiveAskStatusCode("03"); //RVE_AK_STAT_CD	수납요청상태코드
            zwdzWithdrawalReceiveAskDvo.setReceiveCompanyCode(session.getCompanyCode()); //RVE_CO_CD	수납회사코드

            /*수납요청기본 데이터 생성*/
            String receiveAskNumber = zwdzWithdrawalService.createReceiveAskBase(zwdzWithdrawalReceiveAskDvo);
            zwdzWithdrawalReceiveAskDvo.setReceiveAskNumber(receiveAskNumber);

            /*수납요청상세*/
            WwdbBillDepositContractDvo contractDvo = new WwdbBillDepositContractDvo();
            contractDvo.setItgDpNo(dto.get(0).itgDpNo()); //통합입금번호
            contractDvo.setRveAkNo(receiveAskNumber); //요청번호
            contractDvo.setCntrNo(list.cntrNo()); //계약번호
            contractDvo.setCntrSn(list.cntrSn()); //일련번호

            processCount += mapper.insertBillDepositContracts(contractDvo);

            /*수납기본*/
            ZwdbWithdrawalReceiveAskReqDvo reqDvo = new ZwdbWithdrawalReceiveAskReqDvo();
            reqDvo.setRveAkNo(receiveAskNumber);

            ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo = etcDepositMapper.selectReceiveBaseInfo(reqDvo);

            String rveNo = zwdzWithdrawalService.createReceive(zwdzWithdrawalReceiveDvo);
            zwdzWithdrawalReceiveDvo.setRveNo(rveNo);

            /*입금대사기본*/
            reqDvo.setRveNo(rveNo); /*수납번호*/
            reqDvo.setProcsDvCd("1"); /*처리구분코드*/
            reqDvo.setIaDvCd("05"); /*입금항목구분코드*/
            //            reqDvo.setDpCprcnfBizDvCd(); /*입금대사업무구분코드*/
            reqDvo.setDpCprcnfBizCd("03"); /*입금대사업무코드*/

            ZwdzWithdrawalDepositCprDvo cprDvo = etcDepositMapper.selectDepositComparisonComfirmationInfo(reqDvo);

            String depositComparisonPk = zwdzWithdrawalService.createDepositComparison(cprDvo);

            /*수납상세*/
            reqDvo.setDpDt(integrationDepositRes.dpDtm()); /*입금일자*/
            reqDvo.setDpCprcnfNo(depositComparisonPk); /*대사번호*/
            reqDvo.setItgDpNo(dto.get(0).itgDpNo()); //통합입금번호

            zwdzWithdrawalReceiveDvo = etcDepositMapper.selectReceiveDetailInfo(reqDvo);

            //수납상세 데이터 생성
            processCount += zwdzWithdrawalService.createReceiveDetail(zwdzWithdrawalReceiveDvo);

            //통합입금 업데이트
            ZwwdbEtcDepositProcessingDvo itgDvo = new ZwwdbEtcDepositProcessingDvo();

            itgDvo.setItgDpNo(dto.get(0).itgDpNo());//통합입금번호
            itgDvo.setDpCprcnfAmt(list.billDpAmt()); //대사금액
            itgDvo.setRveAkNo(receiveAskNumber); //수납요청번호

            processCount += etcDepositMapper.updateIntegrationDeposit(itgDvo);

            //통합입금 이력
            ZwdbIntegrationDepositDvo depoDvo = new ZwdbIntegrationDepositDvo();
            depoDvo.setItgDpNo(integrationDepositRes.itgDpNo());
            zwdbIntegrationDepositMapper.insertIntegrationDepositHistory(depoDvo);


            sumResult += Integer.parseInt(list.billDpAmt());
        }


        //전표발행

//        String dpCprcnfDtm = zwdzWithdrawalReceiveAskDvo.getReceiveAskDate();


        WwdbBillDepositMgtDto.SearchSlipReq slipReq = new WwdbBillDepositMgtDto.SearchSlipReq(zzsnum, Integer.toString(sumResult), dto.get(0).itgDpNo());

        List<WwdbBillDepositSlipProcessingDvo> slipProcessingDvo = mapper.selectSlipProcessings(slipReq);
        log.info("service==========================");
        log.info(slipProcessingDvo.toString());
        log.info("service==========================");
        for (WwdbBillDepositSlipProcessingDvo list : slipProcessingDvo) {
            processCount += mapper.insertSlipProcessings(list);
        }


        return processCount;

    }

    /**
     * 대체전표 생성
     *
     * @param dto
     * @return
     */
    @Transactional
    public int saveReplacementSlipProcessing(List<WwdbBillDepositMgtDto.SaveDepositSlip> dto) throws Exception {
        int processCount = 0;

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYmd = DateUtil.getNowDayString();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보
        int sumResult = 0;

        for (WwdbBillDepositMgtDto.SaveDepositSlip list : dto) {
            sumResult += Integer.parseInt(list.billDpAmt());
        }

        //대체 전표발행
        String year = sysDateYmd.substring(0, 4);
        String month = sysDateYmd.substring(4, 6);

        String zzsnum = slpnoService.getNumberingSlpno("FE", Integer.parseInt(year), Integer.parseInt(month));
        WwdbBillDepositMgtDto.SearchSlipReq slipReq = new WwdbBillDepositMgtDto.SearchSlipReq(zzsnum, Integer.toString(sumResult), dto.get(0).itgDpNo());

        List<WwdbBillDepositSlipProcessingDvo> slipProcessingDvo = mapper.selectReplacementSlipProcessing(slipReq);

        for (WwdbBillDepositSlipProcessingDvo list : slipProcessingDvo) {
            processCount += mapper.insertSlipProcessings(list);
        }


        return processCount;

    }

}
