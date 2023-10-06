package com.kyowon.sms.wells.web.organization.hmnrsc.dto;

import com.sds.sflex.system.config.masking.MaskRequired;
import com.sds.sflex.system.config.masking.MaskingType;
import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

public class WogcPartnerPlannerDto {

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseReq")
    public record SearchLicenseReq(
        String prtnrKnm,
        String prtnrNo,
        String qlfDvCd
    ) {
    }

    @ApiModel(value = "WogcPartnerPlannerDto-SearchLicenseRes")
    public record SearchLicenseRes(
        String dgr1LevlOgNm,
        String dgr2LevlOgNm,
        String ogCd,
        String bldNm,
        String ogId,
        String ogTpCd,
        String prtnrNo,
        @MaskRequired(type = MaskingType.NAME)
        String prtnrKnm,
        String rsbDvCd,
        String rsbDvNm,
        String bizUseIdvTno,
        String bizUseExnoEncr,
        String bizUseLocaraTno,
        String bryyMmdd,
        String rcrtWrteDt,
        String fnlCltnDt,
        String edu143,
        String edu96,
        String qlfDvCd,
        String qlfDvNm
    ) {
        public SearchLicenseRes {
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
        String mngtYm,
        String ogId,
        String ogLevlDvCd1,
        String ogLevlDvCd2,
        String ogLevlDvCd3,
        String ogLevlDvCd4,
        String olfDvCd
    ) {
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
        @MaskRequired(type = MaskingType.NAME)
        String prtnrKnm,
        String prtnrNo,
        String qlfDvCd,
        String qlfDvNm,
        @MaskRequired(type = MaskingType.NAME)
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
        String fstRgstDtm,
        String fstRgstUsrId,
        String rgstPrtnrKnm,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String mdfcPrtnrKnm
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
}
