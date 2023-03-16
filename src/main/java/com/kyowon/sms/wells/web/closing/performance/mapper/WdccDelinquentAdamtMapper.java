package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdamtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdamtDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccDelinquentAdamtMapper {

    List<SearchRes> selectDelinquentAdamt(SearchReq req);
}
