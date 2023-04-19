package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrChRcchStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzTxinvRcpBaseChangeHistDvo;

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
}
