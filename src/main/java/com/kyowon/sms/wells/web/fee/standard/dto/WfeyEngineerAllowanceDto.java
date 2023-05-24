package com.kyowon.sms.wells.web.fee.standard.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WfeyEngineerAllowanceDto {
    @ApiModel(value = "WfeyEngineerAllowanceDto-SearchAllowanceUnitPriceReq")
    public record SearchAllowanceUnitPriceReq(
            String regionLevelDivideCode,
            String productGroupCode,
            String productGroupDetailCode,
            String currentlyApplyDataYn
    ) {
    }

    @ApiModel(value = "WfeyEngineerAllowanceDto-SearchAllowanceUnitPriceRes")
    public record SearchAllowanceUnitPriceRes(
            String svTpCd,              /* 서비스유형코드  */
            String siteAwAtcCd,         /* 현장수당항목코드 */
            String siteAwGrpCd,         /* 현장수당그룹코드 */
            String pdGrpCd,             /* 상품그룹코드 */
            String rglvlDvCd,           /* 급지구분코드 */
            String apyStrtdt,           /* 적용시작일자 */
            String apyEnddt,            /* 적용종료일자 */
            Integer fuleyAwAmt,          /* 정직원수당금액 */
            Integer rolLyr1TopmrAwAmt,   /* 직무1급수석수당금액 */
            Integer rolLyr1UplrAwAmt,    /* 직무1급상급수당금액 */
            Integer rolLyr1MdlyrAwAmt,   /* 직무1급중급수당금액 */
            Integer rolLyr1LolyrAwAmt,   /* 직무1급하급수당금액 */
            Integer rolL2UplrAwAmt,      /* 직무2급상급수당금액 */
            Integer rolL2MdlyrAwAmt,     /* 직무2급중급수당금액 */
            Integer rolL2LolyrAwAmt,     /* 직무2급하급수당금액 */
            Integer rolL3AwAmt,          /* 직무3급수당금액 */
            Integer crwkAwAmt,           /* 계약직수당금액 */
            Integer indvEntrpAwAmt,      /* 개인사업자수당금액 */
            Integer mngerWkUprc,         /* 매니저작업단가 */
            Integer dsbBaseSn,           /* 지급기준일련번호 */
            String rmkCn,                /* 비고내용 */
            String useYn                 /* 사용여부 */
    ) {
    }

    @ApiModel(value = "WfeyEngineerAllowanceDto-CreateAllowanceUnitPriceReq")
    public record CreateAllowanceUnitPriceReq(
            @NotBlank String svTpCd,              /* 서비스유형코드  */
            @NotBlank String siteAwAtcCd,         /* 현장수당항목코드 */
            String siteAwGrpCd,         /* 현장수당그룹코드 */
            @NotBlank String pdGrpCd,             /* 상품그룹코드 */
            @NotBlank String rglvlDvCd,           /* 급지구분코드 */
            @NotBlank String apyStrtdt,           /* 적용시작일자 */
            @NotBlank String apyEnddt,            /* 적용종료일자 */
            Integer fuleyAwAmt,          /* 정직원수당금액 */
            Integer rolLyr1TopmrAwAmt,   /* 직무1급수석수당금액 */
            Integer rolLyr1UplrAwAmt,    /* 직무1급상급수당금액 */
            Integer rolLyr1MdlyrAwAmt,   /* 직무1급중급수당금액 */
            Integer rolLyr1LolyrAwAmt,   /* 직무1급하급수당금액 */
            Integer rolL2UplrAwAmt,      /* 직무2급상급수당금액 */
            Integer rolL2MdlyrAwAmt,     /* 직무2급중급수당금액 */
            Integer rolL2LolyrAwAmt,     /* 직무2급하급수당금액 */
            Integer rolL3AwAmt,          /* 직무3급수당금액 */
            Integer crwkAwAmt,           /* 계약직수당금액 */
            Integer indvEntrpAwAmt,      /* 개인사업자수당금액 */
            Integer mngerWkUprc,         /* 매니저작업단가 */
            String rmkCn,                /* 비고내용 */
            String useYn                 /* 사용여부 */
    ) {
    }

    @ApiModel(value = "WfeyEngineerAllowanceDto-EditAllowanceUnitPriceReq")
    public record EditAllowanceUnitPriceReq(
            @NotBlank String svTpCd,              /* 서비스유형코드  */
            @NotBlank String siteAwAtcCd,         /* 현장수당항목코드 */
            String siteAwGrpCd,         /* 현장수당그룹코드 */
            @NotBlank String pdGrpCd,             /* 상품그룹코드 */
            @NotBlank String rglvlDvCd,           /* 급지구분코드 */
            String apyStrtdt,           /* 적용시작일자 */
            String apyEnddt,            /* 적용종료일자 */
            Integer fuleyAwAmt,          /* 정직원수당금액 */
            Integer rolLyr1TopmrAwAmt,   /* 직무1급수석수당금액 */
            Integer rolLyr1UplrAwAmt,    /* 직무1급상급수당금액 */
            Integer rolLyr1MdlyrAwAmt,   /* 직무1급중급수당금액 */
            Integer rolLyr1LolyrAwAmt,   /* 직무1급하급수당금액 */
            Integer rolL2UplrAwAmt,      /* 직무2급상급수당금액 */
            Integer rolL2MdlyrAwAmt,     /* 직무2급중급수당금액 */
            Integer rolL2LolyrAwAmt,     /* 직무2급하급수당금액 */
            Integer rolL3AwAmt,          /* 직무3급수당금액 */
            Integer crwkAwAmt,           /* 계약직수당금액 */
            Integer indvEntrpAwAmt,      /* 개인사업자수당금액 */
            Integer mngerWkUprc,         /* 매니저작업단가 */
            @NotBlank Integer dsbBaseSn,           /* 지급기준일련번호 */
            String rmkCn,                /* 비고내용 */
            String useYn                 /* 사용여부 */
    ) {
    }

    @ApiModel(value = "WfeyEngineerAllowanceDto-RemoveAllowanceUnitPriceReq")
    public record RemoveAllowanceUnitPriceReq(
            @NotBlank String svTpCd,              /* 서비스유형코드  */
            @NotBlank String siteAwAtcCd,         /* 현장수당항목코드 */
            @NotBlank String pdGrpCd,             /* 상품그룹코드 */
            @NotBlank String rglvlDvCd,           /* 급지구분코드 */
            @NotBlank Integer dsbBaseSn           /* 지급기준일련번호 */
    ) {
    }
}
