package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchInfoReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchInfoRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchFiltersReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchFiltersRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchHistoriesReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchHistoriesRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SaveFilterReq;

import java.util.List;

@Mapper
public interface WsnbFitForLifeFilterMapper {

    SearchInfoRes selectNextBSCustInfo(SearchInfoReq dto);

    List<SearchFiltersRes> selectFilterItems(SearchFiltersReq dto);

    List<SearchHistoriesRes> selectFilterHistories(SearchHistoriesReq dto);

    int insertBfsvcNxVstPromIz(SaveFilterReq dto);

    int updateCstSvRgbsprIz(SaveFilterReq dto);
}
