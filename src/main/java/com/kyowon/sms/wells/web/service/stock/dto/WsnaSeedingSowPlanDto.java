package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0130M01 모종 파종 예정리스트 조회 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-06
 */

public class WsnaSeedingSowPlanDto {

    @Builder
    @ApiModel("WsnaSeedingSowPlanDto-SearchReq")
    public record SearchReq(
        // 방문예정시작일자
        @NotBlank
        @ValidDate
        String strtDt,
        // 방문예정종료일자
        @NotBlank
        @ValidDate
        String endDt,
        // 계약번호
        String cntrNo,
        // 계약일련번호
        Integer cntrSn
    ) {}

    @Builder
    @ApiModel("WsnaSeedingSowPlanDto-SearchRes")
    public record SearchRes(
        // 기준상품 계약상세번호
        String baseCntrDtlNo,
        // 기준상품 고객명
        String baseCstNm,
        // 기준상품 상품명
        String basePdNm,
        // 연결상품 계약상세번호
        String connCntrDtlNo,
        // 연결상품 모종패키지
        String connSdingPkgNm,
        // 연결상품 모종명
        String connSdingPdNm,
        // 연결상품 수량
        Integer connQty,
        // 방문예정일
        String vstDueDt,
        // 파종일자
        String sowDt,
        // 계약번호
        String cntrNo,
        // 계약일련번호
        int cntrSn
    ) {}

}
