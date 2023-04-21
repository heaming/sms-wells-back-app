package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdySalesTypeVariableMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyTypeVariableBaseDvo;

@Mapper(componentModel = "spring")
public interface WpdySalesTypeVariableMgtConverter {
    List<WpdyTypeVariableBaseDvo> mapAllTypeVarBaseDtoToTypeVarBaseDvo(List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos);
}
