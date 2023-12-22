package com.kyowon.sms.wells.web.closing.performance.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountHomeCardDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountHomeCardDto.PercentageSearchRes;

@Mapper
public interface WdccProductAccountHomeCardMapper {
    SearchRes selectProductAccountHomeCard();

    PercentageSearchRes selectProductAccountPercentageInfoHomeCard();
}
