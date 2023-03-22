package com.kyowon.sms.wells.web.product.manage.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcLifeCustomFilterBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkBaseDvo;
import com.kyowon.sms.wells.web.product.manage.dvo.WpdcRoutineBsWorkDetailDvo;

@Mapper(componentModel = "spring")
public interface WpdcRoutineBsWorkMgtConverter {
    List<WpdcRoutineBsWorkBaseDvo> mapAllBsWorkBaseDtoToBsWorkBaseDvo(List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkBase> dtos);

    List<WpdcRoutineBsWorkDetailDvo> mapAllBsWorkDetailDtoToBsWorkDetailDvo(List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> dtos);

    List<WpdcLifeCustomFilterBaseDvo> mapAllLifeFltBaseDtoToLifeFltBaseDvo(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos);

    List<WpdcLifeCustomFilterBaseDvo> mapAllRemoveLifeFltBaseDtoToLifeFltBaseDvo(List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos);
}
