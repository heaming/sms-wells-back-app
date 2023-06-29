package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0296M01 BS자재 발주수량 산출 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-29
 */

public class WsnaItemOrderQuantityDto {

    @Builder
    @ApiModel("WsnaItemOrderQuantityDto-SearchReq")
    public record SearchReq(

        // 관리년월
        @NotBlank
        String inqrYm,

        // 품목종류코드
        String itmKndCd,
        // 품목코드리스트
        List<String> itmPdCds,

        // 품목코드
        String itmPdCd,
        // 시작 SAP코드
        String strtSapCd,
        // 종료 SAP코드
        String endSapCd

    ) {}

}
