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
    /* 판매상품별 제품유형 - 조회 */
    List<SearchSellProductRes> selectSellProductTypeList(SearchSellProductReq req);
    PagingResult<SearchSellProductRes> selectSellProductTypeList(SearchSellProductReq req, PageInfo pageInfo);
    /* 판매상품별 제품유형 - 정합성체크 */
    int selectDuplicateSellProductType(WfeySellProductTypeDvo req);
    /* 판매상품별 제품유형 - 저장 및 삭제 */
    int insertSellProductType(WfeySellProductTypeDvo req);
    int updateSellProductType(WfeySellProductTypeDvo req);
    int deleteSellProductType(WfeySellProductTypeDvo req);

}
