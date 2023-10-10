package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstYOneAcuAsRtSdingPsDto.*;

import java.util.List;

@Mapper
public interface WsnbIstYOneAcuAsRtSdingPsMapper {
    List<SearchRes> selectIstYOneAcuAsRtSdingPss(SearchReq dto);
}
