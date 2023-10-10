package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRentalLeasePenaltyMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyCancelChargeBaseDvo;

/**
 * <pre>
 * 상품 렌탈/리스 위약금관리 VO <==> DTO 컨버터 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Mapper(componentModel = "spring")
public interface WpdyRentalLeasePenaltyMgtConverter {
    List<WpdyCancelChargeBaseDvo> mapAllCancelChargeBaseDtoToCancelChargeBaseDvo(List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> dtos);
}
