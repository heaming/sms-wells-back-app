package com.kyowon.sms.wells.web.closing.performance.converter;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.*;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesBondDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WdccSalesBondConverter {
    List<SearchRes> mapAllWdccSalesBondDvoToSearchRes(
        List<WdccSalesBondDvo> dvos
    );
}
