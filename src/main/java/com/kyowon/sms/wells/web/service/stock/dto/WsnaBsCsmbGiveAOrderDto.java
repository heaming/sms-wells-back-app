package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

public class WsnaBsCsmbGiveAOrderDto {
    public record SearchReq(
        @NotBlank
        String mngtYm,
        String goDvCd,
        String csmbPdCd,
        String csmbPdCdFrom,
        String csmbPdCdTo,
        String sapMatCdFrom,
        String sapMatCdTo,
        String sgsuExcludeYn
    ) {}

    public record SearchRes(
        String mngtYm,
        String goDvCd,
        String goDvNm,
        String svpdSapCd,
        String csmbPdCd,
        String itmKnm,
        String mngtUnitCd,
        String mms6bDdlvQty,
        String mms5bDdlvQty,
        String mms4bDdlvQty,
        String mms3bDdlvQty,
        String mms2bDdlvQty,
        String mms1bDdlvQty,
        String mmAvDdlvQty,
        String strStnbQty,
        String pajuLgstCnrStocQty,
        String sgsuLgstCnrStocQty,
        String woStocQty,
        String stocPersMmN,
        String etExsDt,
        String goUprc,
        String ncstQty,
        String goQty,
        String goAmt,
        String minOrdQty,
        String pypdDc,
        String rmkCn
    ) {}

    public record CreatReq(
        @NotBlank
        String mngtYm,
        @NotBlank
        String csmbPdCd,
        @NotBlank
        String itmKnm,
        @NotBlank
        String mngtUnitCd,
        @NotBlank
        String goDvCd,
        @NotBlank
        String mms6bDdlvQty,
        @NotBlank
        String mms5bDdlvQty,
        @NotBlank
        String mms4bDdlvQty,
        @NotBlank
        String mms3bDdlvQty,
        @NotBlank
        String mms2bDdlvQty,
        @NotBlank
        String mms1bDdlvQty,
        @NotBlank
        String mmAvDdlvQty,
        String strStnbQty,
        String pajuLgstCnrStocQty,
        String sgsuLgstCnrStocQty,
        String woStocQty,
        String stocPersMmN,
        String etExsDt,
        String goUprc,
        String minOrdQty,
        String pypdDc,
        String ncstQty,
        String goQty,
        String goAmt,
        String rmkCn
    ) {}

    public record ProdutCodeRes(
        String svpdPdCd,
        String svpdNmKor
    ) {}
}
