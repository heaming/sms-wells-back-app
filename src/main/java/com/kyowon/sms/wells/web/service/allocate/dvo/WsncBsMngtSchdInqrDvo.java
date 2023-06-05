package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * W-SV-U-0036M01 책임지역 우편번호 관리
 * </pre>
 *
 * @author gs.piit130 김혜원
 * @since 2022.11.17
 */
@Setter
@Getter
public class WsncBsMngtSchdInqrDvo {

    String vstDt; // 방문일자
    String vstTm; // 방문시간
    String cstNm; // 고객명
    String cntrNo; // 계약번호
    String goodsNm; // 제품명
    String mpNo; // 휴대전화번호
    String puPart1; // 투입부품1
    String puPart2; // 투입부품2
    String curntDt; // 최초계약일
    String cntrDt; // 계약일자
    String mngtAcc; // 관리계정
    String vstAcc; // 방문계정
    String fshAcc; // 완료계정
    String svcProc; // 서비스처리율

}
