package com.kyowon.sms.wells.web.product.manage.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcLifeCustomFilterBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkDetailDvo;

/**
 * <pre>
 * 서비스 상품 - B/S 투입관리 VO <==> DTO 컨버터 
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@Mapper(componentModel = "spring")
public interface WpdcRoutineBsWorkMgtConverter {
    List<WpdcRoutineBsWorkBaseDvo> mapAllBsWorkBaseDtoToBsWorkBaseDvo(List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkBase> dtos);

    List<WpdcRoutineBsWorkDetailDvo> mapAllBsWorkDetailDtoToBsWorkDetailDvo(List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> dtos);

    List<WpdcLifeCustomFilterBaseDvo> mapAllLifeFltBaseDtoToLifeFltBaseDvo(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos);

    List<WpdcLifeCustomFilterBaseDvo> mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos);
}
