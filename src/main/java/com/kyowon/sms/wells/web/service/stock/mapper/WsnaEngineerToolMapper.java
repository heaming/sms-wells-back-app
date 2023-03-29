package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchPartsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaEngineerToolMapper {
    List<SearchRes> selectEngineerToolDsbHist(SearchReq dto);

    PagingResult<SearchPartsRes> selectEngineerToolParts(PageInfo pageInfo);

    int insertEngineerToolsDsb(WsnaEngineerToolDvo dvo);
}
