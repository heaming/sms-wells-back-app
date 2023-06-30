package com.kyowon.sms.wells.web.product.manage.dto;

import io.swagger.annotations.ApiModel;

public class WpdcAsPartCommonUseMgtDto {

    /**
     * AS부품 목록 Search Request Dto
     * @param prdtCateHigh
     * @param prdtCateMid
     * @param prdtCateLow
     * @param asMatItmKndCd
     * @param asMatItmGrpCd
     * @param svMatGrpCd
     * @param pdCd
     * @param sapMatCd
     * @param sapItemCdFrom
     * @param sapItemCdTo
     */
    @ApiModel("WpdcAsPartCommonUseMgtDto-SearchPartReq")
    public record SearchPartReq(
        String prdtCateHigh,    /* 상품대분류ID */
        String prdtCateMid,     /* 상품중분류ID */
        String prdtCateLow,     /* 상품소분류ID */
        String asMatItmKndCd,   /* 품목종류코드 */
        String asMatItmGrpCd,   /* 품목그룹코드 */
        String svMatGrpCd,      /* 자재그룹코드 */
        String pdCd,            /* 제품코드 */
        String sapMatCd,        /* 자재코드 */
        String sapItemCdFrom,   /* 시작품목코드 */
        String sapItemCdTo      /* 종료품목코드 */
    ) {
    }

    /**
     * AS부품 목록 Search Result Dto
     * @param partPdCd
     * @param partPdNm
     * @param asMatMngTpCd
     * @param asMatMngTpNm
     * @param sapMatCd
     * @param asItemCd
     * @param asMatItmKndCd
     * @param asMatItmKndNm
     * @param asMatItmGrpCd
     * @param asMatItmGrpNm
     * @param svMatGrpCd
     * @param svMatGrpNm
     * @param asMatCmnClsfCd
     * @param asMatCmnClsfNm
     * @param ordnyHvMatYn
     * @param trnovrRtOjYn
     */
    @ApiModel("WpdcAsPartCommonUseMgtDto-SearchPartRes")
    public record SearchPartRes(
        String partPdCd,            /* 부품코드 */
        String partPdNm,            /* 부품명 */
        String asMatMngTpCd,        /* 관리유형코드 */
        String asMatMngTpNm,        /* 관리유형명 */
        String sapMatCd,            /* 자재코드 */
        String asItemCd,            /* 품목코드 */
        String asMatItmKndCd,       /* 품목종류 */
        String asMatItmKndNm,       /* 품목종류명 */
        String asMatItmGrpCd,       /* 품목그룹 */
        String asMatItmGrpNm,       /* 품목그룹명 */
        String svMatGrpCd,          /* 자재그룹코드 */
        String svMatGrpNm,          /* 자재그룹명 */
        String asMatCmnClsfCd,      /* AS자재 공통분류코드 */
        String asMatCmnClsfNm,      /* AS자재 공통분류명 */
        String ordnyHvMatYn,        /* 상시보유자재여부 */
        String trnovrRtOjYn         /* 회전율 대상여부 */
    ) {
    }


    /**
     * AS부품 관련 제품 목록 Search Result Dto
     * @param pdCd
     * @param pdNm
     * @param sapMatCd
     * @param asItemCd
     */
    @ApiModel("WpdcAsPartCommonUseMgtDto-SearchProductRes")
    public record SearchProductRes(
        String pdCd,                /* 제품코드 */
        String pdNm,                /* 제품명 */
        String sapMatCd,            /* 자재코드 */
        String asItemCd             /* 품목코드 */
    ) {
    }
}
