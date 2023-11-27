package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.02.14
 */
public class WsnaReturningGoodsOstrDto {

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SearchReq")
    public record SearchReq(
        String ostrTpCd, // 출고유형
        String ostrWareNo, // 출고창고번호
        String ostrDt, // 출고일자
        String itmOstrNo // 품목출고번호
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SearchRes")
    public record SearchRes(
        ItemOutOfStorage itemOutOfStorage,
        List<ReturningGoods> returningGoods
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SearchWarehouseReq")
    public record SearchWarehouseReq(
        @NotBlank
        String userId,
        @NotBlank
        String apyYm
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SearchWarehouseRes")
    public record SearchWarehouseRes(
        String codeId,
        String codeName,
        String wareMngtPrtnrNo,
        String wareIchrNo,
        String wareDvCd,
        String wareDtlDvCd,
        String codeIdUp,
        String codeNameUp,
        String wareMngtPrtnrNoUp,
        String wareLocaraCdUp,
        String wareDvCdUp,
        String wareDtlDvCdUp
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-FindItmOstrNoReq")
    public record FindItmOstrNoReq(
        String ostrTpCd, // 출고유형코드
        String ostrDt // 출고일자
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-FindItmStrNoReq")
    public record FindItmStrNoReq(
        String ostrTpCd, // 출고유형코드
        String ostrDt, // 출고일자
        String strWareNo // strWareNo
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-ItemOutOfStorage")
    public record ItemOutOfStorage(
        String itmOstrNo, // 품목출고번호
        String ostrWareNo, // 출고창고번호
        String ostrWareNm, // 출고창고명
        String wareMngtPrtnrNo, // 출고창고관리파트너번호
        String wareDvCd, // 출고창고구분코드
        String ostrTpCd, // 출고유형코드
        String itmGdCd, // 품목등급코드
        String ostrDt, // 출고일자
        String ostrSn, // 출고일련번호
        String ostrRsonCd, // 출고사유코드
        String acbDt, // 회계일자
        String evidDvCd, // 증빙구분코드
        String strWareNm, // 입고창고명
        String strWareMngtPrtnrNo, // 입고창고관리파트너번호
        String strTpCd, // 입고유형코드
        String strWareDvCd // 입고창고구분코드
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-ReturningGoods")
    public record ReturningGoods(
        String itmOstrNo, // 품목출고번호
        String ostrSn, // 출고일련번호
        String itmStrNo,
        String strSn,
        String itmKndCd, // 품목종류코드
        String sapMatCd, // SAP자재코드
        String itmPdCd, // 품목상품코드
        String itmPdNm, // 품목명(한글)
        String itmGdCd, // 품목등급코드
        BigDecimal onQty, // 재고수량
        String mngtUnitCd, // 관리단위코드
        String ostrRsonCd, // 출고사유코드
        BigDecimal ostrQty, // 출고수량
        String rmkCn, // 비고내용
        String strConfDt, // 입고확인일자
        String acbDt, // 회계일자
        String evidDvCd, // 증빙구분코드
        String strTpCd // 입고유형코드
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState,
        @NotBlank
        String ostrTpCd, // 출고유형
        @NotBlank
        String ostrWareNo, // 출고창고번호
        @NotBlank
        String ostrDt, // 출고일자
        String strWareNo, // 입고창고번호

        String itmOstrNo, // 품목출고번호
        String ostrSn, // 출고일련번호
        String sapMatCd, // SAP자재코드
        @NotBlank
        String itmPdCd, // 품목상품코드
        String itmPdNm, // 품목명(한글)
        String itmGdCd, // 품목등급코드
        String itmKndCd, // 품목구분코드
        BigDecimal onQty, // 재고수량
        String mngtUnitCd, // 관리단위코드
        @NotBlank
        String ostrRsonCd, // 출고사유코드
        @Positive
        BigDecimal ostrQty, // 출고수량
        String rmkCn, // 비고내용
        String trnspnCd, //운송코드

        String wareMngtPrtnrNo, // 창고관리파트너번호
        String wareDvCd, // 출고창고구분코드
        String acbDt, // 회계일자
        String evidDvCd, // 증빙구분코드
        String strTpCd, // 입고유형코드
        String strWareDvCd, // 입고창고구분코드
        String strWareMngtPrtnrNo // 입고창고관리파트너번호
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String ostrTpCd, // 출고유형
        @NotBlank
        String ostrWareNo, // 출고창고번호
        @NotBlank
        String ostrDt, // 출고일자
        String strWareNo, // 입고창고번호

        @NotBlank
        String itmOstrNo, // 품목출고번호
        @NotBlank
        String ostrSn, // 출고일련번호
        String itmStrNo,
        String strSn,

        String sapMatCd, // SAP자재코드
        String itmPdCd, // 품목상품코드
        String itmPdNm, // 품목명(한글)
        String itmGdCd, // 품목등급코드
        BigDecimal onQty, // 재고수량
        String mngtUnitCd, // 관리단위코드
        String ostrRsonCd, // 출고사유코드
        BigDecimal ostrQty, // 출고수량
        String rmkCn, // 비고내용

        String wareMngtPrtnrNo, // 창고관리파트너번호
        String wareDvCd, // 출고창고구분코드
        String acbDt, // 회계일자
        String evidDvCd, // 증빙구분코드
        String strTpCd, // 입고유형코드
        String strWareDvCd, // 입고창고구분코드
        String strWareMngtPrtnrNo // 입고창고관리파트너번호
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SearchPitmStockReq")
    public record SearchPitmStockReq(
        @NotEmpty
        List<String> itmPdCds, // 품목상품코드
        @NotBlank
        String itmGdCd // 품목등급코드
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-SearchPitmStockRes")
    public record SearchPitmStockRes(
        String itmPdCd, // 품목상품코드
        String itmGdCd, // 품목등급코드
        BigDecimal pitmQty // 시점재고
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-FindOstrPrtnrNoReq")
    public record FindOstrPrtnrNoReq(
        String ostrWareNo, // 출고유형코드
        String ostrDt // 출고일자
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrDto-FindStrPrtnrNoReq")
    public record FindStrPrtnrNoReq(
        String strWareNo, // 출고유형코드
        String ostrDt // 출고일자
    ) {}
}
