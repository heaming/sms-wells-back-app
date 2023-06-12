package com.kyowon.sms.wells.web.service.orgcode.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.08
 */
@Setter
@Getter
public class WsndPlaceOfDeliveryDvo {

    String pdlvDvCd; // 출고지구분코드
    String pdlvNo; // 출고지번호
    String pdlvNm; // 출고지명
    String zip; // 우편번호
    String pdlvAdr; // 출고지주소
    String apyStrtdt; // 적용시작일
    String dataDlYn; // 데이터삭제여부

}
