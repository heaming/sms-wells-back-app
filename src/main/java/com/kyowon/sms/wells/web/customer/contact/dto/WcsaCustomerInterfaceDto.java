package com.kyowon.sms.wells.web.customer.contact.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CsCustomerConst;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 고객 인터페이스 관리 Dto
 * </pre>
 *
 * @author jeongeon.kim
 * @since 2023-02-01
 */
public class WcsaCustomerInterfaceDto {

    /**
     * 고객번호 기준으로 고객정보를 조회 (서비스) Search Request Dto
     * @param cstNo 고객번호
     */
    @ApiModel(value = "EcsaCustomerInterfaceDto-SearchCustomerInfoReq")
    public record SearchCustomerInfoReq(

        @JsonProperty("CST_NO")
        String cstNo /* 고객번호 */
    ) {}

    /**
     * 고객번호 기준으로 고객정보를 조회 (IF정보 : EAI_ECUI1038_EDU 고객정보 조회) Search Result Dto
     * @param cstNo 고객번호
     * @param itgCstNo 통합고객번호
     * @param cikVal ci키값
     * @param sfkVal 세이프키값
     * @param copnDvCd 법인격구분코드
     * @param copnDvCdNm 법인격구분코드명
     * @param cstInflwPhDvCd 고객유입경로코드
     * @param cstInflwPhDvCdNm 고객유입경로코드명
     * @param cstKnm 고객한글명
     * @param lnfDvCd 내외국인구분코드
     * @param lnfDvCdNm 내외국인구분코드명
     * @param bryyMmdd 생년월일
     * @param sexDvCd 성별구분코드
     * @param sexDvCdNm 성별구분코드명
     * @param emadr 이메일주소
     * @param bzrno 사업자등록번호
     * @param crpSpmtDrmNm 법인점포명
     * @param locaraTno 지역전화번호
     * @param exno 전화국번호
     * @param idvTno 개별전화번호
     * @param cralLocaraTno 휴대지역전화번호
     * @param mexno 휴대전화국번호
     * @param cralIdvTno 휴대개별전화번호
     * @param adrId 주소ID
     * @param zip 우편번호
     * @param basAdr 기본주소
     * @param dtlAdr 상세주소
     * @param prtnrNo 파트너번호
     * @param sapEmpno sap사원번호
     * @param fmlDscOjYn 가족할인대상여부
     * @param hsCtfYn 본인인증여부
     * @param empYn 직원여부
     * @param cstGdCd 고객등급코드
     * @param nusdRsonCd 미사용사유코드
     * @param dtaDlYn 데이터삭제여부
     * @param fstRgstDtm 최초등록일시
     * @param fnlMdfcDtm 최종수정일시
     * @param rsCd 결과코드
     * @param rsMsg 결과메시지
     */
    @ApiModel(value = "EcsaCustomerInterfaceDto-SearchCustomerRes")
    public record SearchCustomerRes(

        @JsonProperty("CST_NO")
        String cstNo,

        @JsonProperty("ITG_CST_NO")
        String itgCstNo,

        @JsonProperty("CIK_VAL")
        String cikVal,

        @JsonProperty("SFK_VAL")
        String sfkVal,

        @JsonProperty("COPN_DV_CD")
        String copnDvCd,

        @JsonProperty("COPN_DV_CD_NM")
        String copnDvCdNm,

        @JsonProperty("CST_INFLW_PH_DV_CD")
        String cstInflwPhDvCd,

        @JsonProperty("CST_INFLW_PH_DV_CD_NM")
        String cstInflwPhDvCdNm,

        @JsonProperty("CST_KNM")
        String cstKnm,

        @JsonProperty("LNF_DV_CD")
        String lnfDvCd,

        @JsonProperty("LNF_DV_CD_NM")
        String lnfDvCdNm,

        @JsonProperty("BRYY_MMDD")
        String bryyMmdd,

        @JsonProperty("SEX_DV_CD")
        String sexDvCd,

        @JsonProperty("SEX_DV_CD_NM")
        String sexDvCdNm,

        @JsonProperty("EMADR")
        String emadr,

        @JsonProperty("BZRNO")
        String bzrno,

        @JsonProperty("CRP_SPMT_DRM_NM")
        String crpSpmtDrmNm,

        @JsonProperty("LOCARA_TNO")
        String locaraTno,

        @JsonProperty("EXNO")
        String exno,

        @JsonProperty("IDV_TNO")
        String idvTno,

        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno,

        @JsonProperty("MEXNO")
        String mexno,

        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno,

        @JsonProperty("ADR_ID")
        String adrId,

        @JsonProperty("ZIP")
        String zip,

        @JsonProperty("BAS_ADR")
        String basAdr,

        @JsonProperty("DTL_ADR")
        String dtlAdr,

        @JsonProperty("PRTNR_NO")
        String prtnrNo,

        @JsonProperty("SAP_EMPNO")
        String sapEmpno,

        @JsonProperty("FML_DSC_OJ_YN")
        String fmlDscOjYn,

        @JsonProperty("HS_CTF_YN")
        String hsCtfYn,

        @JsonProperty("EMP_YN")
        String empYn,

        @JsonProperty("CST_GD_CD")
        String cstGdCd,

        @JsonProperty("NUSD_RSON_CD")
        String nusdRsonCd,

        @JsonProperty("DTA_DL_YN")
        String dtaDlYn,

        @JsonProperty("FST_RGST_DTM")
        String fstRgstDtm,

        @JsonProperty("FNL_MDFC_DTM")
        String fnlMdfcDtm,

        @JsonProperty("RS_CD")
        String rsCd,

        @JsonProperty("RS_MSG")
        String rsMsg
    ) {
        public SearchCustomerRes {
            exno = StringUtils.isNotEmpty(exno) ? DbEncUtil.dec(exno) : exno;
            mexno = StringUtils.isNotEmpty(mexno) ? DbEncUtil.dec(mexno) : mexno;
        }
    }

