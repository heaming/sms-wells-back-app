package com.kyowon.sms.wells.web.withdrawal.pchssl.dto;

import io.swagger.annotations.ApiModel;

public class WwdcPrepaymentEstimateDto {

    // 선납예상금 데이터 
    @ApiModel("WwdcPrepaymentEstimateDto-SearchPrepaymentEstimateReq")
    public record SearchPrepaymentEstimateReq(
        String cntrNo,
        String cntrSn,
        String prmMcn
    ) {}

    @ApiModel("WwdcPrepaymentEstimateDto-SearchPrepaymentEstimateRes")
    public record SearchPrepaymentEstimateRes(
        String totDpAmt, /* 총 입금 금액 */
        String prmExpAmt, /* 총 선납예정금액  */
        String prmStrtY, /* 선납기간 From 연도 */
        String prmStrtMm, /* 선납기간 From 월 */
        String prmEndY, /* 선납기간 To 연도 */
        String prmEndMm, /* 선납기간 To 월 */
        String prmStrtAndEndYm, /* 선납기간 From ~ To */

        String dcAmt, /* 할인금액*/
        String dcData, /* 할인료 */
        String cntrChDtlAkCn, /* 선납 불가 사유 */

        // ASIS 데이터(조건-할인)
        String m12nrmlDcAmt01, /* 정상할인 = 렌탈료1 - ( 렌탈료2 * 0.95 )   */
        String m12dcAmt01, /* ＤＣ할인 = 렌탈할인1 * 0.95      */
        String m12prmtDcAmt, /* 프로모션할인 = 할인금액 * 0.95      */
        String m24nrmlDcAmt02, /* 정상할인 = 렌탈료2 - ( 렌탈료2 * 0.93 )   */
        String m24dcAmt02, /* ＤＣ할인 = 렌탈할인2 * 0.93      */
        String m24prmtDcAmt, /* 프로모션할인 = 할인금액 * 0.93      */
        String m36nrmlDcAmt03, /* 정상할인 = 렌탈료3 - ( 렌탈료2 * 0.9 )   */
        String m36dcAmt03, /* ＤＣ할인 = 렌탈할인3 * 0.9      */
        String m36prmtDcAmt /* 프로모션할인 = 할인금액 * 0.9   */
    ) {}

}
