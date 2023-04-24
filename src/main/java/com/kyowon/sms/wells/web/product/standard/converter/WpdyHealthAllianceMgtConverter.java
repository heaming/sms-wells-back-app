package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyHealthAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;

@Mapper(componentModel = "spring")
public interface WpdyHealthAllianceMgtConverter {
    List<WpdyAllianceBaseDvo> mapAllAllianceBaseDtoToAllianceBaseDvo(List<WpdyHealthAllianceMgtDto.AllianceBase> dtos);
}
