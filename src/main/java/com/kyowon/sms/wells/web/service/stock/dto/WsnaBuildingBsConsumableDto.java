package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0010M01 소모품 배부현황(빌딩별) dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-04
 */

public class WsnaBuildingBsConsumableDto {
    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchReq")
    public record SearchReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 빌딩코드 리스트
        List<String> bldCds
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchItmRes")
    public record SearchItmRes(
        // 유형코드
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

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-FindTmlmRes")
    public record FindTmlmRes(
        // 시작일자
        String bizStrtdt,
        // 시작시간
        String bizStrtHh,
        // 종료일자
        String bizEnddt,
        // 종료시간
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-CreateTmlmReq")
    public record CreateTmlmReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 시작일자
        @NotBlank
        @ValidDate
        String bizStrtdt,
        // 시작시간
        @NotBlank
        String bizStrtHh,
        // 종료일자
        @NotBlank
        @ValidDate
        String bizEnddt,
        // 종료시간
        @NotBlank
        String bizEndHh
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchBldRes")
    public record SearchBldRes(
        // 빌딩코드
        String bldCd,
        // 빌딩명
        String bldNm
    ) {}

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-CreateReq")
    public record CreateReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 배부대상코드
        @NotBlank
        String bfsvcCsmbDdlvOjCd,
        // 입고창고 (빌딩코드)
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

    @ApiModel(value = "WsnaBldBfsvcCsmbDdlvDto-SearchLmQtyRes")
    public record SearchLmQtyRes(
        // SAP코드
        String sapMatCd,
        // 신청제한수량
        BigDecimal bfsvcCsmbAplcLmQty
    ) {}
}
