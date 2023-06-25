package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaRenewalWareHouseDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaQomAsnMapper {

    int selectCountQomAsn(SearchReq dto);
    PagingResult<SearchRes>  selectIndependenceQomAsns(SearchReq dto, PageInfo pageInfo);
    PagingResult<SearchRes>  selectIndividualWareQomAsns(SearchReq dto, PageInfo pageInfo);

//    int updateWareHouse(SearchReq dto);
    int updateWareHouse(WsnaRenewalWareHouseDvo vo);;
    int insertIndependenceWareQomAsns(CreateIndependenceWareReq dto);
    int insertIndividualWareQomAsns(CreateIndividualWareReq dto);
}
