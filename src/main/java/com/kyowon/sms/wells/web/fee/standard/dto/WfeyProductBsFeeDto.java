package com.kyowon.sms.wells.web.fee.standard.dto;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;

import javax.validation.constraints.NotBlank;

public class WfeyProductBsFeeDto {

    public record SearchProductBsFeeReq(
        String basePdCd, /* 기준상품코드 */
        String vstMcn, /* 방문개월 */
        String svFeeDvCd, /* 서비스구분코드 */
        String apyStrtYm, /* 적용기간 시작 */
        String apyEndYm, /* 적용기간 종료 */
        String svFeePdDvCd /* BS상품그룹코드 */
    ) {}

    public record SearchProductBsFeeRes(
        String basePdCd,       /* 기준상품코드 */
        String basePdNm,
        Integer vstMcn,          /* 방문개월수 */
        String svFeeDvCd,     /* 서비스수수료구분코드 */
        String hcrDvCd1,        /* 홈케어구분코드 */
        String hcrDvCd2,        /* 홈케어구분코드 */
        String svFeePdDvCd,  /* 서비스수수료상품구분코드 */
        Integer baseChTcnt,     /* 기준변경차수 */
        Long svFeeBaseAmt,  /* 서비스수수료기준금액 */
        String feeFxamYn,      /* 수수료정액여부 */
        Long hcrFeeBaseAmt, /* 홈케어수수료기준금액 */
        String apyStrtYm,      /* 적용시작년월 */
        String apyEndYm,      /* 적용종료년월 */
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

    public record SaveProductBsFeeReq(
        String rowState,
        @NotBlank
        String basePdCd,       /* 기준상품코드 */
        @NotBlank
        Integer vstMcn,          /* 방문개월수 */
        @NotBlank
        String svFeeDvCd,     /* 서비스수수료구분코드 */
        String hcrDvCd1,        /* 홈케어구분코드 */
        String hcrDvCd2,        /* 홈케어구분코드 */
        String svFeePdDvCd,  /* 서비스수수료상품구분코드 */
        @NotBlank
        Integer baseChTcnt,     /* 기준변경차수 */
        Long svFeeBaseAmt,  /* 서비스수수료기준금액 */
        String feeFxamYn,      /* 수수료정액여부 */
        Long hcrFeeBaseAmt, /* 홈케어수수료기준금액 */
        String apyStrtYm,      /* 적용시작년월 */
        String apyEndYm,      /* 적용종료년월 */
        String dtaDlYn
    ) {}
}
