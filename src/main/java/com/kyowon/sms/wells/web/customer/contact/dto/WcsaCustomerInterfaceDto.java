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
     * @param CST_NO 고객번호
     */
    @ApiModel(value = "WcsaCustomerInterfaceDto-SearchCustomerInfoReq")
    public record SearchCustomerInfoReq(
        String CST_NO /* 고객번호 */
    ) {}

    /**
     * 고객번호 기준으로 고객정보를 조회 (IF정보 : EAI_WCUI1011_WELLS 고객정보 조회) Search Result Dto
     * @param CST_NO 고객번호
     * @param ITG_CST_NO 통합고객번호
     * @param CIK_VAL ci키값
     * @param SFK_VAL 세이프키값
     * @param COPN_DV_CD 법인격구분코드
     * @param COPN_DV_CD_NM 법인격구분코드명
     * @param CST_INFLW_PH_DV_CD 고객유입경로코드
     * @param CST_INFLW_PH_DV_CD_NM 고객유입경로코드명
     * @param CST_KNM 고객한글명
     * @param LNF_DV_CD 내외국인구분코드
     * @param LNF_DV_CD_NM 내외국인구분코드명
     * @param BRYY_MMDD 생년월일
     * @param SEX_DV_CD 성별구분코드
     * @param SEX_DV_CD_NM 성별구분코드명
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
        String COPN_DV_CD_NM,
        String CST_INFLW_PH_DV_CD,
        String CST_INFLW_PH_DV_CD_NM,
        String CST_KNM,
        String LNF_DV_CD,
        String LNF_DV_CD_NM,
        String BRYY_MMDD,
        String SEX_DV_CD,
        String SEX_DV_CD_NM,
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
