package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniMyKPaymentInfoDto.FindReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniMyKPaymentInfoDto.FindRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniMyKPaymentInfoInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniMyKPaymentInfoDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniMyKPaymentInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsniMyKPaymentInfoService {

    private final WsniMyKPaymentInfoMapper mapper;
    private final WsniMyKPaymentInfoInterfaceConverter converter;

    public FindRes selectMyKPaymentInfo(FindReq req) throws Exception {

        /* 1. 입력받은 계약번호, 계약일련번호로 처리결과, 오류메시지, 월별 청구금액, 납부기관명, 납부번호, 입금자명, 이체일자를 리턴한다.(Q1)

            - 원장 조회되지 않으면 처리결과 N, 오류 메시지 "존재하지 않는 계약입니다."
            - 조회시 처리결과 Y, 오류 메시지 "".

            TB_SSCT_CNTR_DTL		계약상세
            TB_SSCT_CNTR_BAS		계약기본
            TB_SSCT_CNTR_STLM_REL		계약결제관계
            TB_SSCT_CNTR_STLM_BAS		계약결제기본
            TB_CBCL_WELLS_SL_MM_CL_IZ		WELLS매출월마감내역(당월)
            TB_CBCL_DLQ_BAS		연체기본(전월)
            TB_CBCL_DLQ_BAS		연체기본(당월)
            TB_CBCL_WELLS_BOR_AMT_BAS		위약금기본(전월)
            TB_RVCL_MCBY_BIL_DSC_IZ		월별청구할인내역(재약정)

        */

        WsniMyKPaymentInfoDvo dvo = converter.mapFindReqToWsniMyKPaymentInfoDvo(req);
        WsniMyKPaymentInfoDvo myKPaymentInfo = mapper.selectWsniMyKPaymentInfo(dvo);

        if (myKPaymentInfo != null) {
            myKPaymentInfo.setRsltCd("Y");
            myKPaymentInfo.setRsltMsg("");
        } else {
            myKPaymentInfo = new WsniMyKPaymentInfoDvo();
            myKPaymentInfo.setRsltCd("N");
            myKPaymentInfo.setRsltMsg("존재하지 않는 계약입니다.");
        }
        return converter.mapAllEctiMyKPaymentInfolDvoToFindRes(myKPaymentInfo);

    }

}
