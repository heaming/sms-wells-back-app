package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * 회사설치 (8888코드) 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-05-24
 */
public class WsncCompanyIstStateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncCompanyIstStateDto-SearchMainReq")
    public record SearchAllReq(
        @NotBlank
        String mgtYnm
    ) {}

    @ApiModel(value = "WsncCompanyIstStateDto-SearchPsReq")
    public record SearchReq(
        @NotBlank
        String mgtYnm,

        String mgtTyp,

        @NotBlank
        String istDtFrom,

        @NotBlank
        String istDtTo
    ) {}

    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WsncCompanyIstStateDto-SearchAllRes")
    public record SearchAllRes(
        String cntrNo,
        String deptCd,
        String deptNm1,
        String csnrCd,
        String ogNm,
        String custNm,
        String copnDvCd,
        String pdCd, // 상품코드 -
        String pdNm, // 상품명 -
        String svBizMclsfCd, // 작업그룹
        String svBizDclsfCd, // 작업유형상세
        // 작업유형명 BFSVC_WK_DV_CD
        String sapMatCd, // SAP코드 -
        String partPdCd, // 품목코드
        String partPdNm, // 부품명
        Long useQty, // 사용수량
        Long pdctUprc, // 실제원가 -
        Long pdctUprcSum, // 원가합계금액
        Long csmrUprcAmt, // 소비자가 csmrUprcAmt
        Long csmrUprcAmtSum, // 소비자가합계급액
        String refriDvCd, // 유무상
        String wrkPrtnrClsfCd, // 작업자 구분
        String wrkPrtnrNo, // 작업자 사번
        String wrkPrtnrKnm, // 작업자 성명
        String wrkOgNm, // 작업자 소속
        String cstAdr, // 고객주소 상세
        String pdClsfNm // 상품분류명
    ) {}

    @ApiModel(value = "WsncCompanyIstStateDto-SearchFltrRes")
    public record SearchFltrRes(
        String cntrNo,
        String deptCd,
        String deptNm1,
        String csnrCd,
        String ogNm,
        String custNm,
        String copnDvCd,
        String pdCd, // 상품코드 -
        String pdNm, // 상품명 -
        String svBizMclsfCd, // 작업그룹
        String svBizDclsfCd, // 작업유형상세
        // 작업유형명 BFSVC_WK_DV_CD
        String sapMatCd, // SAP코드 -
        String partPdCd, // 품목코드
        String partPdNm, // 부품명
        Long useQty, // 사용수량
        Long pdctUprc, // 실제원가 -
        Long pdctUprcSum, // 원가합계금액
        Long csmrUprcAmt, // 소비자가 csmrUprcAmt
        Long csmrUprcAmtSum, // 소비자가합계급액
        String refriDvCd, // 유무상
        String wrkPrtnrClsfCd, // 작업자 구분
        String wrkPrtnrNo, // 작업자 사번
        String wrkPrtnrKnm, // 작업자 성명
        String wrkOgNm, // 작업자 소속
        String cstAdr// 고객주소 상세 TB_SVST_SV_WK_OSTR_IZ
    ) {}

    @ApiModel(value = "WsncCompanyIstStateDto-SearchPsRes")
    public record SearchPsRes(
        String cntrNo,
        String deptCd,
        String deptNm1,
        String csnrCd,
        String ogNm,
        String custNm,
        String copnDvCd,
        String pdCd, // 상품코드 -
        String pdNm, // 상품명 -
        String svBizMclsfCd, // 작업그룹
        String svBizDclsfCd, // 작업유형상세
        // 작업유형명 BFSVC_WK_DV_CD
        String sapMatCd, // SAP코드 -
        String partPdCd, // 품목코드
        String partPdNm, // 부품명
        Long useQty, // 사용수량
        Long pdctUprc, // 실제원가 -
        Long pdctUprcSum, // 원가합계금액
        Long csmrUprcAmt, // 소비자가 csmrUprcAmt
        Long csmrUprcAmtSum, // 소비자가합계급액
        String refriDvCd, // 유무상
        String wrkPrtnrClsfCd, // 작업자 구분
        String wrkPrtnrNo, // 작업자 사번
        String wrkPrtnrKnm, // 작업자 성명
        String wrkOgNm, // 작업자 소속
        String cstAdr// 고객주소 상세 TB_SVST_SV_WK_OSTR_IZ
    ) {}

    @ApiModel(value = "WsncCompanyIstStateDto-SearchSubMatRes")
    public record SearchSubMatRes(
        String cntrNo,
        String deptCd,
        String deptNm1,
        String csnrCd,
        String ogNm,
        String custNm,
        String copnDvCd,
        String pdCd, // 상품코드 -
        String pdNm, // 상품명 -
        String svBizMclsfCd, // 작업그룹
        String svBizDclsfCd, // 작업유형상세
        // 작업유형명 BFSVC_WK_DV_CD
        String sapMatCd, // SAP코드 -
        String partPdCd, // 품목코드
        String partPdNm, // 부품명
        Long useQty, // 사용수량
        Long pdctUprc, // 실제원가 -
        Long pdctUprcSum, // 원가합계금액
        Long csmrUprcAmt, // 소비자가 csmrUprcAmt
        Long csmrUprcAmtSum, // 소비자가합계급액
        String refriDvCd, // 유무상
        String wrkPrtnrClsfCd, // 작업자 구분
        String wrkPrtnrNo, // 작업자 사번
        String wrkPrtnrKnm, // 작업자 성명
        String wrkOgNm, // 작업자 소속
        String cstAdr// 고객주소 상세 TB_SVST_SV_WK_OSTR_IZ
    ) {}
}
