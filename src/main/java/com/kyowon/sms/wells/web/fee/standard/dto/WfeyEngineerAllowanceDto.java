package com.kyowon.sms.wells.web.fee.standard.dto;

public class WfeyEngineerAllowanceDto {
    public record SearchAllowanceUnitPriceReq(
            String regionLevelDivideCode,
            String productGroupCode,
            String productGroupDetailCode,
            String currentlyApplyDataYn
    ) {
    }

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
            Integer dsbBaseSn            /* 지급기준일련번호 */
    ) {
    }
}
