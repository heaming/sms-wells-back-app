package com.kyowon.sms.wells.web.fee.standard.mapper;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SearchSellProductReq;
import com.kyowon.sms.wells.web.fee.standard.dto.WfeySellProductTypeDto.SearchSellProductRes;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeySellProductTypeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WfeySellProductTypeMapper {
    List<SearchSellProductRes> selectSellProductTypeList(SearchSellProductReq req);
    PagingResult<SearchSellProductRes> selectSellProductTypeList(SearchSellProductReq req, PageInfo pageInfo);

    int selectDuplicateSellProductType(WfeySellProductTypeDvo req);
    int insertSellProductType(WfeySellProductTypeDvo req);
    int updateSellProductType(WfeySellProductTypeDvo req);
    int deleteSellProductType(WfeySellProductTypeDvo req);

}
