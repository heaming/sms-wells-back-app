package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper
public interface WctaContractSettlementMapper {
    Optional<WctaContractForAuthDvo> selectCntrBasicInfo(String cntrNo);

    int updateContractProgressStatus(String cntrNo, String cntrPrgsStatCd);

    int upsertContractChHist(String cntrNo);

    Optional<WctaPriorBizBsdtDvo> selectPriorReceipt();

    List<WctaContractStlmBasDvo> selectCntrStlms(Collection<String> cntrStlmIds);

    int updateContractSettlement(WctaContractStlmBasDvo dvo);

    int insertContractSettlementChHist(String cntrStlmId);

    Optional<WctaContractStlmBasDvo> selectCntrStlmByPk(String cntrStlmId);

    String selectMaxCntrCstAgId();

    int insertAgreeItem(WctaAgreeItemDvo dvo);

    int insertAgreeItemDtl(WctaAgreeItemDtlDvo dvo);

    int updateContractAdrpcBas(WctaContractAdrpcBasDvo dvo);

    Optional<WctaContractAdrpcBasDvo> selectContractAdrpcBasByPk(String cntrAdrpcId);

    int insertContractAdrpcBasChHist(String cntrAdrpcId);

    String selectFnitCdInfo(String crdcdBinNo);

    WctaSettlementCntrBasDvo selectContractBasic(String cntrNo);

    WctaTaxInvoiceInquiryDvo selectBasTaxInvoiceInquiry(String cntrNo);

    List<WctaContractStlmRelDvo> selectContractStlmRels(String cntrStlmId);
}
