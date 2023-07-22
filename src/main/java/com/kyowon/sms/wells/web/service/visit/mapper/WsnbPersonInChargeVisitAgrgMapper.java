package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.FindBldRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPersonInChargeVisitAgrgDto.SearchRes;

@Mapper
public interface WsnbPersonInChargeVisitAgrgMapper {
    List<SearchRes> selectPersonInChargeVisitAgrgs(SearchReq dto);

    List<FindBldRes> selectBuildings();
}
