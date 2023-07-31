package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsHavePresentStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsHavePresentStateDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * K-W-SV-U-0112M01 자재보유현황
 * </pre>
 *
 * @author segi 홍세기
 * @since 2023.07.27
 */
@Mapper
public interface WsnaMaterialsHavePresentStateMapper {

    PagingResult<SearchRes> selectMaterialsHavePresentState(SearchReq dto, PageInfo pageInfo);

    List<WsnzWellsCodeWareHouseDvo> selectWareHouses(WsnaAsMaterialItemGradeDto.SearchWareReq dto);

}
