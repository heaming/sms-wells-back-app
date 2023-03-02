package com.kyowon.sms.wells.web.bond.consultation.dto;

import io.swagger.annotations.ApiModel;

public class WbncCustomerSearchDto {
    @ApiModel(value = "WbncCustomerSearchDto-SearchReq")
    public record SearchReq(
        String bndClctnPrpDvCd,
        String bndClctnPrpRsonCd,
        String clctamPrtnrNo,
        String cstKnm,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String istCralLocaraTno,
        String istMexnoEncr,
        String istCralIdvTno,
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno,
        String istLocaraTno,
        String istExnoEncr,
        String istIdvTno,
        String adr
    ) {}

    @ApiModel(value = "WbncCustomerSearchDto-SearchRes")
    public record SearchRes(
        String bndBizDvCd,
        String bndBizDvNm,
        String cntrNo,
        String cntrSn,
        String cstKnm,
        String clctamPrtnrNo,
        String prtnrKnm,
        String cstNo,
        String sppZip,
        String sppAdr,
        String sppDtlAdr,
        String adr,
        String cntrLocaraTno,
        String cntrExnoEncr,
        String cntrIdvTno,
        String cntrCralLocaraTno,
        String cntrMexnoEncr,
        String cntrCralIdvTno,
        String istLocaraTno,
        String istExnoEncr,
        String istIdvTno,
        String istCralLocaraTno,
        String istMexnoEncr,
        String istCralIdvTno,
        String sfkVal,
        String bndClctnPrpDvCd,
        String bndClctnPrpDvNm,
        String bndClctnPrpRsonCd,
        String bndClctnPrpRsonNm
    ) {}
}
