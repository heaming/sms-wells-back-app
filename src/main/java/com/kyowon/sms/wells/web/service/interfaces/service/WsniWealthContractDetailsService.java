package com.kyowon.sms.wells.web.service.interfaces.service;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniWealthContractDetailsDto.FindReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniWealthContractDetailsDto.FindRes;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniWealthContractDetailsInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniWealthContractDetailsDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniWealthContractDetailsMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsniWealthContractDetailsService {

    private final WsniWealthContractDetailsMapper mapper;
    private final WsniWealthContractDetailsInterfaceConverter converter;

    public FindRes selectWealthContractDetails(FindReq req) throws Exception {

        /*
         *  입력
         *     - 고객번호
         *     - 고객일련번호
         *  출력
         *     - 결과
         *      계약번호
         *      계약일련번호
                상품코드
                상품명
                구매유형
                구매유형상세
                구매가(총계약금액)
                매출일자
                약정개월수
                납부구분
                월납부금액
                설치자명
                설치자휴대전화
                설치자전화
                설치/배송우편번호
                설치배송주소1
                설치배송주소2
                설치배송주소3
                계약자명
                계약자연락처
                계약자우편번호
                계약자주소1
                계약자주소2
                계약자주소3
                계약자이메일
                계약상태
                설치일자
                사용개월
                만기일자
                웰스매니저명
                웰스매니저연락처
                연체금액

        */

        WsniWealthContractDetailsDvo dvo = converter.mapFindReqToWsniWealthContractDetailsDvo(req);
        WsniWealthContractDetailsDvo wealthContractDetails = mapper.selectWsniWealthContractDetails(dvo);

        if (wealthContractDetails != null) {
            wealthContractDetails.setRsltCd("Y");
            wealthContractDetails.setRsltMsg("");
        } else {
            wealthContractDetails = new WsniWealthContractDetailsDvo();
            wealthContractDetails.setRsltCd("N");
            wealthContractDetails.setRsltMsg("계약번호가 존재 하지 않습니다.");
        }
        return converter.mapAllWsniWealthContractDetailsDvoToFindRes(wealthContractDetails);

    }

}
