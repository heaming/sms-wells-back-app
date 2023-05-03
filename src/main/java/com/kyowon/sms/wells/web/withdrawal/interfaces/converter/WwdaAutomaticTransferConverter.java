package com.kyowon.sms.wells.web.withdrawal.interfaces.converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutomaticTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutomaticTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutomaticTransferObjectItemizationInterfaceDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdaAutomaticTransferConverter {

    List<WwdaAutomaticTransferInterfaceDto.SearchObjectRes> mapWwdaAutomaticTransferDvoToSearchObjectRes(
        List<WwdaAutomaticTransferObjectItemizationInterfaceDvo> dvo
    );

    List<WwdaAutomaticTransferInterfaceDto.SearchRes> mapWwdaAutomaticTransferDvoToSearchRes(
        List<WwdaAutomaticTransferInfoInterfaceDvo> dvo
    );
}
