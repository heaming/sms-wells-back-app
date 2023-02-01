package com.kyowon.sms.wells.web.withdrawal.bilfnt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntDsnWdrwMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaAutoFntDsnWdrwMgtDvo;

@Mapper(componentModel = "spring")
public interface WwdaAutoFntDsnWdrwMgtConverter {

    WwdaAutoFntDsnWdrwMgtDvo mapSaveReqToWwdaAutoFntDsnWdrwMgtDvo(SaveReq dto);

}
