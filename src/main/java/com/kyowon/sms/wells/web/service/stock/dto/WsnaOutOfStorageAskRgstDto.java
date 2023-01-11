package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.01.05
 */
public class WsnaOutOfStorageAskRgstDto {

    @ApiModel(value = "WsnaOutOfStorageAskRgstDto-SearchReq")
    public record SearchReq(
        String wareNm,
        String ostrAkNo
    ) {}

    @ApiModel(value = "WsnaOutOfStorageAskRgstDto-SearchRes")
    public record SearchRes(
        String wareNm
    ) {}

}
