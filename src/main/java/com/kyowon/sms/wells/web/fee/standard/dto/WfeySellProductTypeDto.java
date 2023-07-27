package com.kyowon.sms.wells.web.fee.standard.dto;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;

import javax.validation.constraints.NotBlank;

public class WfeySellProductTypeDto {

    public record SearchSellProductReq(
        String basePdCd,
        String apyStrtYm,
        String apyEndYm
    ) {}

    public record SearchSellProductRes(
        String basePdCd,       /* 기준상품코드 */
        String basePdNm,
        String apyStrtYm,      /* 적용시작년월 */
        String apyEndYm,       /* 적용종료년월 */
        String feePdctTpCd1,  /* 수수료제품유형코드1 */
        String feePdctTpCd2,  /* 수수료제품유형코드2 */
        String dtaDlYn,        /* 데이터삭제여부 */
        String fstRgstDtm,     /* 최초등록일시 */
        @MaskRequired(type = MaskingType.NAME)
        String fstRgstUsrId,  /* 최초등록사용자ID */
        String fstRgstPrgId,  /* 최초등록프로그램ID */
        String fstRgstDeptId, /* 최초등록부서ID */
        String fnlMdfcDtm,     /* 최종수정일시 */
        @MaskRequired(type = MaskingType.NAME)
        String fnlMdfcUsrId,  /* 최종수정사용자ID */
        String fnlMdfcPrgId,  /* 최종수정프로그램ID */
        String fnlMdfcDeptId /* 최종수정부서ID */

    ) {}

    public record SaveSellProductReq(
        String rowState,
        @NotBlank
        String basePdCd,
        @NotBlank
        String apyStrtYm,
        @NotBlank
        String apyEndYm,
        String feePdctTpCd1,
        String feePdctTpCd2
    ) {}
}
