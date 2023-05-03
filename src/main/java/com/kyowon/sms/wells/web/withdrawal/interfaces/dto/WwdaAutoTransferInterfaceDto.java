package com.kyowon.sms.wells.web.withdrawal.interfaces.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.annotation.DBDecField;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

public class WwdaAutoTransferInterfaceDto {

    /* EDU 자동이체 출금,변경내역, 정보 조회 Request Dto */
    @ApiModel("WwdaAutoTransferInterfaceDto-SearchReq")
    public record SearchReq(
        @NotBlank
        @JsonProperty("CNTR_NO")
        String cntrNo, // 계약번호
        @NotBlank
        @JsonProperty("CNTR_SN")
        String cntrSn, // 계약일련번호

        @JsonProperty("FNT_DV_CD")
        String fntDvCd, // 이체구분코드

        @JsonProperty("ACNO_CDNO")
        String acnoCdno // 계좌카드번호
    ) {
        public SearchReq {
            acnoCdno = StringUtils.isNotEmpty(acnoCdno) ? DbEncUtil.enc(acnoCdno) : acnoCdno;
        }
    }

    /* EDU 자동이체 대상목록 조회 Request Dto */
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

    /* EDU 자동이체 출금내역 조회 Response Dto */
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
        @DBDecField
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("AFTN_RS_CD")
        String aftnRsCd, /*자동이체결과코드*/
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm /*자동이체결과명*/
    ) {}

    /* EDU 자동이체 변경내역 조회 Response Dto */
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
        @DBDecField
        @JsonProperty("ACNO_CDNO")
        String acnoCdno, /*계좌카드번호*/
        @JsonProperty("OWR_KNM")
        String owrKnm, /*소유자한글명*/
        @JsonProperty("AFTN_RS_CD")
        String aftnRsCd, /*자동이체결과코드*/
        @JsonProperty("AFTN_RS_NM")
        String aftnRsNm /*자동이체결과명*/

    ) {}

    /* EDU 자동이체 대상 목록 Response Dto */
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
        @DBDecField
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
    ) {}

    /* EDU 자동이체 정보 조회 Response Dto */
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
        @DBDecField
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

    ) {}
}
