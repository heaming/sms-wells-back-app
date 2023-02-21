package com.kyowon.sms.wells.web.withdrawal.bilfnt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaDesignationWithdrawalCustomerMgtDvo;

@Mapper(componentModel = "spring")
public interface WwdaDesignationWithdrawalCustomerMgtConverter {

    WwdaDesignationWithdrawalCustomerMgtDvo mapSaveReqToWwdaDesignationWithdrawalCustomerMgtDvo(SaveReq dto);

    WwdaDesignationWithdrawalCustomerMgtDvo mapRemoveReqToWwdaDesignationWithdrawalCustomerMgtDvo(RemoveReq dto);

}
