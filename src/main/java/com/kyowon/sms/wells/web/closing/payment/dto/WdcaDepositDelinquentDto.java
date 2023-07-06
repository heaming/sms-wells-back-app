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
        String perfYm,
        String copnDvCd,
        String inqrDv,
        List<String> sellChnl,
        String sellTpCd,
        String sellTpDtlCd,
        String cntrNo,
        String cntrSn
    ) {}

    /**
     * 입금 연체 현황-검색결과 dto
     */
    @ApiModel("WdcaDepositDelinquentDto-SearchRes")
    public record SearchRes(
        String sellTpCd,
        String sellTpDtlCd,
        String sellInflwChnlDtlCd,
        String pdClsfNm,
        String totAccN,
        String ucamTam,
        String fnlAmt,
        String thmNwAccN,
        String thmNwDpAmt,
        String thmNwDpRt,
        String nomUcAmt,
        String nomAccN,
        String nomDpAmt,
        String nomDpRt,
        String dlqAmt,
        String dlqAccN,
        String dlqDpAmt,
        String ucCprDlqRt,
        String totDpAmt,
        String dpRt,
        String bilAgg,
        String dpAgg,
        String dlqRtSum
    ) {}

}
