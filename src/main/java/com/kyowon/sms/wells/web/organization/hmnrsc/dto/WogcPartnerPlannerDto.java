package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WogcPartnerPlannerDto {

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseReq")
    @Builder
    public record SearchLicenseReq(
        String prtnrKnm, // 파트너한글명
        String prtnrNo, // 파트너번호
        List<String> qlfDvCd // 자격구분코드
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseRes")
    public record SearchLicenseRes(
        String dgr1LevlOgNm, // 총괄단
        String dgr2LevlOgNm, // 지역단
        String ogCd, // 조직코드
        String bldNm, // 빌딩명
        String ogId, // 조직ID
        String ogTpCd, // 조직유형코드
        String prtnrNo, // 파트너번호
        String prtnrKnm, // 파트너한글명
        String usrId, // 사용자ID
        String rsbDvCd, // 직책코드
        String rsbDvNm, // 직책명
        String cralLocaraTno, // 휴대지역전화번호
        String mexnoEncr, // 휴대전화국번호암호화
        String cralIdvTno, // 휴대개별전화번호
        String bizUseIdvTno, // 업무사용개별전화번호
        String bizUseExnoEncr, // 업무사용전화국번호암호화
        String bizUseLocaraTno, // 업무사용지역전화번호
        String bryyMmdd, // 생년월일
        String cntrDt, // 계약일자(리쿠르팅 일자)
        String fnlCltnDt, // 최종해약일자
        String edu143, // Pre스타트업
        String edu96, // 스타트업
        String qlfDvCd, // 자격구분코드
        String qlfDvNm // 자격구분코드명
    ) {
        public SearchLicenseRes {
            // 업무사용전화국번호암호화 복호화처리
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            // 업무사용전화국번호암호화 복호화처리
            bizUseExnoEncr = StringUtils.isNotEmpty(bizUseExnoEncr) ? DbEncUtil.dec(bizUseExnoEncr) : bizUseExnoEncr;
        }
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseDetailRes")
    public record SearchLicenseDetailRes(
        String ogTpCd,
        String prtnrNo,
        String ogId,
        String qlfDvCd,
        String qlfDvNm,
        String qlfAplcDvCd,
        String qlfAplcDvNm,
        String strtdt,
        String cvDt,
        String enddt,
        String pymdt,
        String dsbAmt,
        String cntrDt,
        String prtnrCntrTpCd,
        String pcpOgTpCd,
        String pcpPrtnrNo,
        String pcpPrtnrKnm,
        String prcsdt
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchReq")
    @Builder
    public record SearchReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String mOgYn,
        String baseYm,
        String mngtYm,
        String ogId,
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3,
        String ogLevlDvCd4,
        String olfDvCd
    ) {
        public SearchReq {
            if (StringUtils.isNotEmpty(mngtYm)) {
                baseYm = mngtYm;
            }
        }
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCheckReq")
    @Builder
    public record SearchCheckReq(
        String mngtYm

    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchRes")
    public record SearchRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String ogCd,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String qlfDvCd,
        String qlfDvNm,
        String rcmdrPrtnrNm,
        String rcmdrPrtnrNo,
        String cntrDt,
        String fnlCltnDt,
        String rcntrDt,
        String edu14,
        String twoSum,
        String curSum,
        String mTotSum,
        String curBs,
        String totSum,
        String prfmtDt,
        String dmtnDt,
        String dmtnCurBs,
        String dmtnTotSum,
        String strtdt,
        String fnlEnddt,
        String cvDt,
        String enddt,
        String mQlfDvCd,
        String mQlfDvNm,
        String btnYn,
        String mTotCnt
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-DeleteReq")
    @Builder
    public record DeleteReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String aplcSn
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SaveReq")
    @Builder
    public record SaveReq(
        String mngtYm,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,

        String qlfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCountMmPartnerReq")
    @Builder
    public record SearchCountMmPartnerReq(
        String mngtYm,
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String qlfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchCountPlarPartnerReq")
    @Builder
    public record SearchCountPlarPartnerReq(
        String ogTpCd,
        String prtnrKnm,
        String prtnrNo,
        String olfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-FindRes")
    public record FindRes(
        String mngtYm,
        String prtnrNo,
        String prtnrKnm,
        String upgrYm,
        String dmtnYm,
        String qlfDvCd,
        String rfdt,
        String cntrDt,
        String cltnDt,
        String upgrDmtnDvCd,
        String rcntrDt,
        String upgrMcn,
        String rgrOgTpCd,
        String rgstPrtnrKnm,
        String rgrPrtnrNo,
        String rgstDt,
        String udrOgTpCd,
        String mdfcPrtnrKnm,
        String udrPrtnrNo,
        String mdfcDt

    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-EditReq")
    @Builder
    public record EditReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String prtnrNo,
        String qlfDvCd,
        String rfdt,
        String upgrDmtnDvCd,
        String upgrYm,
        String dmtnYm,
        String cntrDt,
        String cltnDt,
        String rcntrDt,
        String upgrMcn
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SaveQulificationReq")
    @Builder
    public record SaveQulificationReq(
        String ogId,
        String ogTpCd,
        String prtnrNo,
        String qlfDvCd,
        String strtdt,
        String enddt,
        String cvDt,
        String qlfAplcDvCd,
        String pymdt,
        String dsbAmt,
        String chdt,
        String pcpOgTpCd,
        String pcpPrtnrNo,
        String prcsdt,
        String dtaDlYn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstRgstPrgId,
        String fstRgstDeptId,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcDeptId
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-CheckCancellationReq")
    @Builder
    public record CheckCancellationReq(
        @NotBlank
        String prtnrNo, // 파트너번호
        @NotBlank
        String ogTpCd // 조직유형코드
    ) {
    }
}
