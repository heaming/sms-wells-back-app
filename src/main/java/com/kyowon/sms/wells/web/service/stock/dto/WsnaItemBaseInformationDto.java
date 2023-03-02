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
        String pdNm
    ) {}

    @ApiModel("WsnaItemBaseInformationDto-SearchRes")
    public record SearchRes(
        String chk,
        String itmPdCd, /*품목상품콛,*/
        String itmNm, /*품목명*/
        String pdPrpVal19, /*품목종류*/
        String mngtUnitCd, /*관리단위*/
        String pdPrpVal06, /*Lead Time*/
        String pdPrpVal11, /*출고단위*/
        String svStrtDt, /*적용시작일자*/
        String svEndDt, /*적용종료일자*/
        String boxQty, /*OutBox내 수량*/
        String sapMatCd, /*SAP코드*/
        String sapMatGrpVal, /*SAP_자재그룹*/
        String pdPrpVal16, /*기초자재색상여부*/
        String pdAbbrNm, /*품목명(약어(1))*/
        String mulQty, /*물류수량*/
        String centerQty, /*센터수량*/
        String onQty, /*조직창고수량*/
        String indiStckQty, /*개인창고수량*/
        String lQty
    ) {}

    @ApiModel("WsnaItemBaseInformationDto-OstrRes")
    public record OstrRes(
        String itmPdCd,
        String pdNm,
        String pdPrpVal19,
        String imgApnFileId,
        String pdPrpVal05,
        String pdPrpVal02,
        String pdPrpVal01,
        String pdPrpVal06,
        String pdPrpVal31,
        String pdPrpVal11,
        String useQty,
        String svStrtDt,
        String svEndDt,
        String pdPrpVal12,
        String sapMatCd,
        String sapMatGrpVal,
        String pdPrpVal16,
        String pdAbbrNm,
        String mulQty,
        String centerQty,
        String myCenterQty,
        String indiStckQty,
        String warehouseBQty,
        String useQtyY,
        String useQtyP,
        String indiQty,
        String shortSupplty,
        String totalQty
    ) {}
}
