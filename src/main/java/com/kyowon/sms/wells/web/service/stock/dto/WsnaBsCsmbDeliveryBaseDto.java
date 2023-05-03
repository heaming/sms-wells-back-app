package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

import io.swagger.annotations.ApiModel;

public class WsnaBsCsmbDeliveryBaseDto {
    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-SearchReq")
    public record SearchReq(
        String mngtYm,
        String goDvCd,
        String csmbPdCd,
        String csmbPdCdStrt,
        String csmbPdCdEnd,
        String sapMatCdStrt,
        String sapMatCdEnd,
        List<String> itmKnms
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-SearchRes")
    public record SearchRes(
        String mngtYm,
        String csmbPdCd,
        String itmKnm,
        String mngtUnitCd,
        String mngtUnitNm,
        String goDvCd,
        String goDvNm,
        String goUprc,
        String boxUnitQty,
        String sapMatCd,
        String nwcmrOrtYn,
        String nwcmrTpCd,
        String nwcmrTpNm,
        String nwcmrCmptBaseCd,
        String nwcmrCmptBaseNm,
        String nwcmrPdGrpCd,
        String nwcmrPdGrpNm,
        String nwcmrAccTpCd,
        String nwcmrAccTpNm,
        String nwcmrUnitAccN,
        String nwcmrUnitQty,
        String nwcmrLmQty,
        String nwcmrSortOdr,
        String indvOrtYn,
        String indvTpCd,
        String indvTpNm,
        String indvCmptBaseCd,
        String indvCmptBaseNm,
        String indvPdGrpCd,
        String indvPdGrpNm,
        String indvAccTpCd,
        String indvAccTpNm,
        String indvUnitAccN,
        String indvUnitQty,
        String indvLmQty,
        String indvSortOdr,
        String bldOrtYn,
        String bldTpCd,
        String bldTpNm,
        String bldCmptBaseCd,
        String bldCmptBaseNm,
        String bldPdGrpCd,
        String bldPdGrpNm,
        String bldAccTpCd,
        String bldAccTpNm,
        String bldUnitAccN,
        String bldUnitQty,
        String bldLmQty,
        String bldSortOdr
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String mngtYm,
        String itmKnm,
        @NotBlank
        String csmbPdCd,
        String mngtUnitCd,
        String goDvCd,
        String boxUnitQty,
        String goUprc,
        String rmkCn,
        String bfsvcCsmbDdlvOjCd,
        String bfsvcCsmbDdlvOrtYn,
        String bfsvcCsmbDdlvTpCd,
        String bfsvcCsmbDdlvCmptBaseCd,
        String bfsvcCsmbDdlvOjPdGrpCd,
        String bfsvcCsmbDdlvOjAccTpCd,
        String bfsvcCsmbDdlvUnitAccN,
        String bfsvcCsmbDdlvUnitQty,
        String bfsvcCsmbAplcLmQty,
        String sortOdr
    ) {
        public CreateReq {
            goUprc = StringUtil.isEmpty(goUprc) ? "0" : goUprc;
            boxUnitQty = StringUtil.isEmpty(boxUnitQty) ? "0" : boxUnitQty;
            bfsvcCsmbDdlvUnitAccN = StringUtil.isEmpty(bfsvcCsmbDdlvUnitAccN) ? "0" : bfsvcCsmbDdlvUnitAccN;
            bfsvcCsmbDdlvUnitQty = StringUtil.isEmpty(bfsvcCsmbDdlvUnitQty) ? "0" : bfsvcCsmbDdlvUnitQty;
            bfsvcCsmbAplcLmQty = StringUtil.isEmpty(bfsvcCsmbAplcLmQty) ? "0" : bfsvcCsmbAplcLmQty;
            sortOdr = StringUtil.isEmpty(sortOdr) ? "1" : sortOdr;
        }
    }

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-SearchItemsRes")
    public record SearchItemsRes(
        String pdCd,
        String pdNm,
        String pdAbbrNm
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-FindRes")
    public record FindRes(
        String mngtYm,
        String csmbPdCd,
        String itmKnm,
        String mngtUnitCd,
        String goDvCd,
        String goUprc,
        String boxUnitQty,
        String rmkCn,
        String nwcmrOrtYn,
        String nwcmrTpCd,
        String nwcmrCmptBase,
        String nwcmrOjPdGrpCd,
        String nwcmrOjAccTpCd,
        String nwcmrUnitAccN,
        String nwcmrUnitQty,
        String nwcmrAplcLmQty,
        String nwcmrSortOdr,
        String indvOrtYn,
        String indvTpCd,
        String indvCmptBase,
        String indvOjPdGrpCd,
        String indvOjAccTpCd,
        String indvUnitAccN,
        String indvUnitQty,
        String indvAplcLmQty,
        String indvSortOdr,
        String bldOrtYn,
        String bldvTpCd,
        String bldCmptBase,
        String bldOjPdGrpCd,
        String bldOjAccTpCd,
        String bldUnitAccN,
        String bldUnitQty,
        String bldAplcLmQty,
        String bldSortOdr
    ) {}

    @ApiModel(value = "WsnaBsCsmbDeliveryBaseDto-CreateReq")
    public record EditReq(
        String mngtYm,
        String csmbPdCd,
        String mngtUnitCd,
        String goDvCd,
        String boxUnitQty,
        String goUprc,
        String rmkCn,
        String bfsvcCsmbDdlvOjCd,
        String bfsvcCsmbDdlvOrtYn,
        String bfsvcCsmbDdlvTpCd,
        String bfsvcCsmbDdlvCmptBaseCd,
        String bfsvcCsmbDdlvOjPdGrpCd,
        String bfsvcCsmbDdlvOjAccTpCd,
        String bfsvcCsmbDdlvUnitAccN,
        String bfsvcCsmbDdlvUnitQty,
        String bfsvcCsmbAplcLmQty,
        String sortOdr
    ) {}
}
