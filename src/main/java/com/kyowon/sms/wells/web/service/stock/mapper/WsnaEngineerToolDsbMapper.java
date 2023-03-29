package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.SearchPartsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDsbDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaEngineerToolDsbMapper {
    List<SearchRes> selectEngrToolDsbHist(SearchReq dto);

    PagingResult<SearchPartsRes> selectEngineerToolParts(PageInfo pageInfo);

    int insertEngineerToolsDsb(WsnaEngineerToolDsbDvo dvo);
}
