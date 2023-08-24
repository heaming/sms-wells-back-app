package com.kyowon.sms.wells.web.closing.performance.converter;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccDelinquentAdditionalChargesDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WdccDelinquentAdditionalChargesConverter {
    List<SearchRes> mapAllWdccDelinquentAdditionalChargesDvoToSearchRes(
        List<WdccDelinquentAdditionalChargesDvo> dvos
    );
}
