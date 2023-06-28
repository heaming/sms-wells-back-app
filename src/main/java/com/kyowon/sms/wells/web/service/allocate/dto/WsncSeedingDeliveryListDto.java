package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * 모종 배송내역
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-06-23
 */
public class WsncSeedingDeliveryListDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    @ApiModel(value = "WsncSeedingDeliveryListDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String cntrNo,
        String cntrSn
    ) {}

    // *********************************************************
    // Response Dto
    // *********************************************************
    @ApiModel(value = "WsncSeedingDeliveryListDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String sdingInfo,
        String adr,
        String vstDt,
        String dlvyStatus,
        Integer sellAmt
    ) {}

}
