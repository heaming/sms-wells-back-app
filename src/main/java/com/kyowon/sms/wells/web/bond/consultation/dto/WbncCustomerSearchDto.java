package com.kyowon.sms.wells.web.bond.consultation.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

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
    ) {
        public SearchReq {
            cntrExnoEncr = StringUtils.isNotEmpty(cntrExnoEncr) ? DbEncUtil.enc(cntrExnoEncr) : cntrExnoEncr;
            cntrMexnoEncr = StringUtils.isNotEmpty(cntrMexnoEncr) ? DbEncUtil.enc(cntrMexnoEncr) : cntrMexnoEncr;
            istExnoEncr = StringUtils.isNotEmpty(istExnoEncr) ? DbEncUtil.enc(istExnoEncr) : istExnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.enc(istMexnoEncr) : istMexnoEncr;
        }
    }

    @ApiModel(value = "WbncCustomerSearchDto-SearchRes")
    public record SearchRes(
        String bndBizDvCd,
        String bndBizDvNm,
        String cntrNo,
        int cntrSn,
        String cstKnm,
        String clctamPrtnrNo,
        String prtnrKnm,
        String cstNo,
        String bndCntrTpCd,
        String bzHdqDvCd,
        String dtaDlYn,
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
    ) {
        public SearchRes {
            cntrExnoEncr = StringUtils.isNotEmpty(cntrExnoEncr) ? DbEncUtil.dec(cntrExnoEncr) : cntrExnoEncr;
            cntrMexnoEncr = StringUtils.isNotEmpty(cntrMexnoEncr) ? DbEncUtil.dec(cntrMexnoEncr) : cntrMexnoEncr;
            istExnoEncr = StringUtils.isNotEmpty(istExnoEncr) ? DbEncUtil.dec(istExnoEncr) : istExnoEncr;
            istMexnoEncr = StringUtils.isNotEmpty(istMexnoEncr) ? DbEncUtil.dec(istMexnoEncr) : istMexnoEncr;
        }

    }
}
