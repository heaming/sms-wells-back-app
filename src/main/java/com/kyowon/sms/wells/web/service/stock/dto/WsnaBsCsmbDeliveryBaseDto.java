package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.StringUtil;

public class WsnaBsCsmbDeliveryBaseDto {
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
}
