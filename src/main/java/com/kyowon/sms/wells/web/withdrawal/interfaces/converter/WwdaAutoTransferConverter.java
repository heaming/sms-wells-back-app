package com.kyowon.sms.wells.web.withdrawal.interfaces.converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferObjectItemizationInterfaceDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdaAutoTransferConverter {

    List<WwdaAutoTransferInterfaceDto.SearchObjectRes> mapWwdaAutoTransferDvoToSearchObjectRes(
        List<WwdaAutoTransferObjectItemizationInterfaceDvo> dvo
    );

    List<WwdaAutoTransferInterfaceDto.SearchRes> mapWwdaAutoTransferDvoToSearchRes(
        List<WwdaAutoTransferInfoInterfaceDvo> dvo
    );
}
