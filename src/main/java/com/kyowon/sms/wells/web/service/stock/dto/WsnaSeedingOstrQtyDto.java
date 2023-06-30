package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0129M01 모종 출고가능수량 관리 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-27
 */

public class WsnaSeedingOstrQtyDto {

    @Builder
    @ApiModel("WsnaSeedingOstrQtyDto-SearchReq")
    public record SearchReq(
        // 시작일자
        @NotBlank
        @ValidDate
        String strtDt,
        // 종료일자
        @NotBlank
        @ValidDate
        String endDt,
        // 업무유형
        String svBizHclsfCd
    ) {}

    @Builder
    @ApiModel("WsnaSeedingOstrQtyDto-SearchRes")
    public record SearchRes(
        // 방문일자
        String vstDt,
        // 업무유형
        String svBizHclsfNm,
        // 패키지명
        String sdingPkgNm,
        // 한도수량
        BigDecimal limQty,
        // 모종패키지그룹코드
        String sdingPkgGrpCd,
        // 서비스업무대분류코드
        String svBizHclsfCd,
        // 한도일련번호
        int limSn
    ) {}

    @Builder
    @ApiModel("WsnaSeedingOstrQtyDto-EditReq")
    public record EditReq(
        // 방문일자
        @NotBlank
        @ValidDate
        String vstDt,
        // 모종패키지그룹코드
        @NotBlank
        String sdingPkgGrpCd,
        // 서비스업무대분류코드
        @NotBlank
        String svBizHclsfCd,
        // 한도일련번호
        @Positive
        int limSn,
        // 한도수량
        @Positive
        @Max(999999999999L)
        BigDecimal limQty
    ) {}

}
