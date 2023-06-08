package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0173P01 품목기본정보
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.21
 */
public class WsnaItemBaseInformationDto {
    @Builder
    @ApiModel("WsnaItemBaseInformationDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String itmKndCd,
        String itmPdCd,
        String itmPdNm,
        String wareNo,
        String ostrWareNo
    ) {}

    @ApiModel("WsnaItemBaseInformationDto-SearchRes")
    public record SearchRes(
        String sapCd, /*SAP코드*/
        String sapGrp, /*자재그룹*/
        String itmPdCd, /*품목상품코드*/
        String itmPdNm, /*품목상품명*/
        String itmPdAbbr1, /*품목상품명1*/
        String lgstQty, /*물류수량*/
        String centerQty, /*센터A등급재고*/
        String myCenterQty, /*조직창고 수량*/
        String indiStckQty, /*개인창고수량*/
        String lQty,
        String itmKnd, /*품목종류*/
        String itmKndNm, /*품목종류명*/
        String delUnt, /*출고단위*/
        String delUntNm, /*출고단위명*/
        String imgUrl, /*이미지URL*/
        String apldFr, /*사용시작일*/
        String apldTo, /*사용중지일*/
        String boxQty, /*박스수량*/
        String leadTime /*leadTime*/

    ) {}

    @ApiModel("WsnaItemBaseInformationDto-OstrRes")
    public record OstrRes(
        String itmPdCd, /*품목상품코드*/
        String itmPdNm, /*품목상품명*/
        String sapCd, /*SAP코드*/
        String itmPdNm1, /*품목상품명1*/
        String imgUrl, /*이미지URL*/
        String itemKnd, /*품목종류*/
        String boxUnitQty, /*박스단위수량*/
        String mngtUnitCd, /*신청단위코드*/
        String delUnt, /*관리단위*/
        String warehouseQty, /* 현재출고창고재고 */
        String centerQty, /*현재센터A등급재고(조직)*/
        String centerBqty, /*현재센터B등급재고(조직)*/
        String indiQty, /*개인수량*/
        String useQty, /*당월사용수량*/
        String useQtyP, /*전월기준재고*/
        String useQtyY, /*전년도*/
        String shortSupply, /*신청 예상수량*/
        String totalQty /*총재고*/

    ) {}

    @ApiModel("WsnaItemBaseInformationDto-SearchAplcReq")
    public record SearchAplcReq(
        String itmKndCd,
        String aplcList
    ) {}

    @ApiModel("WsnaItemBaseInformationDto-SearchAplcRes")
    public record SearchAplcRes(
        String itmPdCd
    ) {}
}
