package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstYOneAcuAsRtPsDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnbIstYOneAcuAsRtPsMapper {
    List<SearchRes> selectIstYOneAcuAsRtPss(SearchReq searchReq);
}
