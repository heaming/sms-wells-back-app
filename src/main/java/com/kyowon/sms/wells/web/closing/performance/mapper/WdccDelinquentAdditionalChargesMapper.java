package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccDelinquentAdditionalChargesMapper {

    List<SearchRes> selectDelinquentAdditionalCharges(SearchReq req);
}
