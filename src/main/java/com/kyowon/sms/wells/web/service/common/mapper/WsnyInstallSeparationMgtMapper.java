package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyInstallSeparationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyInstallSeparationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyInstallSeparationMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnyInstallSeparationMgtMapper {
    PagingResult<SearchRes> selectInstallSeparationCosts(SearchReq dto, PageInfo pageInfo);
    List<SearchRes> selectInstallSeparationCosts(SearchReq dto);
    int insertInstallSeparationCosts(WsnyInstallSeparationMgtDvo dvo);
    int deleteInstallSeparationCosts(WsnyInstallSeparationMgtDvo dvo);

}
