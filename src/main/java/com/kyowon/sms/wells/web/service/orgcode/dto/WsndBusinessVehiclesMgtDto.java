package com.kyowon.sms.wells.web.service.orgcode.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WsndBusinessVehiclesMgtDto {
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchReq")
    public record SearchReq(
        String ogId,
        String prtnrNo,
        String findGb
    ) {}

    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String ogTpCd,
        String prtnrNo,
        String prtnrKnm,
        String rolDvCd,
        String cntrDt,
        String carno,
        String vhcMngtTpCd,
        String vhcMngtTpNm,
        String vhcPymdt,
        String dsbEnddt,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn,
        String ogId,
        String vhcMngtNo,
        String vhcMngtSn,
        String vhcMngtPrtnrNo,
        String carnm
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
        String vhcMngtOgTpCd,
        String vhcMngtPrtnrNo,
        String vhcPymdt,
        String dsbEnddt,
        String vhcMngtTpCd,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn,
        String carnm
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
        @NotBlank
        String vhcMngtOgTpCd,
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
        String fnlMdfcDtm
    ) {
        public CreateReq {
            rflngCdnoEncr = DbEncUtil.enc(rflngCdnoEncr);
            hipsCdnoEncr = DbEncUtil.enc(hipsCdnoEncr);
        }
    }

    // 차량지급등록 Edit Request Dto
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-EditReq")
    public record EditReq(
        @NotBlank
        String vhcMngtNo,
        @NotBlank
        String vhcMngtSn,
        String carno,
        @NotBlank
        String vhcMngtOgTpCd,
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
        String fnlMdfcDeptNm,
        String prtnrChYn
    ) {
        public EditReq {
            rflngCdnoEncr = DbEncUtil.enc(rflngCdnoEncr);
            hipsCdnoEncr = DbEncUtil.enc(hipsCdnoEncr);
        }
    }

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
