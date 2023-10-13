package com.kyowon.sms.wells.web.product.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.standard.dto.WpdySalesTypeVariableMgtDto;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyTypeVariableBaseDvo;

/**
 * <pre>
 * 상품 판매유형 변수 관리 VO <==> DTO 컨버터 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Mapper(componentModel = "spring")
public interface WpdySalesTypeVariableMgtConverter {
    List<WpdyTypeVariableBaseDvo> mapAllTypeVarBaseDtoToTypeVarBaseDvo(List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos);
}
