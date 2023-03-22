package com.kyowon.sms.wells.web.contract.common.dvo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WctzCntrDtlStatChHistDvo {
    private String cntrNo; /* 계약번호 */
    private String cntrSn; /* 계약일련번호 */
    private String histStrtDtm; /* 이력시작일시 */
    private String histEndDtm; /* 이력종료일시 */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
    private String cntrDtlStatChRsonCd; /* 계약상세상태변경사유코드 */
    private String cntrDtlStatChMoCn; /* 계약상세상태변경메모내용 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
