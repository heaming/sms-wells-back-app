package com.kyowon.sms.wells.web.closing.performance.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountHomeCardDto.SearchRes;

@Mapper
public interface WdccProductAccountHomeCardMapper {
    SearchRes selectProductAccountHomeCard();
}
