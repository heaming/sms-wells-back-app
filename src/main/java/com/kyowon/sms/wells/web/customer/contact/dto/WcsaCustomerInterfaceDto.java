package com.kyowon.sms.wells.web.customer.contact.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WcsaCustomerInterfaceDto {

    /**
     * 고객번호 기준으로 고객정보를 조회 (서비스) Search Request Dto
     * @param cstNo 고객번호
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerInfoReq")
    public record SearchCustomerInfoReq(
        String cstNo /* 고객번호 */
    ) {}

    /**
     * 고객번호 기준으로 고객정보를 조회 (IF정보 : EAI_WCUI1011_WELLS 고객정보 조회) Search Result Dto
     * @param CST_NO 고객번호
     * @param ITG_CST_NO 통합고객번호
     * @param CIK_VAL ci키값
     * @param SFK_VAL 세이프키값
     * @param COPN_DV_CD 법인격구분코드
     * @param CST_INFLW_PH_DV_CD 고객유입경로코드
     * @param CST_KNM 고객한글명
     * @param LNF_DV_CD 내외국인구분코드
     * @param BRYY_MMDD 생년월일
     * @param SEX_DV_CD 성별구분코드
     * @param EMADR 이메일주소
     * @param BZRNO 사업자등록번호
     * @param CRP_SPMT_DRM_NM 법인점포명
     * @param LOCARA_TNO 지역전화번호
     * @param EXNO 전화국번호
     * @param IDV_TNO 개별전화번호
     * @param CRAL_LOCARA_TNO 휴대지역전화번호
     * @param MEXNO 휴대전화국번호
     * @param CRAL_IDV_TNO 휴대개별전화번호
     * @param ADR_ID 주소ID
     * @param ZIP 우편번호
     * @param BAS_ADR 기본주소
     * @param DTL_ADR 상세주소
     * @param PRTNR_NO 파트너번호
     * @param SAP_EMPNO sap사원번호
     * @param FML_DSC_OJ_YN 가족할인대상여부
     * @param HS_CTF_YN 본인인증여부
     * @param EMP_YN 직원여부
     * @param CST_GD_CD 고객등급코드
     * @param NUSD_RSON_CD 미사용사유코드
     * @param DTA_DL_YN 데이터삭제여부
     * @param FST_RGST_DTM 최초등록일시
     * @param FNL_MDFC_DTM 최종수정일시
     * @param RS_CD 결과코드
     * @param RS_MSG 결과메시지
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerRes")
    public record SearchCustomerRes(
        String CST_NO,
        String ITG_CST_NO,
        String CIK_VAL,
        String SFK_VAL,
        String COPN_DV_CD,
        String CST_INFLW_PH_DV_CD,
        String CST_KNM,
        String LNF_DV_CD,
        String BRYY_MMDD,
        String SEX_DV_CD,
        String EMADR,
        String BZRNO,
        String CRP_SPMT_DRM_NM,
        String LOCARA_TNO,
        String EXNO,
        String IDV_TNO,
        String CRAL_LOCARA_TNO,
        String MEXNO,
        String CRAL_IDV_TNO,
        String ADR_ID,
        String ZIP,
        String BAS_ADR,
        String DTL_ADR,
        String PRTNR_NO,
        String SAP_EMPNO,
        String FML_DSC_OJ_YN,
        String HS_CTF_YN,
        String EMP_YN,
        String CST_GD_CD,
        String NUSD_RSON_CD,
        String DTA_DL_YN,
        String FST_RGST_DTM,
        String FNL_MDFC_DTM,
        String RS_CD,
        String RS_MSG
    ) {
        public SearchCustomerRes {
            EXNO = StringUtils.isNotEmpty(EXNO) ? DbEncUtil.dec(EXNO) : EXNO;
            MEXNO = StringUtils.isNotEmpty(MEXNO) ? DbEncUtil.dec(MEXNO) : MEXNO;
        }
    }

}
