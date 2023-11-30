package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0014M01 BS소모품 발주수량 산출 dto
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-30
 */

public class WsnaBsCsmbGiveAOrderDto {

    @ApiModel(value = "WsnaBsCsmbGiveAOrderDto-ProdutCodeRes")
    public record ProdutCodeRes(
        // 품목코드
        String svpdPdCd,
        // 품목명
        String svpdNmKor
    ) {}
    @ApiModel(value = "WsnaBsCsmbGiveAOrderDto-SearchReq")
    public record SearchReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 발주구분
        String goDvCd,
        // 품목종류
        String itmKndCd,
        // 품목코드
        String csmbPdCd,
        // 시작 품목코드
        String csmbPdCdFrom,
        // 종료 품목코드
        String csmbPdCdTo,
        // 시작 SAP 코드
        String sapMatCdFrom,
        // 종료 SAP코드
        String sapMatCdTo,
        // 성수재고
        String sgsuExcludeYn
    ) {}

    @ApiModel(value = "WsnaBsCsmbGiveAOrderDto-CreateReq")
    public record CreateReq(
        // 관리년월
        @NotBlank
        String mngtYm,
        // 소모품상품코드
        @NotBlank
        String csmbPdCd,
        // 품목명
        @NotBlank
        String itmKnm,
        // 관리단위코드
        @NotBlank
        String mngtUnitCd,
        // 발주구분코드
        @NotBlank
        String goDvCd,
        // 6개월전배부수량

        BigDecimal mms6bDdlvQty,
        // 5개월전배부수량

        BigDecimal mms5bDdlvQty,
        // 4개월전배부수량

        BigDecimal mms4bDdlvQty,
        // 3개월전배부수량

        BigDecimal mms3bDdlvQty,
        // 2개월전배부수량

        BigDecimal mms2bDdlvQty,
        // 1개월전배부수량

        BigDecimal mms1bDdlvQty,
        // 월평균배부수량

        BigDecimal mmAvDdlvQty,
        // 입고대기수량
        BigDecimal strStnbQty,
        // 파주물류센터재고수량
        BigDecimal pajuLgstCnrStocQty,
        // 성수물류센터재고수량
        BigDecimal sgsuLgstCnrStocQty,
        // 전체재고수량
        BigDecimal woStocQty,
        // 재고지속성월수
        int stocPersMmN,
        // 예상소진일자
        String etExsDt,
        // 발주단가
        BigDecimal goUprc,
        // 최소주문수량
        BigDecimal minOrdQty,
        // 납기일수
        BigDecimal pypdDc,
        // 필요수량
        int ncstQty,
        // 발주수량
        BigDecimal goQty,
        // 발주금액
        BigDecimal goAmt,
        // 비고내용
        String rmkCn
    ) {}

}
