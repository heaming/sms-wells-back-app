package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualServicePsDto.*;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIndividualServicePsDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 * <pre>
 * W-SV-U-0072M01 개인별 서비스 현황
 * </pre>
 *
 * @author kyunglyn.lee
 * @since 2023-06-17
 */
@Mapper
public interface WsnbIndividualServicePsMapper {
    int insertUnusualItem(WsnbIndividualServicePsDvo dvo);
    SearchRes selectIndividualServicePs(SearchReq dto);
    List<WsnbIndividualServicePsDvo> selectIndividualServiceHousehold(SearchReq dto);
    List<SearchContactRes> selectIndividualServiceContact(SearchReq dto);
    List<SearchFarmRes> selectIndividualFarm(SearchReq dto);
    List<SearchDelinquentRes> selectIndividualDelinquent(SearchReq dto);
    PagingResult<WsnbIndividualServicePsDvo> selectIndividualProcessState(SearchReq dto, PageInfo pageInfo);
    List<WsnbIndividualServicePsDvo>selectIndividualProcessState(SearchReq dto);
    PagingResult<SearchCounselRes> selectIndividualCounsel(SearchReq dto, PageInfo pageInfo);
}
