package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbGoodsChangeAcceptingStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbGoodsChangeAcceptingStateDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbGoodsChangeAcceptingStateMapper {

    PagingResult<SearchRes> selectGoodsChangeAcceptingState(SearchReq dto, PageInfo pageInfo);

    int updateSvpdPdChngAprAkIz(WsnbGoodsChangeAcceptingStateDvo vo);

    int updateCstSvasIstOjIz(WsnbGoodsChangeAcceptingStateDvo dvo);

    int insertCstSvasIstOjHist(WsnbGoodsChangeAcceptingStateDvo dvo);

    int updateCstSvAsIstAsnIz(WsnbGoodsChangeAcceptingStateDvo dvo);

    int insertSvWkDchIz(WsnbGoodsChangeAcceptingStateDvo dvo);

}
