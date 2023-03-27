package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SaveMainDtlReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SaveMainReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRegistrationListDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRegistrationSubListDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbRegistrationListConvert {
    WwdbRegistrationListDvo mapSaveWwdbRegistrationListDvo(SaveMainReq dto);

    WwdbRegistrationSubListDvo mapSaveWwdbRegistrationSubListDvo(SaveMainDtlReq dto);

}
