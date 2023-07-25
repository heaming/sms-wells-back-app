package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import java.util.List;

public class WsnaChangeOfRentalStatusDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 렌탈 상태변경 현황 Search Request Dto
    @Builder
    @ApiModel("WsnaChangeOfRentalStatusDto-SearchReq")
    public record SearchReq(
        String itmGdCd,
        String itmPdCd,
        String inqrYm,
        String rentalRtngdProcsTp,
        String wareNo

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈 상태변경 현황 Search Result Dto
    @ApiModel("WsnaChangeOfRentalStatusDto-SearchRes")
    public record SearchRes(
        String svpdSapCd,
        String svpdPdCd,
        String svpdNmAbbr1,
        String istDt,
        String fnlVstFshDt,
        String reqdDt,
        String fnlItmGdCd,
        String deptNm,
        String useQty,
        String cntrDtlNo,
        String rcgvpKnm,
        String ostrConfDt,
        String ostrDt,
        String rtngdProcsTpCd,
        String rmkCn,
        String rentalAssetStat,
        String cntrNo,
        String rtngdRvpyProcsYn,
        String hgrWareNo,
        String factoryDisposalGb,
        String svpdItemGr
    ) {}
    @ApiModel("WsnaChangeOfRentalStatusDto-SearchItmPdCdRes")
    public record SearchItmPdCdRes(
        String codeId,
        String codeName
    ) {}

    @ApiModel("WsnaChangeOfRentalStatusDto-SearchWarehouseReq")
    public record SearchWarehouseReq(
        String inqrYm
    ) {}

    @ApiModel("WsnaChangeOfRentalStatusDto-SearchWarehouseRes")
    public record SearchWarehouseRes(
        String codeId,
        String codeName
    ) {}
}
