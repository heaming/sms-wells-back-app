package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaTaxInvoiceInquiryDvo;

@Mapper
public interface WctaTaxInvoiceInquiryMapper {

    WctaTaxInvoiceInquiryDvo selectTaxInvoiceInquiry(String cntrNo, int cntrSn);

    WctaTaxInvoiceInquiryDvo selectTaxInvoiceInquiryCheck(String cntrNo, int cntrSn);

    int updateTaxInvoiceInquiry(WctaTaxInvoiceInquiryDvo dvo);

    int updateCntrDetailTxinvPblOj(String txinvPblOjYn, String cntrNo, int cntrSn);

    int insertTaxInvoiceReceiptBaseHist(WctaTaxInvoiceInquiryDvo dvo);
}
