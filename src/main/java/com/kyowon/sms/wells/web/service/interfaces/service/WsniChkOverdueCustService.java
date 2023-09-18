package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto.FindReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto.FindRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniChkOverdueCustInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniChkOverdueCustDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniChkOverdueCustMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsniChkOverdueCustService {

    private final WsniChkOverdueCustMapper mapper;
    private final WsniChkOverdueCustInterfaceConverter converter;

    public FindRes selectChkOverdueCust(FindReq req) throws Exception {

        /*
         *  입력
         *     - 교원키(고객계약번호)
         *     - 연체개월수
         *  출력
         *     - 체크결과 - 정상 : 'Y', 오류 : 'N'
         *     - 연체여부 - 조회결과 있을경우 'Y'
         *     - 총연체금액
         *     - 주문번호별연체금액
         *     - 결과메시지상세 : 체크결과 오류시 오류메시지(연체이력이 없는 계약입니다)


        */

        WsniChkOverdueCustDvo dvo = converter.mapFindReqToWsniChkOverdueCustDvo(req);
        WsniChkOverdueCustDvo chkOverdueCust = mapper.selectWsniChkOverdueCust(dvo);

        if (chkOverdueCust != null) {
            chkOverdueCust.setRsltCd("Y");
            chkOverdueCust.setRsltMsg("");
        } else {
            chkOverdueCust = new WsniChkOverdueCustDvo();
            chkOverdueCust.setRsltCd("N");
            chkOverdueCust.setRsltMsg("연체이력이 없는 계약입니다.");
        }
        return converter.mapAllEctiChkOverdueCustlDvoToFindRes(chkOverdueCust);

    }

}
