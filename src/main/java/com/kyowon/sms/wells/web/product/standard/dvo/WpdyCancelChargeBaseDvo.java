package com.kyowon.sms.wells.web.product.standard.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WpdyCancelChargeBaseDvo {
    private String ccamId; /* 위약금ID */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String vlStrtDtm; /* 유효시작일시 */
    private String vlEndDtm; /* 유효종료일시 */
    private String pdCd; /* 상품코드 */
    private Long csmbCs; /* 소모품비용 */
    private Long reqdCs; /* 철거비용 */
    private Long rentalRntf; /* 렌탈손료 */
    private Long spayRntf; /* 일시불손료 */
    private BigDecimal resCcamRat; /* 잔여위약금비율 */
    private String tempSaveYn; /* 임시저장여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
