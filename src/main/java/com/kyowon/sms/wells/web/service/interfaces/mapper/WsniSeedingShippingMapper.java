package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchRes;

/**
 * <pre>
 * W-SV-I-0019 Wells홈페이지 모종배송내역 조회
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.07.11
 */
@Mapper
public interface WsniSeedingShippingMapper {

    PagingResult<SearchRes> selectSeedingShippings(SearchReq dto, PageInfo pageInfo);

}
