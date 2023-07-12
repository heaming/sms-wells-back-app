package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

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

    List<SearchRes> selectSeedingShippings(SearchReq dto);

}
