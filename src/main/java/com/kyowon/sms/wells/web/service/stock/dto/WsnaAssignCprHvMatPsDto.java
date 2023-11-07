package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAssignCprHvMatPsDto {
    @ApiModel("WsnaAssignCprHvMatPsDto-SearchReq")
    public record SearchReq(
        String strtDt,
        String endDt,
        String sapItemCdFrom,
        String sapItemCdTo,
        String wareNoD,
        String wareNoM,
        String strtSapCd,
        String endSapCd,
        String itmKndCd,
        String itmPdCds
    ){}
    @ApiModel("WsnaAssignCprHvMatPsDto-SearchRes")
    public record SearchRes(
        String itmKnd,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String wareNmUp,
        String onQtyUp,
        String ogCd,
        String wareMngtPrtnrNo,
        String prtnrKnm,
        String wareNm,
        Integer approvedQty,
        Integer nonApprovedQty,
        Integer onQty,
        String remain,
        String lack,
        Integer partUseTotalQty,
        Integer qtyBsComp,
        Integer qtyBsNonComp,
        Integer partUseQty01,
        Integer partUseQty02,
        Integer partUseQty03,
        Integer partUseQty04,
        Integer partUseQty05,
        Integer partUseQty06,
        Integer partUseQty07,
        Integer partUseQty08,
        Integer partUseQty09,
        Integer partUseQty10,
        Integer partUseQty11,
        Integer partUseQty12,
        Integer qtyBsComp01,
        Integer qtyBsComp02,
        Integer qtyBsComp03,
        Integer qtyBsComp04,
        Integer qtyBsComp05,
        Integer qtyBsComp06,
        Integer qtyBsComp07,
        Integer qtyBsComp08,
        Integer qtyBsComp09,
        Integer qtyBsComp10,
        Integer qtyBsComp11,
        Integer qtyBsComp12,
        Integer qtyBsNonComp01,
        Integer qtyBsNonComp02,
        Integer qtyBsNonComp03,
        Integer qtyBsNonComp04,
        Integer qtyBsNonComp05,
        Integer qtyBsNonComp06,
        Integer qtyBsNonComp07,
        Integer qtyBsNonComp08,
        Integer qtyBsNonComp09,
        Integer qtyBsNonComp10,
        Integer qtyBsNonComp11,
        Integer qtyBsNonComp12
    ){}
}
