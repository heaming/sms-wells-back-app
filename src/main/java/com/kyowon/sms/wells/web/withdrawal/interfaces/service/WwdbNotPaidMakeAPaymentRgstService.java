package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdbNotPaidMakeAPaymentRgstConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbNotPaidMakeAPaymentRgstDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentContractDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentRgstReqDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentRgstResDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbNotPaidMakeAPaymentRgstMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalDepositCprDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbNotPaidMakeAPaymentRgstService {

    private final WwdbNotPaidMakeAPaymentRgstMapper mapper;

    private final ZwdzWithdrawalService zwdzWithdrawalService;

    private final WwdbNotPaidMakeAPaymentRgstConverter converter;

    @Transactional
    public WwdbNotPaidMakeAPaymentRgstResDvo saveDepositRegistration(WwdbNotPaidMakeAPaymentRgstDto.SaveReq dto)
        throws Exception {

        WwdbNotPaidMakeAPaymentRgstResDvo resultDvo = new WwdbNotPaidMakeAPaymentRgstResDvo();
        WwdbNotPaidMakeAPaymentRgstReqDvo dvo = converter.mapWwdbNotPaidMakeAPaymentRgstSaveReqToDvo(dto);
        /*1.1 계약번호, 계약일련번호 미입력시 에러 리턴
            - MSG : '계약번호 을(를) 입력해주세요.'
            - MSG : '계약일련번호 을(를) 입력해주세요.'*/

        if (StringUtils.isEmpty(dvo.getCntrNo())) {
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("계약번호 을(를) 입력해주세요.");

            return resultDvo;
        }

        if (StringUtils.isEmpty(dvo.getCntrSn())) {
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("계약일련번호 을(를) 입력해주세요.");

            return resultDvo;
        }

        if (StringUtils.isEmpty(dvo.getDpDvCd())) {
            /*1.2 입금구분코드 미입력시 에러 리턴
            - MSG : '입금구분코드 을(를) 입력해주세요'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("입금구분코드 을(를) 입력해주세요.");

            return resultDvo;
        }

        if (!List.of("1", "2").contains(dvo.getDpDvCd())) {
            /*1.3 입금구분코드가 1, 2 가 아닌 경우 에러 리턴
                - MSG : '입금구분코드(1:입금, 2:환불)를 확인해주세요.'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("입금구분코드(1:입금, 2:환불)를 확인해주세요.");

            return resultDvo;
        }

        if (StringUtils.isEmpty(dvo.getSellTpCd())) {
            /*1.4 판매유형코드 미입력시 에러 리턴
            - MSG : '판매유형코드 을(를) 입력해주세요'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("판매유형코드 을(를) 입력해주세요.");

            return resultDvo;
        }

        //        if (!"2".equals(dvo.getSellTpCd()) || !"3".equals(dvo.getSellTpCd()) || !"6".equals(dvo.getSellTpCd())) {
        if (!List.of("2", "3", "6").contains(dvo.getSellTpCd())) {
            /*1.5 판매유형코드가 2, 3, 6이 아닌 경우 에러 리턴
            - MSG : '판매유형코드가(2:렌탈, 3:멤버십, 6:정기배송)를 확인해주세요.'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("판매유형코드가(2:렌탈, 3:멤버십, 6:정기배송)를 확인해주세요.");

            return resultDvo;
        }

        if (StringUtils.isEmpty(dvo.getPrtnrNo())) {
            /* 1.6 파트너번호 미입력시 에러 리턴
            - MSG : '파트너번호 을(를) 입력해주세요'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("파트너번호 을(를) 입력해주세요.");

            return resultDvo;
        }

        if (StringUtils.isEmpty(dvo.getDpTpCd())) {
            /*1.7 입금유형코드 미입력시 에러 리턴
            - MSG : '입금유형코드 을(를) 입력해주세요'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("입금유형코드 을(를) 입력해주세요.");

            return resultDvo;
        }

        if (!List.of("0103", "0204").contains(dvo.getDpTpCd())) {
            /*1.8 입금유형코드가 0103, 0204 가 아닌 경우 에러 리턴
            - MSG : '입금구분코드(0103:PG-계좌이체, 0204:PG-신용카드)를 확인해주세요.'*/
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("입금구분코드(0103:PG-계좌이체, 0204:PG-신용카드)를 확인해주세요.");

            return resultDvo;
        }

        if (StringUtils.isEmpty(dvo.getDpAmt())) {
            /*1.9 입금금액 미입력시 에러 리턴
            - MSG : '입금금액 을(를) 입력해주세요'*/

            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("입금금액 을(를) 입력해주세요.");

            return resultDvo;
        }

        if (dvo.getDpAmt() < 1) {
            /*1.9 입금금액 미입력시 에러 리턴
            - MSG : '입금금액 을(를) 입력해주세요'*/

            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("입금금액이 1보다 작을 수 없습니다.");

            return resultDvo;
        }

        WwdbNotPaidMakeAPaymentContractDvo contractDvo = mapper.selectContractInquiry(dvo);

        /*2. 주문번호 체크
        2.1 계약 데이터가 존재하지 않는 경우 에러 리턴
        - 조회 조건
        	TB_SSCT_CNTR_BAS(계약기본).CNTR_NO(계약번호) = 입력파리미터 계약번호(CNTR_NO)
        	TB_SSCT_CNTR_DTL(계약상세).CNTR_NO(계약번호) = 입력파리미터 계약번호(CNTR_NO)
        	TB_SSCT_CNTR_DTL(계약상세).CNTR_SN(계약일련번호) = 입력파리미터 계약일련번호(CNTR_SN)
        	TB_SSCT_CNTR_DTL(계약상세).SELL_TP_CD(판매유형코드) = 입력파라미터 판매유형코드(SELL_TP_CD)
        	TB_SSCT_CNTR_BAS(계약기본).DTA_DL_YN(데이터삭제여부) = 'N'
        	TB_SSCT_CNTR_DTL(계약상세).DTA_DL_YN(데이터삭제여부) = 'N'
        - 조인 조건
        	TB_SSCT_CNTR_BAS(계약기본).CNTR_NO(계약번호) = TB_SSCT_CNTR_DTL(계약상세).CNTR_NO(계약번호)
        - MSG : '고객정보를 찾을 수 없습니다.'
        2.2 판매유형코드가 3(멤버십)인 경우
        2.2.1 계약 데이터가 존재하는 경우
        	2.2.1.1 TB_SSCT_CNTR_DTL(계약상세).BASE_PD_CD(기준상품코드)가 '4020'와 동일하고 TB_SSCT_CNTR_BAS(계약기본).CNTR_CNFM_DTM(계약확정일자)가 NULL이 아닌 경우 에리 리턴
        		- MSG : '등록불가! 멤버십 미확정 입니다.'*/
        //만약 고객정보가 없으면 오류 발생
        if (ObjectUtils.isEmpty(contractDvo)) {

            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("고객정보를 찾을 수 없습니다.");

            return resultDvo;
        } else {
            //고객정보가 존재 하지만 계약확정일자가 Null인 경우
            if (StringUtils.isEmpty(contractDvo.getCntrCnfmDtm())) {

                resultDvo.setProcsRs("N");
                resultDvo.setErrMsg("등록불가! 멤버십 미확정 입니다.");

                return resultDvo;

            }
        }

        /*
        3. 실적마감 여부 체크
        3.1 마감 데이터 조회
        - 조회조건
        	Wells 경우 : TB_CBCL_WELLS_SL_MM_CL_IZ(WELLS매출월마감내역).SL_CL_YM(매출마감년월) = 현재년월('YYYYMM')
        	           TB_CBCL_WELLS_SL_MM_CL_IZ(WELLS매출월마감내역).CNTR_NO(계약번호) = 입력파리미터 계약번호(CNTR_NO)
        	           TB_CBCL_WELLS_SL_MM_CL_IZ(WELLS매출월마감내역).CNTR_SN(계약일련번호) = 입력파리미터 계약일련번호(CNTR_SN)
        	           TB_CBCL_WELLS_SL_MM_CL_IZ(WELLS매출월마감내역).SELL_TP_CD(판매유형코드) = 입력파라미터 판매유형코드(SELL_TP_CD)
        3.2 데이터가 조회 되는 경우 에러 리턴
        - MSG : '등록불가! 해당년월에 실적이 마감되었습니다.'*/

        int inquiryResult = mapper.selectContractMonthCloseInquiry(dvo);

        if (inquiryResult > 0) {
            resultDvo.setProcsRs("N");
            resultDvo.setErrMsg("등록불가! 해당년월에 실적이 마감되었습니다.");

            return resultDvo;
        }

        /*4. 입금 등록 처리
            수납요청기본
         */

        //오늘 날짜
        String sysDate = DateUtil.getNowString();
        String sysDateYmd = DateUtil.getNowDayString();

        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession(); //세션정보 불러오기

        String iaDvCd = null;
        //        - IA_DV_CD(입금항목구분코드) : 입력파라미터 판매유형코드(SELL_TP_CD)가 2(렌탈)인 경우 11(렌탈), 3(멤버십)인 경우 12, 6(정기배송)인 경우 14(정기배송) 셋팅
        switch (contractDvo.getSellTpCd()) {
            case "2":
                iaDvCd = "11";
                break;
            case "3":
                iaDvCd = "12";
                break;
            case "6":
                iaDvCd = "14";
                break;
        }

        //수납요청 데이터 생성
        String receiveAskNumber = receiveAskRgst(dvo, contractDvo, sysDateYmd, session);

        //수납요청상세 데이터 생성
        receiveAskDtlRgst(dvo, contractDvo, session, receiveAskNumber);

        //입금대사 데이터 생성
        String depositPk = depositRgst(dvo, contractDvo, sysDate, sysDateYmd, session, iaDvCd);

        //수납기본 데이터 생성
        String rveNo = receiveBase(dvo, contractDvo, sysDateYmd, session, receiveAskNumber);

        //수납상세 데이터 생성

        receiveDtl(dvo, contractDvo, sysDateYmd, session, depositPk, rveNo);

        resultDvo.setProcsRs("Y");
        resultDvo.setErrMsg("정상 처리 되었습니다.");

        return resultDvo;
    }

    private void receiveDtl(
        WwdbNotPaidMakeAPaymentRgstReqDvo dvo, WwdbNotPaidMakeAPaymentContractDvo contractDvo, String sysDateYmd,
        UserSessionDvo session, String depositPk, String rveNo
    ) {
        /*수납상세 인설트 데이터 입력*/
        ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo = new ZwdzWithdrawalReceiveDvo();
        zwdzWithdrawalReceiveDvo.setKwGrpCoCd(session.getCompanyCode());//            KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveDvo.setRveNo(rveNo);//       RVE_NO 수납번호
        zwdzWithdrawalReceiveDvo.setRveSn(Integer.toString(1));//                        RVE_SN	수납일련번호
        zwdzWithdrawalReceiveDvo.setDpDvCd(dvo.getDpDvCd());//            DP_DV_CD	입금구분코드(1 입금)
        zwdzWithdrawalReceiveDvo.setDpMesCd(dvo.getDpTpCd() == "0103" ? "01" : "02");//            DP_MES_CD	입금수단코드(01 현금)
        zwdzWithdrawalReceiveDvo.setDpTpCd(dvo.getDpTpCd());//            DP_TP_CD	입금유형코드( )
        //        zwdzWithdrawalReceiveDvo.setRveDvCd("");//            RVE_DV_CD	수납구분코드
        //            RVE_BIZ_DV_CD	수납업무구분코드

        zwdzWithdrawalReceiveDvo.setOgTpCd(session.getOgTpCd());//            OG_TP_CD	조직유형코드
        zwdzWithdrawalReceiveDvo.setPrtnrNo(session.getEmployeeIDNumber());//            PRTNR_NO	파트너번호
        zwdzWithdrawalReceiveDvo.setProcsDvCd("1");//            PROCS_DV_CD	처리구분코드(1 정상)
        zwdzWithdrawalReceiveDvo.setDpDt(sysDateYmd);//            DP_DT	입금일자
        zwdzWithdrawalReceiveDvo.setDpAmt(Long.toString(dvo.getDpAmt()));//            DP_AMT	입금금액
        zwdzWithdrawalReceiveDvo.setRveDt(sysDateYmd);//            RVE_DT	수납일자
        zwdzWithdrawalReceiveDvo.setRveAmt(Long.toString(dvo.getDpAmt()));//            RVE_AMT	수납금액
        zwdzWithdrawalReceiveDvo.setRveProcsYn("Y");//            RVE_PROCS_YN	수납처리여부
        zwdzWithdrawalReceiveDvo.setPerfDt(sysDateYmd);//            PERF_DT	실적일자
        //            RVE_AK_NO	수납요청번호
        zwdzWithdrawalReceiveDvo.setRveAkSn(Integer.toString(1));//            RVE_AK_SN	수납요청일련번호
        zwdzWithdrawalReceiveDvo.setDpCprcnfNo(depositPk);//            DP_CPRCNF_NO	입금대사번호
        zwdzWithdrawalReceiveDvo.setCntrNo(contractDvo.getCntrNo());//            CNTR_NO	계약번호
        zwdzWithdrawalReceiveDvo.setCntrSn(contractDvo.getCntrSn());//            CNTR_SN	계약일련번호
        zwdzWithdrawalReceiveDvo.setPdCd(contractDvo.getBasePdCd());//            PD_CD	상품코드
        zwdzWithdrawalReceiveDvo.setRveOjDrmNo2(contractDvo.getCntrNo());//            RVE_OJ_DRM_NO1	수납대상식별번호1
        zwdzWithdrawalReceiveDvo.setRveOjDrmNo2(contractDvo.getCntrSn());//            RVE_OJ_DRM_NO2	수납대상식별번호2
        zwdzWithdrawalReceiveDvo.setIncmdcYn("N");//            INCMDC_YN	소득공제여부
        zwdzWithdrawalReceiveDvo.setRveCoCd(session.getCompanyCode());//            RVE_CO_CD	수납회사코드

        //수납상세 데이터 생성
        zwdzWithdrawalService.createReceiveDetail(zwdzWithdrawalReceiveDvo);

    }

    private String receiveBase(
        WwdbNotPaidMakeAPaymentRgstReqDvo dvo, WwdbNotPaidMakeAPaymentContractDvo contractDvo, String sysDateYmd,
        UserSessionDvo session, String receiveAskNumber
    ) {
        /*수납기본 데이터 입력*/
        ZwdzWithdrawalReceiveDvo zwdzWithdrawalReceiveDvo = new ZwdzWithdrawalReceiveDvo();
        zwdzWithdrawalReceiveDvo.setKwGrpCoCd(session.getCompanyCode()); //        KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveDvo.setCstNo(contractDvo.getCntrCstNo()); //        CST_NO	고객번호
        zwdzWithdrawalReceiveDvo.setRveAkNo(receiveAskNumber); //        RVE_AK_NO	수납요청번호
        zwdzWithdrawalReceiveDvo.setRvePhCd("01"); //        RVE_PH_CD	수납경로코드
        zwdzWithdrawalReceiveDvo.setRveDt(sysDateYmd); //        RVE_DT	수납일자
        zwdzWithdrawalReceiveDvo.setRveAmt(Long.toString(dvo.getDpAmt())); //        RVE_AMT	수납금액

        //수납기본 데이터 생성 (수납번호 리턴)
        return zwdzWithdrawalService.createReceive(zwdzWithdrawalReceiveDvo);
    }

    private String depositRgst(
        WwdbNotPaidMakeAPaymentRgstReqDvo dvo, WwdbNotPaidMakeAPaymentContractDvo contractDvo, String sysDate,
        String sysDateYmd, UserSessionDvo session, String iaDvCd
    ) {
        /*입금대사 인설트 데이터 생성*/
        ZwdzWithdrawalDepositCprDvo depositCprDvo = new ZwdzWithdrawalDepositCprDvo();

        depositCprDvo.setKwGrpCoCd(session.getCompanyCode()); /*교원그룹회사코드*/ // KW_GRP_CO_CD	교원그룹회사코드
        depositCprDvo.setRveCoCd(session.getCompanyCode()); /*수납회사코드*/ // RVE_CO_CD	수납회사코드
        depositCprDvo.setProcsDvCd("1"); /*처리구분코드*/ // PROCS_DV_CD	처리구분코드(1 정상)
        depositCprDvo.setDpDvCd(dvo.getDpDvCd()); /*입금구분코드*/ // DP_DV_CD	입금구분코드(2 환불)
        depositCprDvo.setDpMesCd(dvo.getDpTpCd() == "0103" ? "01" : "02"); /*입금수단코드*/ // DP_MES_CD	입금수단코드(01 현금)
        depositCprDvo.setDpTpCd(dvo.getDpTpCd()); /*입금유형코드*/ // DP_TP_CD	입금유형코드()
        //        depositCprDvo.setRveDvCd(); /*수납구분코드*/ // RVE_DV_CD	수납구분코드 (98 기타선수)
        // RVE_BIZ_DV_CD	수납업무구분코드
        depositCprDvo.setIaDvCd(iaDvCd); /*입금항목구분코드*/ // IA_DV_CD	입금항목구분0코드 ??
        //        depositCprDvo.setDpCprcnfBizDvCd("03"); /*입금대사업무구분코드*/ // DP_CPRCNF_BIZ_DV_CD	입금대사업무구분코드
        depositCprDvo.setDpCprcnfBizCd("03"); /*입금대사업무코드*/ // DP_CPRCNF_BIZ_CD	일단 입금대사업무코드(03 입금등록)
        //            depositCprDvo.setDpCprcnfPdClsfCd(); /*입금대사상품분류코드*/                               // DP_CPRCNF_PD_CLSF_CD	입금대사상품분류코드
        //            depositCprDvo.setDpCprcnfPdClsfId(); /*입금대사상품분류id*/                               // DP_CPRCNF_PD_CLSF_ID	입금대사상품분류ID
        depositCprDvo.setDpCprcnfSellTpCd(contractDvo.getSellTpCd()); /*입금대사판매유형코드*/ // DP_CPRCNF_SELL_TP_CD	입금대사판매유형코드
        depositCprDvo.setDpCprcnfDtm(sysDate); /*입금대사일시*/ // DP_CPRCNF_DTM	입금대사일시
        depositCprDvo.setDpCprcnfPerfDt(sysDateYmd); /*입금대사실적일자*/ // DP_CPRCNF_PERF_DT	입금대사실적일자
        depositCprDvo.setDpCprcnfCanYn("N"); /*입금대사취소여부*/ // DP_CPRCNF_CAN_YN	입금대사취소여부
        depositCprDvo.setDpCprcnfCnfmYn("Y"); /*입금대사확정여부*/ // DP_CPRCNF_CNFM_YN	입금대사확정여부
        depositCprDvo.setDpCprcnfCnfmDtm(sysDate); /*입금대사확정일시*/ // DP_CPRCNF_CNFM_DTM	입금대사확정일시
        depositCprDvo.setDpCprcnfAmt(Long.toString(dvo.getDpAmt())); /*입금대사금액*/ // DP_CPRCNF_AMT	입금대사금액 ??
        depositCprDvo.setDpCprcnfProcsAmt(Long.toString(dvo.getDpAmt())); /*입금대사처리금액*/ // DP_CPRCNF_PROCS_AMT	입금대사처리금액 ??
        //            depositCprDvo.setDpCprcnfBlam(); /*입금대사잔액*/ // DP_CPRCNF_BLAM	입금대사잔액 ??
        depositCprDvo.setDpCprcnfDstApyYn("N"); /*입금대사배분적용여부*/ // DP_CPRCNF_DST_APY_YN	입금대사배분적용여부 ??
        depositCprDvo.setCntrNo(contractDvo.getCntrNo()); /*계약번호*/ // CNTR_NO	계약번호
        depositCprDvo.setCntrSn(contractDvo.getCntrSn()); /*계약일련번호*/ // CNTR_SN	계약일련번호
        depositCprDvo.setPdCd(contractDvo.getBasePdCd()); /*상품코드*/ // PD_CD	상품코드
        depositCprDvo.setRveOjDrmNo1(contractDvo.getCntrNo()); /*수납대상식별번호1*/ // RVE_OJ_DRM_NO1	수납대상식별번호1
        depositCprDvo.setRveOjDrmNo2(contractDvo.getCntrSn()); /*수납대상식별번호2*/ // RVE_OJ_DRM_NO2	수납대상식별번호2
        depositCprDvo.setIncmdcYn("N"); /*소득공제여부*/ // INCMDC_YN	소득공제여부

        //입금대사 데이터 생성(리턴 입금대사번호)
        return zwdzWithdrawalService.createDepositComparison(depositCprDvo);
    }

    private int receiveAskDtlRgst(
        WwdbNotPaidMakeAPaymentRgstReqDvo dvo, WwdbNotPaidMakeAPaymentContractDvo contractDvo, UserSessionDvo session,
        String receiveAskNumber
    ) {
        int processCount = 0;

        /*수납요청상세 데이터 입력*/
        ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
        zwdzWithdrawalReceiveAskDvo.setKyowonGroupCompanyCd(session.getCompanyCode()); //KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveAskDvo.setReceiveAskNumber(receiveAskNumber); //수납요청번호

        zwdzWithdrawalReceiveAskDvo.setDepositDivideCode(dvo.getDpDvCd());//입금구분코드
        zwdzWithdrawalReceiveAskDvo.setDepositMeansCode(dvo.getDpTpCd() == "0103" ? "01" : "02");//입금수단코드
        zwdzWithdrawalReceiveAskDvo.setDepositTypeCode(dvo.getDpTpCd());//입금유형코드
        //        zwdzWithdrawalReceiveAskDvo.setReceiveDivideCode();//수납구분코드
        //                        zwdzWithdrawalReceiveAskDvo.set//수납업무구분코드
        zwdzWithdrawalReceiveAskDvo.setOrganizationId(session.getOgId());//조직ID
        zwdzWithdrawalReceiveAskDvo.setOrganizationTypeCode(session.getOgTpCd());//조직유형코드
        zwdzWithdrawalReceiveAskDvo.setPartnerNumber(session.getEmployeeIDNumber());//파트너번호
        zwdzWithdrawalReceiveAskDvo.setContractNumber(contractDvo.getCntrNo());//계약번호
        zwdzWithdrawalReceiveAskDvo.setContractSerialNumber(contractDvo.getCntrSn()); //계약일련번호
        zwdzWithdrawalReceiveAskDvo.setProductCode(contractDvo.getBasePdCd());//상품코드
        //RVE_AK_OJ_DRM_NO1	수납요청대상식별번호1
        //RVE_AK_OJ_DRM_NO2	수납요청대상식별번호2
        zwdzWithdrawalReceiveAskDvo.setReceiveAskAmount(Long.toString(dvo.getDpAmt()));//수납요청금액
        zwdzWithdrawalReceiveAskDvo.setReceiveAmount(Long.toString(dvo.getDpAmt()));//수납금액
        zwdzWithdrawalReceiveAskDvo.setReceiveStatusCode("02"); //수납상태코드 수납완료(02)
        zwdzWithdrawalReceiveAskDvo.setIncmdcYn("N"); //소득공제여부

        // 수납요청상세 데이터 생성
        processCount += zwdzWithdrawalService.createReceiveAskDetail(zwdzWithdrawalReceiveAskDvo);

        //수납요청상세 이력 생성
        processCount += zwdzWithdrawalService.createReceiveAskDetailHistory(zwdzWithdrawalReceiveAskDvo);

        return processCount;
    }

    private String receiveAskRgst(
        WwdbNotPaidMakeAPaymentRgstReqDvo dvo, WwdbNotPaidMakeAPaymentContractDvo contractDvo, String sysDateYmd,
        UserSessionDvo session
    ) {

        /*수납요청기본 데이터 입력*/
        ZwdzWithdrawalReceiveAskDvo zwdzWithdrawalReceiveAskDvo = new ZwdzWithdrawalReceiveAskDvo();
        zwdzWithdrawalReceiveAskDvo.setKyowonGroupCompanyCd(session.getCompanyCode()); //KW_GRP_CO_CD	교원그룹회사코드
        zwdzWithdrawalReceiveAskDvo.setCustomNumber(contractDvo.getCntrCstNo()); //CST_NO	고객번호
        zwdzWithdrawalReceiveAskDvo.setRveAkMthdCd("02"); //RVE_AK_MTHD_CD	수납요청방식코드 비대면(02)
        zwdzWithdrawalReceiveAskDvo.setRveAkPhCd("01"); //RVE_AK_PH_CD	수납요청경로코드 판매(01)
        zwdzWithdrawalReceiveAskDvo.setRvePrtnrOgTpCd(session.getOgTpCd()); //RVE_AK_PRTNR_OG_TP_CD	수납요청파트너조직유형코드
        zwdzWithdrawalReceiveAskDvo.setRvePrtnrNo(session.getEmployeeIDNumber()); //RVE_AK_PRTNR_NO	수납요청파트너번호
        zwdzWithdrawalReceiveAskDvo.setReceiveAskAmount(Long.toString(dvo.getDpAmt())); //RVE_AK_AMT	수납요청금액
        zwdzWithdrawalReceiveAskDvo.setReceiveAskDate(sysDateYmd); //RVE_RQDT	수납요청일자
        zwdzWithdrawalReceiveAskDvo.setReceiveAskStatusCode("03"); //RVE_AK_STAT_CD	수납요청상태코드 03(처리완료)
        zwdzWithdrawalReceiveAskDvo.setReceiveCompanyCode(session.getCompanyCode()); //RVE_CO_CD	수납회사코드

        /*수납요청기본 데이터 생성*/
        String receiveAskNumber = zwdzWithdrawalService.createReceiveAskBase(zwdzWithdrawalReceiveAskDvo);
        return receiveAskNumber;
    }

}
