package com.kyowon.sms.wells.web.closing.payment.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-CL-U-0016M01 입금 연체 현황
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-05-25
 */
public class WdcaDepositDelinquentDto {
    /**
     * 입금 연체 현황 조회 dto
     * @param perfYm 실적년월
     * @param copnDvCd 개인/법인구분
     * @param inqrDv 조회구분
     * @param sellChnl 판매채널
     * @param sellTpCd 판매유형
     * @param sellTpDtlCd 판매유형상세
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     */
    @ApiModel("WdcaDepositDelinquentDto-SearchReq")
    public record SearchReq(
        String perfYm, /*실적년월*/
        String copnDvCd, /*개인/법인구분*/
        String inqrDv, /*조회구분*/
        List<String> sellChnl, /*판매채널*/
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String cntrNo, /*계약번호*/
        String cntrSn /*계약일련번호*/
    ) {}

    /**
     * 입금 연체 현황-검색결과 dto
     */
    @ApiModel("WdcaDepositDelinquentDto-SearchRes")
    public record SearchRes(
        String sellTpCd, /*판매유형*/
        String sellTpDtlCd, /*판매유형상세*/
        String sellInflwChnlDtlCd, /*판매유입채널상세코드*/
        String pdClsfNm, /*상품명*/
        String totAccN, /*총 계정수*/
        String ucamTam, /*미수금 총액*/
        String fnlAmt, /*미수금액*/
        String thmNwAccN, /*계정수*/
        String thmNwDpAmt, /*입금금액*/
        String thmNwDpRt, /*입금률*/
        String nomUcAmt, /*미수금액*/
        String nomAccN, /*계정수*/
        String nomDpAmt, /*입금금액*/
        String nomDpRt, /*입금률*/
        String dlqAmt, /*연체금액*/
        String dlqAccN, /*계정수*/
        String dlqDpAmt, /*입금금액*/
        String ucCprDlqRt, /*미수대비연체율*/
        String totDpAmt, /*총입금액*/
        String dpRt, /*입금률*/
        String bilAgg, /*청구누계*/
        String dpAgg, /*입금누계*/
        String dlqRtSum /*연체율계*/
    ) {}

}
