package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0012M01 소모품 배부현황(개인) dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-05
 */

public class WsnaManagerBsConsumableDto {

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        List<String> bldCds /* 빌딩코드 리스트 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchItmRes")
    public record SearchItmRes(
        String bfsvcCsmbDdlvTpCd, /* BS소모품배부상태코드 */
        String fxnPdCd, /* 고정품목코드 */
        String fxnPckngUnit, /* 고정포장단위 */
        String fxnPdNm, /* 고정품목명 */
        String fxnSapMatCd, /* 고정SAP코드 */
        String aplcPdCd, /* 신청품목코드 */
        String aplcPckngUnit, /* 신청포장단위 */
        String aplcPdNm, /* 신청품목명 */
        String aplcSapMatCd, /* 신청SAP코드 */
        BigDecimal qty /* 수량 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-FindTmlmRes")
    public record FindTmlmRes(
        String bizStrtdt, /* 업무시작일자 */
        String bizStrtHh, /* 업무시작시간 */
        String bizEnddt, /* 업무종료일자 */
        String bizEndHh /* 업무종료시간 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-CreateTmlmReq")
    public record CreateTmlmReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        @NotBlank
        @ValidDate
        String bizStrtdt, /* 업무시작일자 */
        @NotBlank
        String bizStrtHh, /* 업무시작시간 */
        @NotBlank
        @ValidDate
        String bizEnddt, /* 업무종료일자 */
        @NotBlank
        String bizEndHh /* 업무종료시간 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String mngtYm, /* 관리년월 */
        @NotBlank
        String bfsvcCsmbDdlvOjCd, /* BS소모품배부대상코드 */
        @NotBlank
        String strWareNo, /* 입고창고번호(파트너번호 or 빌딩코드) */
        @NotBlank
        String csmbPdCd, /* 소모품상품코드 */
        @NotBlank
        String sapMatCd, /* SAP자재코드 */

        BigDecimal bfsvcCsmbDdlvQty, /* BS소모품배부수량 */
        @NotBlank
        String bfsvcCsmbDdlvStatCd /* BS소모품배부상태코드 */
    ) {}

    @ApiModel(value = "WsnaManagerBsConsumableDto-SearchLmQtyRes")
    public record SearchLmQtyRes(
        String sapMatCd, /* SAP자재코드 */
        BigDecimal bfsvcCsmbAplcLmQty /* BS소모품신청제한수량 */
    ) {}
}
