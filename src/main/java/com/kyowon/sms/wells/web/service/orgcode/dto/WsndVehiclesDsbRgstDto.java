package com.kyowon.sms.wells.web.service.orgcode.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

public class WsndVehiclesDsbRgstDto {
    // 차량지급등록 Find Request Dto
    @ApiModel(value = "WsndVehiclesDsbRgstDto-FindReq")
    public record FindReq(
        String vhcMngtNo,
        String vhcMngtSn
    ) {}

    // 차량지급등록 Find Result Dto
    @ApiModel(value = "WsndVehiclesDsbRgstDto-FindRes")
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
        String hipsCdnoEncr, // 암/복호화 필요 컬럼
        String vhcDsbRmkCn
    ) {
        public FindRes {
            // 복호화
            rflngCdnoEncr = DbEncUtil.dec(rflngCdnoEncr);
            hipsCdnoEncr = DbEncUtil.dec(hipsCdnoEncr);
        }
    }

    // 차량지급등록 Create Request Dto
    @ApiModel(value = "WsndVehiclesDsbRgstDto-CreateReq")
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
        String vhcDsbRmkCn
    ) {}

    // 차량지급등록 Edit Request Dto
    @ApiModel(value = "WsndVehiclesDsbRgstDto-CreateReq")
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
        String vhcDsbRmkCn
    ) {}

    // 차량번호조회 Search Result Dto
    @ApiModel(value = "WsndVehiclesDsbRgstDto-SearchVehiclesRes")
    public record SearchVehiclesRes(
        String carseq,
        String owstat,
        String carno,
        String carnm,
        String cardno1,
        String cardno2
    ) {}
}
