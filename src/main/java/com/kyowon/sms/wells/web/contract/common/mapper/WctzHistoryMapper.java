package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dvo.*;

@Mapper
public interface WctzHistoryMapper {

    WctzCntrDetailChangeHistDvo selectCntrDetailForHist(String cntrNo, int cntrSn);

    WctzCntrDetailChangeHistDvo selectCntrDetailChangeHist(String cntrNo, int cntrSn);

    int insertCntrDetailChangeHist(WctzCntrDetailChangeHistDvo dvo);

    int updateCntrDetailChangeHist(WctzCntrDetailChangeHistDvo dvo);

    int deleteCntrDetailChangeHist(WctzCntrDetailChangeHistDvo dvo);

    WctzCntrDtlStatChangeHistDvo selectCntrDetailStatChangeHist(String cntrNo, int cntrSn);

    int insertCntrDetailStatChangeHist(WctzCntrDtlStatChangeHistDvo dvo);

    int updateCntrDetailStatChangeHist(WctzCntrDtlStatChangeHistDvo dvo);

    int deleteCntrDetailStatChangeHist(WctzCntrDtlStatChangeHistDvo dvo);

    WctzTxinvRcpBaseChangeHistDvo selectTaxInvoiceReceiptBase(String cntrNo, int cntrSn);

    int insertTaxInvoiceReceiptBaseHist(WctzTxinvRcpBaseChangeHistDvo dvo);

    WctzCntrChRcchStatChangeHistDvo selectCntrChRcchStatChangeHist(String cntrChRcpId);

    int insertCntrChRcchStatChangeHist(WctzCntrChRcchStatChangeHistDvo dvo);

    int updateCntrChRcchStatChangeHist(WctzCntrChRcchStatChangeHistDvo dvo);

    int deleteCntrChRcchStatChangeHist(WctzCntrChRcchStatChangeHistDvo dvo);

    WctzCntrBasicChangeHistDvo selectCntrBasicChangeHist(String cntrNo);

    int insertCntrBasicChangeHist(WctzCntrBasicChangeHistDvo dvo);

    int updateCntrBasicChangeHist(WctzCntrBasicChangeHistDvo dvo);

    int deleteCntrBasicChangeHist(WctzCntrBasicChangeHistDvo dvo);
}
