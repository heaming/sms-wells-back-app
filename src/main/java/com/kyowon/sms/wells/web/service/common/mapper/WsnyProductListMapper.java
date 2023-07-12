package com.kyowon.sms.wells.web.service.common.mapper;

import static com.kyowon.sms.wells.web.service.common.dto.WsnyProductListDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProductListDto.SearchReq;

/**
 * <pre>
 * Wells 상품/품목 리스트 ITM_KND_CD별 조회
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.07.05
 */
@Mapper
public interface WsnyProductListMapper {

    List<SearchRes> selectProductListByItmKndCd(SearchReq dto);

}
