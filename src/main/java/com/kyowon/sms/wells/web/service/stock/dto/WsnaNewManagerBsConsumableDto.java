package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0011M01 소모품 배부현황(신입) dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-05
 */

public class WsnaNewManagerBsConsumableDto {

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-SearchReq")
    public record SearchReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 빌딩코드 리스트
        List<String> bldCds
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-SearchItmRes")
    public record SearchItmRes(
        // 배부유형코드
        String bfsvcCsmbDdlvTpCd,
        // 고정품목코드
        String fxnPdCd,
        // 고정포장단위
        String fxnPckngUnit,
        // 고정품목명
        String fxnPdNm,
        // 고정 SAP코드
        String fxnSapMatCd,
        // 신청품목코드
        String aplcPdCd,
        // 신청포장단위
        String aplcPckngUnit,
        // 신청품목명
        String aplcPdNm,
        // 신청 SAP코드
        String aplcSapMatCd,
        // 수량
        BigDecimal qty
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-FindTmlmRes")
    public record FindTmlmRes(
        // 업무 시작일자
        String bizStrtdt,
        // 업무 시작시간
        String bizStrtHh,
        // 업무 종료일자
        String bizEnddt,
        // 업무 종료시간
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-CreateTmlmReq")
    public record CreateTmlmReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 업무 시작일자
        @NotBlank
        @ValidDate
        String bizStrtdt,
        // 업무 시작시간
        @NotBlank
        String bizStrtHh,
        // 업무 종료일자
        @NotBlank
        @ValidDate
        String bizEnddt,
        // 업무 종료시간
        @NotBlank
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-CreateReq")
    public record CreateReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 배부대상코드
        @NotBlank
        String bfsvcCsmbDdlvOjCd,
        // 파트너번호
        @NotBlank
        String strWareNo,
        // 소모품상품코드
        @NotBlank
        String csmbPdCd,
        // SAP코드
        @NotBlank
        String sapMatCd,
        // 배부수량
        BigDecimal bfsvcCsmbDdlvQty,
        // 배부상태코드
        @NotBlank
        String bfsvcCsmbDdlvStatCd
    ) {}

    @ApiModel(value = "WsnaNewManagerBsConsumableDto-SearchLmQtyRes")
    public record SearchLmQtyRes(
        // SAP코드
        String sapMatCd,
        // 신청제한수량
        BigDecimal bfsvcCsmbAplcLmQty
    ) {}
}
