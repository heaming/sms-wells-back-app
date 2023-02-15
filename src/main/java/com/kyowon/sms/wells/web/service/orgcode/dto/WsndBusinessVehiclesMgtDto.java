package com.kyowon.sms.wells.web.service.orgcode.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WsndBusinessVehiclesMgtDto {
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchReq")
    public record SearchReq(
        String hgrOgId,
        String ogCd,
        String prtnrNo,
        String findGb
    ) {}

    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String rol,
        String entcoDt,
        String carno,
        String vhcKndCd,
        String vhcMngtTpCd,
        String vhcPymdt,
        String dsbEnddt,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn,
        String ogCd,
        String vhcMngtNo,
        String vhcMngtSn,
        String vhcMngtPrtnrNo
    ) {
        public SearchRes {
            rflngCdnoEncr = DbEncUtil.dec(rflngCdnoEncr);
            hipsCdnoEncr = DbEncUtil.dec(hipsCdnoEncr);
        }
    }

    // 차량지급등록 Find Result Dto
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-FindRes")
    public record FindRes(
        String vhcMngtNo,
        String vhcMngtSn,
        String carno,
        String vhcMngtPrtnrNo,
        String vhcPymdt,
        String dsbEnddt,
        String vhcMngtTpCd,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn
    ) {
        public FindRes {
            // 복호화
            rflngCdnoEncr = DbEncUtil.dec(rflngCdnoEncr);
            hipsCdnoEncr = DbEncUtil.dec(hipsCdnoEncr);
        }
    }

    // 차량지급등록 Create Request Dto
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String vhcMngtNo,
        String vhcMngtSn,
        String carno,
        String vhcMngtPrtnrNo,
        @ValidDate
        String vhcPymdt,
        @ValidDate
        String dsbEnddt,
        String vhcMngtTpCd,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn,
        String fnlMdfcDtm
    ) {}

    // 차량지급등록 Edit Request Dto
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String vhcMngtNo,
        @NotBlank
        String vhcMngtSn,
        String carno,
        @NotBlank
        String vhcMngtPrtnrNo,
        @ValidDate
        String vhcPymdt,
        @ValidDate
        String dsbEnddt,
        String vhcMngtTpCd,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn,
        String fnlMdfcDtm,
        String fnlMdfcUsrNmSet,
        String fnlMdfcDeptNm
    ) {}

    // 차량번호조회 Search Result Dto
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchVehiclesRes")
    public record SearchVehiclesRes(
        String carseq,
        String owstat,
        String carno,
        String carnm,
        String cardno1,
        String cardno2
    ) {}
}
