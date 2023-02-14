package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnStrWareDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaQomAsnStrWareMapper {
    PagingResult<SearchRes> selectQomAsnStrWares(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectQomAsnStrWares(SearchReq dto);

    List<SearchWareRes> selectWareGroup(SearchWareReq dto);

    int insertQomAsnStrWare(List<WsnaQomAsnStrWareDvo> list);

    List<prtnrRes> selectPartners(prtnrRes dto);

    List<ogRes> selectOrganizations(ogRes dto);
}
