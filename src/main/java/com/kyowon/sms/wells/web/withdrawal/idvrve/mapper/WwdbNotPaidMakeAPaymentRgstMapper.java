package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbNotPaidMakeAPaymentContractDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbNotPaidMakeAPaymentRgstReqDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WwdbNotPaidMakeAPaymentRgstMapper {

    WwdbNotPaidMakeAPaymentContractDvo selectContractInquiry(WwdbNotPaidMakeAPaymentRgstReqDvo dvo);

    int selectContractMonthCloseInquiry(WwdbNotPaidMakeAPaymentRgstReqDvo dvo);
}
