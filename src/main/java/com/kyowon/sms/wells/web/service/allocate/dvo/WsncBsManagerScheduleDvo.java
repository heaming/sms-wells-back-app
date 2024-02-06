package com.kyowon.sms.wells.web.service.allocate.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsncBsManagerScheduleDvo {
    String vstDt; // 방문일자
    String vstTm; // 방문시간
    String cstNm; // 고객명
    String cntrNo; // 계약번호
    String pdctCD; // 제품명
    String goodsNm; // 제품명
    //        String mpNo; // 휴대전화번호
    String puPart1; // 투입부품1
    String puPart2; // 투입부품2
    String puPart3; // 투입부품3
    String puPart4; // 투입부품4
    String puPart5; // 투입부품5
    String puPart6; // 투입부품6
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String istYm; // 설치년월
    Integer cntrNmnN; // 사용차월
    Integer stplPtrm; // 약정기간
    Long fnlAmt; // 렌탈료
    Integer svHshdCnt; // 사용계정수
}
