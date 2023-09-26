package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsAgrgDto.SearchRes;

@Mapper
public interface WsnbBsContactVisitPsAgrgMapper {

    List<SearchRes> selectBsContactVisitPsAgrgs(SearchReq dto);
}
