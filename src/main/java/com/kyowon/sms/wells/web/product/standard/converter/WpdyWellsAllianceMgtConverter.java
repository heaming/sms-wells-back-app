package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyWellsAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyAllianceBaseDvo;

/**
 * <pre>
 * 상품 웰스제휴 관리 VO <==> DTO 컨버터 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Mapper(componentModel = "spring")
public interface WpdyWellsAllianceMgtConverter {
    List<WpdyAllianceBaseDvo> mapAllAllianceBaseDtoToAllianceBaseDvo(List<WpdyWellsAllianceMgtDto.AllianceBase> dtos);
}
