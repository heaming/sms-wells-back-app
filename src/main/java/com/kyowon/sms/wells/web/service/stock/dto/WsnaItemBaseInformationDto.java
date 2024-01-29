package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

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
        String itmKndCd, // 품목종류코드
        String itmPdCd, // 품목상품코드
        String itmPdNm, // 품목상품
        String wareNo, // 창고번호
        String ostrWareNo, // 출고창고번호
        String wareDvCd, // 창고구분코드
        String wareDtlDvCd, // 창고상세구분코드
        String strtSapCd, // 시작SAP코드
        String endSapCd, // 종료 SAP코드
        String itmGrpCd, // 상품그룹코드
        String svMatGrpCd // 자재그룹코드
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

    @ApiModel("WsnaItemBaseInformationDto-SearchAplcReq")
    public record SearchAplcReq(
        String itmKndCd,
        String aplcList
    ) {}

    @ApiModel("WsnaItemBaseInformationDto-SearchAplcRes")
    public record SearchAplcRes(
        String itmPdCd
    ) {}

    @ApiModel("WsnaItemBaseInformationDto-SearchWareRes")
    public record SearchWareRes(
        String wareDvCd,
        String wareDtlDvCd
    ) {}
}
