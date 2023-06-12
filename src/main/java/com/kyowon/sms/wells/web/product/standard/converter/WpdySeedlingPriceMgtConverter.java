package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdySeedlingPriceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdySeedlingPriceBaseDvo;

@Mapper(componentModel = "spring")
public interface WpdySeedlingPriceMgtConverter {
    List<WpdySeedlingPriceBaseDvo> mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos);
}
