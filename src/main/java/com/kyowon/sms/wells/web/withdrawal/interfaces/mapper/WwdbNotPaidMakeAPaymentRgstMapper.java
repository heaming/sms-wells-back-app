package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentContractDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentRgstReqDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WwdbNotPaidMakeAPaymentRgstMapper {

    WwdbNotPaidMakeAPaymentContractDvo selectContractInquiry(WwdbNotPaidMakeAPaymentRgstReqDvo dvo);

    int selectContractMonthCloseInquiry(WwdbNotPaidMakeAPaymentRgstReqDvo dvo);
}
