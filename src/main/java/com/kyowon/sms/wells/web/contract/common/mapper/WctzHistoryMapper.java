package com.kyowon.sms.wells.web.contract.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContraceDetailHistDvo;

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

    //계약상세상태변경이력
    WctzCntrDtlStatChHistDvo selectCntrDtlStatChangeHistory(String cntrNo, String cntrSn);

    WctzCntrDtlStatChHistDvo selectCntrDtlStatForHist(String cntrNo, String cntrSn);

    int updateCntrDtlStatChangeHistory(WctzCntrDtlStatChHistDvo dvo);

    int insertCntrDtlStatChangeHistory(WctzCntrDtlStatChHistDvo dvo);

    //계약상세변경이력
    WctzContraceDetailHistDvo selectContractDetailHistory(String cntrNo, String cntrSn);

    WctzContraceDetailHistDvo selectContractDetailForHist(String cntrNo, String cntrSn);

    int updateContractDetailHistory(WctzContraceDetailHistDvo dvo);

    int insertContractDetailHistory(WctzContraceDetailHistDvo dvo);
}
