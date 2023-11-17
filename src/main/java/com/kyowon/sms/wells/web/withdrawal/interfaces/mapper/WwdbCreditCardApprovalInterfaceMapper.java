package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;

@Mapper
public interface WwdbCreditCardApprovalInterfaceMapper {

    int updateReceiveAskDetail(ZwdzWithdrawalReceiveAskDvo receiveAskDvo);

    int updateReceiveDetail(ZwdzWithdrawalReceiveAskDvo receiveAskDvo);
}
