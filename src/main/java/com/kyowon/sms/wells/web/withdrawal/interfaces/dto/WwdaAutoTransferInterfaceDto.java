package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdaAutoTransferInterfaceDto {

    /* WELLS 자동이체 출금,변경내역, 정보 조회 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchReq")
    public record SearchReq(

        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호
        @JsonProperty("FNT_DV_CD")
        String fntDvCd, // 이체구분코드
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, // 이체구분코드
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, // 계좌카드번호
        @JsonProperty("FNIT_CD")
        String fnitCd, // 금융기관코드
        @JsonProperty("CST_NO")
        String cstNo, // 고객번호
        @JsonProperty("RCP_STRTDT")
        String rctStrtDt, /*접수일자*/
        @JsonProperty("RCP_ENDDT")
        String rctEndDt, /*접수일자*/
        @JsonProperty("RCT_DT")
        String rctDt, /*접수일자*/
        @JsonProperty("CH_RCP_USR_ID")
        String chRcpUsrId, /*변경접수사용자ID*/
        @JsonProperty("EVID_FSH_YN")
        String evidFshYn, /*증빙완료여부*/
        @JsonProperty("OWR_KNM")
        String owrKnm /*소유자한글명*/
    ) {
        public SearchReq {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.enc(acnoCdno) : acnoCdno;
        }
    }

    /* WELLS 자동이체 대상목록 조회 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchObjectReq")
    public record SearchObjectReq(
        @NotBlank
        @JsonProperty("CST_NO")
        String cstNo, /*고객번호*/
        @JsonProperty("EXCD_YN01")
        String excdYn01, /*제외여부01*/
        @JsonProperty("EXCD_YN02")
        String excdYn02, /*제외여부02*/
        @JsonProperty("EXCD_YN03")
        String excdYn03, /*제외여부03*/
        @JsonProperty("EXCD_YN04")
        String excdYn04, /*제외여부04*/
        @JsonProperty("EXCD_YN05")
        String excdYn05, /*제외여부05*/
        @JsonProperty("EXCD_YN06")
        String excdYn06, /*제외여부06*/
        @JsonProperty("EXCD_YN07")
        String excdYn07, /*제외여부07*/
        @JsonProperty("EXCD_YN08")
        String excdYn08, /*제외여부08*/
        @JsonProperty("EXCD_YN09")
        String excdYn09, /*제외여부09*/
        @JsonProperty("EXCD_YN10")
        String excdYn10, /*제외여부10*/
        @JsonProperty("EXCD_YN11")
        String excdYn11 /*제외여부11*/

    ) {}

    /* WELLS 자동이체 출금내역 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchPaymentAndWithdrawalRes")
    public record SearchPaymentAndWithdrawalRes(
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, /*판매유형코드*/
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("FNT_STPL_D")
        String fntStplD, /*이체약정일*/
        @JsonProperty("FNL_BIL_AMT")
        String fnlBilAmt, /*최종청구금액*/
        @JsonProperty("DP_AMT")
        String dpAmt, /*입금금액*/
        @JsonProperty("DP_DT")
        String dpDt, /*입금일자*/
        @JsonProperty("FNIT_NM")
        String fnitNm, /*금융기관명*/
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("AFTN_RS_CD")
        String aftnRsCd, /*자동이체결과코드*/
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm /*자동이체결과명*/
    ) {
        public SearchPaymentAndWithdrawalRes {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.dec(acnoCdno) : acnoCdno;
        }
    }

    /* WELLS 자동이체 변경내역 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchChangeRes")
    public record SearchChangeRes(
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, /*판매유형코드*/
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("FNT_STPL_D")
        String fntStplD, /*이체약정일*/
        @JsonProperty("FNIT_NM")
        String fnitNm, /*금융기관명*/
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("AFTN_RS_CD")
        String aftnRsCd, /*자동이체결과코드*/
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm /*자동이체결과명*/

    ) {
        public SearchChangeRes {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.dec(acnoCdno) : acnoCdno;
        }
    }

    /* WELLS 자동이체 대상 목록 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchObjectRes")
    public record SearchObjectRes(
        @JsonProperty("CST_NO")
        String cstNo, /*고객번호*/
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, /*판매유형코드*/
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("CNTR_DTL_STAT_CD")
        String cntrDtlStatCd, /*계약상세상태코드*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("PD_NM")
        String pdNm, /*상품명*/
        @JsonProperty("MB_NM")
        String mbNm, /*회원명*/
        @JsonProperty("MPNO")
        String mpno, /*휴대전화번호*/
        @JsonProperty("MM_ISTM_AMT")
        String mmIstmAmt, /*월할부금액*/
        @JsonProperty("FNT_DV_CD")
        String fntDvCd, /*이체구분코드*/
        @JsonProperty("FNIT_NM")
        String fnitNm, /*금융기관명*/
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("FNT_STPL_D")
        String fntStplD, /*이체약정일*/
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm, /*자동이체결과명*/
        @JsonProperty("RVE_CRP_CD_NM")
        String rveCrpCdNm, /*수납법인코드명*/
        @JsonProperty("INCMDC_YN")
        String incmdcYn /*소득공제여부*/
    ) {
        public SearchObjectRes {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.dec(acnoCdno) : acnoCdno;
        }
    }

    /* WELLS 자동이체 정보 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchRes")
    public record SearchRes(
        @JsonProperty("FNT_DV_CD")
        String fntDvCd, /*이체구분코드*/
        @JsonProperty("FNT_DV_CD_NM")
        String fntDvCdNm, /*이체구분코드명*/
        @JsonProperty("FNIT_CD")
        String fnitCd, /*금융기관코드*/
        @JsonProperty("FNIT_NM")
        String fnitNm, /*금융기관명*/
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("FNT_STPL_D")
        String fntStplD, /*이체약정일*/
        @JsonProperty("CARD_EXPDT_YM")
        String cardExpdtYm, /*카드유효기간년월*/
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, /*법인격구분코드*/
        @JsonProperty("COPN_DV_DRM_VAL")
        String copnDvDrmVal, /*법인격구분식별값*/
        @JsonProperty("MPNO")
        String mpno, /*휴대전화번호*/
        @JsonProperty("AFTN_RS_CD")
        String aftnRsCd, /*자동이체결과코드*/
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm /*자동이체결과명*/

    ) {
        public SearchRes {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.dec(acnoCdno) : acnoCdno;
        }
    }

    /* WELLS 자동이체 구분(개인/법인) 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchCorporatePersonalityDivisionRes")
    public record SearchCorporatePersonalityDivisionRes(
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, /*법인격구분코드*/
        @JsonProperty("COPN_DV_CD_NM")
        String copnDvCdNm /*법인격구분코드명*/
    ) {}

    /* WELLS 자동이체 은행 목록 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchFinancialInstitutionCodeRes")
    public record SearchFinancialInstitutionCodeRes(
        @JsonProperty("FNIT_CD")
        String fnitCd, /*금융기관코드*/
        @JsonProperty("FNIT_NM")
        String fnitNm /*금융기관코드명*/
    ) {}

    /* EDU 자동이체 묶음 등록 정보 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchBundleInfoRes")
    public record SearchBundleInfoRes(
        @JsonProperty("CST_NO")
        String cstNo, /*고객번호*/
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("PD_NM")
        String pdNm, /*상품명*/
        @JsonProperty("ISTLL_KNM")
        String istllKnm, /*설치자한글명*/
        @JsonProperty("FNT_DV_CD")
        String fntDvCd, /*이체구분코드*/
        @JsonProperty("FNT_DV_CD_NM")
        String fntDvCdNm, /*이체구분코드명*/
        @JsonProperty("FNIT_NM")
        String fnitNm, /*금융기관명*/
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("FNT_STPL_D")
        String fntStplD, /*이체약정일*/
        @JsonProperty("DG_YN")
        String dgYn, /*대표여부(Y/N)*/
        @JsonProperty("BNDL_YN")
        String bndlYn, /*묶음여부(Y/N)*/
        @JsonProperty("DG_CNTR_NO")
        String dgCntrNo, /*대표계약번호*/
        @JsonProperty("DG_CNTR_SN")
        String dgCntrSn, /*대표계약일련번호*/
        @JsonProperty("IST_DT")
        String istDt, /*설치일자*/
        @JsonProperty("MDFC_DT")
        String mdfcDt, /*수정일자*/
        @JsonProperty("FNL_MDFC_USR_ID")
        String fnlMdfcUsrId, /*수정담당자ID*/
        @JsonProperty("MDFC_PSIC_NM")
        String mdfcPsicNm /*수정담당자명*/
    ) {
        public SearchBundleInfoRes {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.dec(acnoCdno) : acnoCdno;
        }
    }

    /* EDU 자동이체 증빙 정보 목록 조회 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchEvidenceInfoRes")
    public record SearchEvidenceInfoRes(
        @JsonProperty("RCT_DT")
        String rctDt, /*접수일자*/
        @JsonProperty("RCT_TM")
        String rctTm, /*접수시간*/
        @JsonProperty("CH_RCP_USR_ID")
        String chRcpUsrId, /*변경접수사용자ID*/
        @JsonProperty("PSIC_NM")
        String psicNm, /*담당자명*/
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("SELL_TP_CD")
        String sellTpCd, /*판매유형코드*/
        @JsonProperty("SELL_TP_CD_NM")
        String sellTpCdNm, /*판매유형코드명*/
        @JsonProperty("PD_NM")
        String pdNm, /*상품명*/
        @JsonProperty("MPNO")
        String mpno, /*휴대전화번호*/
        @JsonProperty("BFCH_FNIT_NM")
        String bfchFnitNm, /*변경전금융기관명*/
        @JsonProperty("BFCH_ACNO_CDNO")
        String bfchAcnoCdno, /*변경전계좌카드번호*/
        @JsonProperty("BFCH_OWR_KNM")
        String bfchOwrKnm, /*변경전소유자한글명*/
        @JsonProperty("AFCH_FNIT_NM")
        String afchFnitNm, /*변경후금융기관명*/
        @JsonProperty("AFCH_ACNO_CDNO")
        String afchAcnoCdno, /*변경후계좌카드번호*/
        @JsonProperty("AFCH_OWR_KNM")
        String afchOwrKnm, /*변경후소유자한글명*/
        @JsonProperty("FNT_STPL_D")
        String fntStplD, /*이체약정일*/
        @JsonProperty("AFTN_EVID_FSH_DT")
        String aftnEvidFshDt, /*자동이체증빙완료일자*/
        @JsonProperty("AFTN_EVID_FSH_YN")
        String aftnEvidFshYn, /*자동이체증빙완료여부*/
        @JsonProperty("FNIT_APR_RS_CD")
        String fnitAprRsCd /*금융기관승인결과코드*/
    ) {
        public SearchEvidenceInfoRes {
            bfchAcnoCdno = StringUtils.isNotEmpty(bfchAcnoCdno) ? DbEncUtil.dec(bfchAcnoCdno) : bfchAcnoCdno;
            afchAcnoCdno = StringUtils.isNotEmpty(afchAcnoCdno) ? DbEncUtil.dec(afchAcnoCdno) : afchAcnoCdno;
        }
    }

    /* Wells 자동이체 일괄 묶음 등록/해제 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SaveReq")
    public record SaveReq(
        @JsonProperty("AFTN_BLK")
        List<SaveBulkRegistrationReleaseReq> bulks, // 일괄등록
        @JsonProperty("AFTN_BNDL")
        List<SaveBundleRegistrationReleaseReq> bundles
    ) {}

    /* Wells 자동이체 일괄 묶음 등록/해제 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SaveBundleRegistrationReleaseReq")
    public record SaveBundleRegistrationReleaseReq(
        @JsonProperty("FNT_DV_CD")
        String fntDvCd, /*이체구분코드(카드/은행)*/
        @JsonProperty("DG_YN")
        String dgYn, /*대표여부(Y/N)*/
        @JsonProperty("DG_CNTR_NO")
        String dgCntrNo, /*대표계약번호*/
        @JsonProperty("DG_CNTR_SN")
        String dgCntrSn, /*대표계약일련번호*/
        @JsonProperty("DG_SELL_TP_CD")
        String dgSellTpCd, /*대표판매유형코드(일시불/렌탈/멤버십/회사/정기배송)*/
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("SELL_TP_CD")
        String sellTpCd /*판매유형코드(일시불/렌탈/멤버십/회사/정기배송)*/
    ) {}

    /* EDU 자동이체 일괄 등록/해제 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SaveBulkRegistrationReleaseReq")
    public record SaveBulkRegistrationReleaseReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("FNT_DV_CD")
        String fntDvCd, /*이체구분코드*/
        @JsonProperty("FNIT_CD")
        String fnitCd, /*금융기관코드*/
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("MPY_BSDT")
        String mpyBsdt, /*납부기준일자*/
        @JsonProperty("CARD_EXPDT_YM")
        String cardExpdtYm, /*카드유효기간년월*/
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, /*법인격구분코드*/
        @JsonProperty("CRP_SPMT_DRM_NM")
        String crpSpmtDrmNm, /*법인추가식별명*/
        @JsonProperty("MPNO")
        String mpno /*휴대전화번호*/

    ) {
        public SaveBulkRegistrationReleaseReq {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.enc(acnoCdno) : acnoCdno;
        }
    }

    /* Wells 자동이체 일괄 묶음 등록/해제 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SaveBundleRegistrationReleaseRes")
    public record SaveBundleRegistrationReleaseRes(
        @JsonProperty("RESL_CD")
        String reslCd, /*(결과코드)(E : 에러, F : 실패, S : 정상)*/
        @JsonProperty("PCS_RSLT_CN")
        String pcsRsltCn /*처리결과내용*/
    ) {}

    /* wells 자동이체 계좌 실명인증 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchRealNameCertificationReq")
    public record SearchRealNameCertificationReq(
        @JsonProperty("BNK_CD")
        String bnkCd, /*은행코드*/
        @JsonProperty("ACNO")
        String acno, /*계좌번호*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd /*생년월일*/

    ) {}

    /* wells 자동이체 계좌 실명인증 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchRealNameCertificationRes")
    public record SearchRealNameCertificationRes(
        @JsonProperty("AC_FNT_RS_CD")
        String acFntRsCd, /*계좌이체결과코드*/
        @JsonProperty("AC_FNT_RS_CD_NM")
        String acFntRsCdNm, /*계좌이체결과코드명*/
        @JsonProperty("OWR_KNM")
        String owrKnm /*소유자한글명*/
    ) {}

    /* wells 자동이체 카드 유효성 검사 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchCardEffectivenessCheckReq")
    public record SearchCardEffectivenessCheckReq(
        @JsonProperty("CRDCD_NO")
        String crdcdNo, /*신용카드번호*/
        @JsonProperty("CARD_EXPDT_YM")
        String cardExpdtYm, /*카드유효기간년월*/
        @JsonProperty("CNTRT_NM")
        String cntrtNm, /*계약자명*/
        @JsonProperty("BRYY_MMDD")
        String bryyMmdd /*생년월일*/

    ) {}

    /* wells 자동이체 카드 유효성 검사 Response Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchCardEffectivenessCheckRes")
    public record SearchCardEffectivenessCheckRes(
        @JsonProperty("CARD_FNT_RS_CD")
        String cardFntRsCd, /*카드이체결과코드*/
        @JsonProperty("CARD_FNT_RS_CD_NM")
        String cardFntRsCdNm, /*카드이체결과코드명*/
        @JsonProperty("CDCO_CD")
        String cdcoCd, /*카드사코드*/
        @JsonProperty("CDCO_NM")
        String cdcoNm /*카드사코드명*/
    ) {}

    /* WELLS 은행계좌유효성체크_SB Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchBankEffectivenessCheckReq")
    public record SearchBankEffectivenessCheckReq(
        @JsonProperty("CNTR_NO")
        String cntrNo, /*계약번호*/
        @JsonProperty("CNTR_SN")
        String cntrSn, /*계약일련번호*/
        @JsonProperty("BNK_CD")
        String bnkCd, /*은행코드*/
        @JsonProperty("ACNO")
        String acno, /*계좌번호*/
        @JsonProperty("COPN_DV_CD")
        String copnDvCd, /*법인격구분코드*/
        @JsonProperty("COPN_DV_DRM_VAL")
        String copnDvDrmVal, /*법인격구분식별값*/
        @JsonProperty("ACHLDR_NM")
        String achldrNm, /*예금주명*/
        @JsonProperty("SYS_DV_CD")
        String sysDvCd, /*시스템구분코드*/
        @JsonProperty("PSIC_ID")
        String psicId, /*담당자ID*/
        @JsonProperty("DEPT_ID")
        String deptId /*부서ID*/

    ) {}

    /* WELLS 은행계좌유효성체크_SB Response Dto */
    @Builder
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchBankEffectivenessCheckRes")
    public record SearchBankEffectivenessCheckRes(
        @JsonProperty("ACHLDR_NM")
        String achldrNm, /*예금주*/
        @JsonProperty("AC_FNT_IMPS_CD")
        String acFntImpsCd, /*계좌이체불능코드*/
        @JsonProperty("AC_FNT_IMPS_CD_NM")
        String acFntImpsCdNm, /*계좌이체불능코드명*/
        @JsonProperty("ERR_CN")
        String errCn, /*오류내용*/
        @JsonProperty("BIL_CRT_STAT_CD")
        String bilCrtStatCd /*청구생성상태코드*/
    ) {}
}
