package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * K-W-SV-U-0110M01 반품출고집계현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.29
 */
@Mapper
public interface WsnaReturningGoodsOstrAgrgMapper {

    PagingResult<SearchRes> selectReturningGoodsOstrAgrg(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectReturningGoodsOstrAgrg(SearchReq dto);

}
