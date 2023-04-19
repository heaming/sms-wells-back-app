package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyInstallSeparationMgtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyInstallSeparationMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnyInstallSeparationMgtMapper {
    List<FindProductsRes> selectPdCd(String pdGr);
    PagingResult<SearchRes> selectInstallSeparationCosts(SearchReq dto, PageInfo pageInfo);
    List<SearchRes> selectInstallSeparationCosts(SearchReq dto);
    int insertInstallSeparationCosts(WsnyInstallSeparationMgtDvo dvo);
    int deleteInstallSeparationCosts(WsnyInstallSeparationMgtDvo dvo);

}
