package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0294M01 B/S소모품 배부집계 현황 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-06
 */

public class WsnaBsCsmbDeliveryAggregateDto {

    @ApiModel(value = "WsnaBsCsmbDeliveryAggregateDto-SearchReq")
    public record SearchReq(
        // 조회 시작년월
        @NotBlank
        String mngtYmFrom,
        // 조회 종료년월
        @NotBlank
        String mngtYmTo,
        // 빌딩코드 리스트
        List<String> bldCds,
        // 품목코드
        String itmCd,
        // 배부대상코드
        String bfsvcCsmbDdlvOjCd,
        // 시작 SAP코드
        String sapCdFrom,
        // 종료 SAP코드
        String sapCdTo
    ) {}

}
