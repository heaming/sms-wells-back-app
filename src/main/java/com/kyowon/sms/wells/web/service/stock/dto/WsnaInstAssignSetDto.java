package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0171P01 설치배정세팅
 * </pre>
 *
 * @author segi 홍세기
 * @since 2023.07.23
 */
public class WsnaInstAssignSetDto {
    @Builder
    @ApiModel("WsnaInstAssignSetDto-SearchReq")
    public record SearchReq(
        String itmPdCd

    ) {}

    @ApiModel("WsnaInstAssignSetDto-SearchRes")
    public record SearchRes(

        String chk,
        String pdNm,
        String pdCd,
        String rstrCndtId,
        String rstrCndtSn,
        String apyStrtdt,
        String apyEnddt,
        String rstrCndtSrnMarkCn,
        String rstrCndtVal2,

        String sapMatCd

    ) {}

    @ApiModel("WsnaInstAssignSetDto-CreateReq")
    public record CreateReq(

        String pdCd,
        String apyStrtdt,
        String apyEnddt,
        String rstrCndtSrnMarkCn,

        String rstrCndtVal1,

        String rstrCndtVal2

    ) {}

    @ApiModel("WsnaInstAssignSetDto-RemoveReq")
    public record RemoveReq(
        String pdCd

    ) {}
}
