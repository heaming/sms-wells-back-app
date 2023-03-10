package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

public class WctaWellsVlCntrEynInqrDto {
    @ApiModel("WctaWellsVlCntrEynInqrDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cstNo, /*고객번호*/
        @NotNull
        @Size(min = 8, max = 8)
        Integer bryyMmdd, /*생년월일*/
        @NotBlank
        @Size(min = 1, max = 1)
        String cntrtSexDv /*계약자성별구분*/
    ) {}
    @ApiModel("WctaWellsVlCntrEynInqrDto-RequestRes")
    public record RequestRes(
        String nwYn, /*신규여부(Y:신규,N:기존)*/
        String cntrNo, /*계약번호*/
        Integer cntrSn /*계약일련번호*/
    ) {}
}
