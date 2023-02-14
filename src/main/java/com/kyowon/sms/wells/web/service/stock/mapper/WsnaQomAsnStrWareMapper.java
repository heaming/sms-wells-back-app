package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.ogRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.prtnrRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnStrWareDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaQomAsnStrWareMapper {
    PagingResult<SearchRes> selectQomAsnStrWares(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectQomAsnStrWares(SearchReq dto);

    int insertQomAsnStrWare(List<WsnaQomAsnStrWareDvo> list);

    List<prtnrRes> selectPartners(prtnrRes dto);

    List<ogRes> selectOrganizations(ogRes dto);
}