    /**
     * 고객번호 기준으로 고객정보를 변경 (서비스) Search Request Dto
     * @param cstNo 고객번호
     * @param calngDvCd 호출구분코드
     * @param copnDvCd 법인격구분코드
     * @param cstNo 고객번호
     * @param locaraTno 지역전화번호
     * @param exno 전화국번호
     * @param idvTno 개별전화번호
     * @param cralLocaraTno 휴대전화지역
     * @param mexno 휴대전화국번호
     * @param cralIdvTno 휴대전화번호
     * @param adrId 주소ID
     * @param adrDvCd 주소구분코드
     * @param chLtrqConfYn 변경요청서확인여부
     * @param chLtrqConfDt 변경요청서확인일자
     * @param unuitmCn 특이사항내용
     * @param rgstMdfcUsrId 등록수정사용자ID
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerInfoEditReq")
    public record SearchCustomerInfoEditReq(
        @JsonProperty("CALNG_DV_CD")
        String calngDvCd, /*호출구분코드*/
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, /*법인격구분코드*/
        @JsonProperty("CST_NO")
        String cstNo, /*고객번호 */
        @JsonProperty("LOCARA_TNO")
        String locaraTno, /*지역전화번호*/
        @JsonProperty("EXNO")
        String exno, /*전화국번호*/
        @JsonProperty("IDV_TNO")
        String idvTno, /*개별전화번호*/
        @JsonProperty("CRAL_LOCARA_TNO")
        String cralLocaraTno, /*휴대전화지역*/
        @JsonProperty("MEXNO")
        String mexno, /*휴대전화국번호*/
        @JsonProperty("CRAL_IDV_TNO")
        String cralIdvTno, /*휴대전화번호*/
        @JsonProperty("ADR_ID")
        String adrId, /*주소ID*/
        @JsonProperty("ADR_DV_CD")
        String adrDvCd, /*주소구분코드*/
        @JsonProperty("CH_LTRQ_CONF_YN")
        String chLtrqConfYn, /*변경요청서확인여부*/
        @JsonProperty("CH_LTRQ_CONF_DT")
        String chLtrqConfDt, /*변경요청서확인일자*/
        @JsonProperty("UNUITM_CN")
        String unuitmCn, /*특이사항내용*/
        @JsonProperty("RGST_MDFC_USR_ID")
        String rgstMdfcUsrId /*등록수정사용자ID*/
    ) {
        public SearchCustomerInfoEditReq {
            exno = StringUtils.isNotEmpty(exno) ? DbEncUtil.enc(exno) : exno;
            mexno = StringUtils.isNotEmpty(mexno) ? DbEncUtil.enc(mexno) : mexno;
        }
    }

    /**
     * 고객번호 기준으로 고객정보를 조회 (IF정보 : EAI_ECUI1038_EDU 고객정보 조회) Search Result Dto
     * @param cstNo 고객번호
     * @param rsCd 결과코드
     * @param rsMsg 결과메시지
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerInfoEditRes")
    public record SearchCustomerInfoEditRes(
        @JsonProperty("CST_NO")
        String cstNo,
        @JsonProperty("RS_CD")
        String rsCd,
        @JsonProperty("RS_MSG")
        String rsMsg
    ) {}

    /**
     * 고객 약관동의 정보 저장 Req
     * @param wkDv
     * @param cstNo
     * @param agWdwalDtm
     * @param prvCn
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SaveCustomerAgreementReq")
    public record SaveCustomerAgreementReq(
        @NotBlank
        @JsonProperty("WK_DV")
        String wkDv, /* 작업구분 (01 : 약관동의변경, 02 : 회원탈퇴) */
        @NotBlank
        @JsonProperty("CST_NO")
        String cstNo, /* 고객번호 */
        @JsonProperty("AG_WDWAL_DTM")
        String agWdwalDtm, /* 동의탈퇴일시 */
        @NotBlank
        @JsonProperty("PRV_CN")
        String prvCn, /* 약관내용 */
        Map<String, String> agAtcDvCdMap /* 동의항목구분코드 */
    ) {

        // 동의항목구분코드 Info
        public static final String[] AG_ATC_DV_CD_ARRAY = new String[] {
            "101" // 이용약관
            , "102" // 개인정보 수집 및 이용 동의
            , "103" // 마케팅 목적 처리 동의서
            , "105" // 개인정보 제3자 제공 동의
            , "107" // 교원그룹 통합 마케팅 목적 수집 이용 및 광고성 정보 수신 동의
        };

        public SaveCustomerAgreementReq {
            String[] prvCnArray = prvCn.trim().split(CsCustomerConst.IF_PRV_CN_SPLIT_CHAR);
            if (prvCnArray.length > 0) {
                agAtcDvCdMap = new HashMap<>();
                for (int i = 0; i < prvCnArray.length; i++) {
                    if (i >= AG_ATC_DV_CD_ARRAY.length)
                        break;
                    if (StringUtils.equals(prvCnArray[i], "Y") || StringUtils.equals(prvCnArray[i], "N")) {
                        agAtcDvCdMap.put(AG_ATC_DV_CD_ARRAY[i], prvCnArray[i]);
                    }
                }
            }
        }
    }

    /**
     * 고객 약관동의 정보 저장 Res
     * @param rsStat
     * @param rsMsg
     * @param rsCd
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SaveCustomerAgreementRes")
    public record SaveCustomerAgreementRes(
        @JsonProperty("RS_STAT")
        Boolean rsStat, /* 결과상태 (true/false) */
        @JsonProperty("RS_MSG")
        String rsMsg, /* 결과메세지 (에러의 경우만 에러 메시지) */
        @JsonProperty("RS_CD")
        String rsCd /* 결과코드 (0000 : 정상, 9999 : 시스템 오류) */
    ) {}

}
