package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0140M01 품목별 재고 집계 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-08
 */

public class WsnaItemByStockAggregationDto {

    @Builder
    @ApiModel("WsnaItemByStockAggregationDto-SearchWareRes")
    public record SearchWareRes(
        // 창고상세구분
        String wareDtlDvCd,
        // 창고번호
        String wareNo,
        // 창고명
        String wareNm
    ) {}

    @Builder
    @ApiModel("WsnaItemByStockAggregationDto-SearchReq")
    public record SearchReq(
        // 기준일자
        @NotBlank
        @ValidDate
        String baseDt,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 재고유형
        String mgtTypCd,
        // 품목구분
        String itmKndCd,
        // 품목그룹
        String itmGrpCd,
        // 품목코드 리스트
        List<String> itmPdCds,
        // 등급
        String itmGdCd,
        // 사용여부
        String useYn,
        // 자재그룹
        String svMatGrpCd,
        // 창고유형
        String wareTpCd,
        // 품목코드
        String itmPdCd,
        // 시작SAP코드
        String strtSapCd,
        // 종료SAP코드
        String endSapCd,
        // 중수리자재여부
        String commGb,
        // 기초자재여부
        String baseGb,
        // 회전율대상여부
        String turnoverGb

    ) {}
}
