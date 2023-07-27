package com.kyowon.sms.wells.web.fee.standard.dto;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WfeyContractBsFeeExDto {

    public record SearchContractBsFeeExReq(
        String cntrNo,
        String cntrSn,
        String basePdCd,
        Integer vstMcn,
        String apyStrtYm,
        String apyEndYm,
        String svFeePdDvCd
    ) {}

    public record SearchContractBsFeeExRes(
        String cntrNo,            /* 계약번호 */
        String cntrSn,           /* 계약일련번호 */
        String cntrDtlSn, /* 계약번호-계약일련번호 */
        @MaskRequired(type = MaskingType.NAME)
        String cntorNm, /* 계약자명 */
        String basePdCd, /* 상품 */
        String basePdNm,
        String vstMcn,           /* 방문개월수 */
        String baseChTcnt,       /* 기준변경차수 */
        String svFeePdDvCd,       /* 서비스수수료상품구분코드 */
        String svFeeBaseAmt,        /* 서비스수수료기준금액 */
        String feeFxamYn,         /* 수수료정액여부 */
        String apyStrtYm,         /* 적용시작년월 */
        String apyEndYm,          /* 적용종료년월 */
        String feeCtrRsonCn,      /* 수수료조정사유내용 */
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

    public record SaveContractBsFeeExReq(
        String rowState,
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String cntrDtlSn,            /* 계약번호-계약일련번호 */
        @NotBlank
        String vstMcn,           /* 방문개월수 */
        @NotBlank
        String baseChTcnt,       /* 기준변경차수 */
        String svFeePdDvCd,       /* 서비스수수료상품구분코드 */
        String svFeeBaseAmt,        /* 서비스수수료기준금액 */
        String feeFxamYn,         /* 수수료정액여부 */
        String apyStrtYm,         /* 적용시작년월 */
        String apyEndYm          /* 적용종료년월 */
    ) {}
}
