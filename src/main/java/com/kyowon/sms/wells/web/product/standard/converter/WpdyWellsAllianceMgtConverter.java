package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyWellsAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;

@Mapper(componentModel = "spring")
public interface WpdyWellsAllianceMgtConverter {
    List<WpdyAllianceBaseDvo> mapAllAllianceBaseDtoToAllianceBaseDvo(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos);
}
