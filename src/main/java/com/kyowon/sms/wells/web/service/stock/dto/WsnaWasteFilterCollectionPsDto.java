package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0275M01 폐필터 회수 현황 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

public class WsnaWasteFilterCollectionPsDto {

    @Builder
    @ApiModel("WsnaWasteFilterCollectionPsDto-SearchReq")
    public record SearchReq(
        // 기준년월
        @NotBlank
        String baseYm,
        // 창고구분
        @NotBlank
        String wareDvCd,
        // 상위창고번호
        String hgrWareNo,
        // 창고번호
        String wareNo,
        // 업무유형
        String svBizHclsfCd,
        // 품목그룹
        String itmGrpCd,
        // 품목코드 리스트
        List<String> itmPdCds
    ) {}

    @Builder
    @ApiModel("WsnaWasteFilterCollectionPsDto-SearchRes")
    public record SearchRes(
        // SAP코드
        String sapMatCd,
        // 품목코드
        String pdCd,
        // 품목명
        String pdNm,
        // 구분
        String gubun,
        // 구분코드
        String gubunCd,

        // 1일 수량
        BigDecimal d01Qty,
        // 2일 수량
        BigDecimal d02Qty,
        // 3일 수량
        BigDecimal d03Qty,
        // 4일 수량
        BigDecimal d04Qty,
        // 5일 수량
        BigDecimal d05Qty,
        // 6일 수량
        BigDecimal d06Qty,
        // 7일 수량
        BigDecimal d07Qty,
        // 8일 수량
        BigDecimal d08Qty,
        // 9일 수량
        BigDecimal d09Qty,
        // 10일 수량
        BigDecimal d10Qty,
        // 11일 수량
        BigDecimal d11Qty,
        // 12일 수량
        BigDecimal d12Qty,
        // 13일 수량
        BigDecimal d13Qty,
        // 14일 수량
        BigDecimal d14Qty,
        // 15일 수량
        BigDecimal d15Qty,
        // 16일 수량
        BigDecimal d16Qty,
        // 17일 수량
        BigDecimal d17Qty,
        // 18일 수량
        BigDecimal d18Qty,
        // 19일 수량
        BigDecimal d19Qty,
        // 20일 수량
        BigDecimal d20Qty,
        // 21일 수량
        BigDecimal d21Qty,
        // 22일 수량
        BigDecimal d22Qty,
        // 23일 수량
        BigDecimal d23Qty,
        // 24일 수량
        BigDecimal d24Qty,
        // 25일 수량
        BigDecimal d25Qty,
        // 26일 수량
        BigDecimal d26Qty,
        // 27일 수량
        BigDecimal d27Qty,
        // 28일 수량
        BigDecimal d28Qty,
        // 29일 수량
        BigDecimal d29Qty,
        // 30일 수량
        BigDecimal d30Qty,
        // 31일 수량
        BigDecimal d31Qty,
        // 계 수량
        BigDecimal totQty
    ) {}

}
