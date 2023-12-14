package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndBusinessVehiclesOperationJrnlMgtDto {
    @ApiModel(value = "WsndBusinessVehiclesOperationJrnlMgtDto-SearchReq")
    public record SearchReq(
        String startDt,
        String endDt,
        String ogId,
        String prtnrNo,
        String rgstYn
    ) {}

    @ApiModel(value = "WsndBusinessVehiclesOperationJrnlMgtDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String prtnrKnm,
        String vhcMngtPrtnrNo,
        String hirFomCd,
        String cntrDt,
        String carnm,
        String carNo,
        String vhcOprtnDt,
        Integer vhcOprtnSn,
        Integer dptuAcuDstn,
        Integer arvAcuDstn,
        Integer oprtnDstn,
        Integer oprtnHh,
        Float lbrcqVal,
        Integer lbrcqPr,
        Double fuelCsmRt,
        Integer oprtnCt,
        Integer bspdTms,
        Integer sdpdTms,
        Double avgBspdTms,
        Double avSftIdxt,
        String vhcMngtNo,
        Integer vhcMngtSn
    ) {}

}
