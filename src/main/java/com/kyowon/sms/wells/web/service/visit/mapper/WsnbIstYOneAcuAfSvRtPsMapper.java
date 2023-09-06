package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstYOneAcuAfSvRtPsDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnbIstYOneAcuAfSvRtPsMapper {
    List<SearchRes> selectIstYOneAcuAfSvRtPsList(SearchReq searchReq);
}
