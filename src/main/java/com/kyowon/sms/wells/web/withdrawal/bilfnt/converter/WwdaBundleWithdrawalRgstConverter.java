package com.kyowon.sms.wells.web.withdrawal.bilfnt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaBundleWithdrawalRgstDvo;

@Mapper(componentModel = "spring")
public interface WwdaBundleWithdrawalRgstConverter {

    WwdaBundleWithdrawalRgstDvo mapSaveReqToWwdaBundleWithdrawalRgstDvo(SaveReq saveReq);

}
