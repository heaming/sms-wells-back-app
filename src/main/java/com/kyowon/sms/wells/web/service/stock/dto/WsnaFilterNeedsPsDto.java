package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0278M01 필터소요 현황(교체주기) dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

public class WsnaFilterNeedsPsDto {

    @Builder
    @ApiModel("WsnaFilterNeedsPsDto-SearchReq")
    public record SearchReq(
        // 출고시작일자
        @NotBlank
        @ValidDate
        String strtDt,

        // 출고종료일자
        @NotBlank
        @ValidDate
        String endDt,

        // 품목구분
        @NotBlank
        String itmKndCd,
        // 품목코드
        String itmPdCd,
        // 시작SAP코드
        String strtSapCd,
        // 종료SAP코드
        String endSapCd,
        // B2B관리

        String b2bMngtCd
    ) {}

}
