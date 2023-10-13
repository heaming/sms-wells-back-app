package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdySeedlingPriceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdySeedlingPriceBaseDvo;

/**
 * <pre>
 * 상품 모종제품가격 관리 VO <==> DTO 컨버터 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Mapper(componentModel = "spring")
public interface WpdySeedlingPriceMgtConverter {
    List<WpdySeedlingPriceBaseDvo> mapAllSeedlingPriceBaseDtoToSeedlingPriceBaseDvo(List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos);
}
