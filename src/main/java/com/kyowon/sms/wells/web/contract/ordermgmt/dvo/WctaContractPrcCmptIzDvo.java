package com.kyowon.sms.wells.web.contract.ordermgmt.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctaContractPrcCmptIzDvo {
    private String cntrPrcCmptId; /* 계약가격산출ID */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String pdCd; /* 상품코드 */
    private String pdPrcFnlDtlId; /* 상품가격최종상세ID */
    private Integer verSn; /* 버전일련번호 */
    private String pdPrpMetaId; /* 상품속성메타ID */
    private String prpNm; /* 속성명 */
    private String pdPrcPrpVal; /* 상품가격속성값 */
    private Integer apyStrtTn; /* 적용시작회차 */
    private Integer apyEndTn; /* 적용종료회차 */
    private String fxamFxrtDvCd; /* 정액정률구분코드 */
    private Long ctrVal; /* 조정값 */
    private Long fnlVal; /* 최종값 */
    private String pdPrcId; /* 상품가격ID */
    private String dtaDlYn; /* 데이터삭제여부 */
}
