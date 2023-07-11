package com.kyowon.sms.wells.web.withdrawal.interfaces.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbCreditCardApprovalDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbCreditCardApprovalInterfaceDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbCreditCardApprovalInterfaceConverter {

    ZwdbCreditCardApprovalDto.SaveReq mapCreditCardApprovalInterfaceDvoToCreditCardApprovalDtoSaveReq(
        WwdbCreditCardApprovalInterfaceDvo dvo
    );
}
