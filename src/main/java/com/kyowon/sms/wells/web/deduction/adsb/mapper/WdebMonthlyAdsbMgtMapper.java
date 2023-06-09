package com.kyowon.sms.wells.web.deduction.adsb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchContractRes;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchPartnerhRes;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdebMonthlyAdsbMgtMapper {
    /* WELLS 월별 재지급 관리 목록조회 ( 판매자별 ) */
    PagingResult<SearchPartnerhRes> selectMcbyAdsbPartners(SearchReq dto, PageInfo pageInfo);

    /* WELLS 월별 재지급 관리 목록조회 ( 판매자별 ) - 엑셀 다운로드 */
    List<SearchPartnerhRes> selectMcbyAdsbPartners(SearchReq dto);

    /* WELLS 월별 재지급 관리 목록조회 ( 판매자별 ) */
    PagingResult<SearchContractRes> selectMcbyAdsbContracts(SearchReq dto, PageInfo pageInfo);

    /* WELLS 월별 재지급 관리 목록조회 ( 판매자별 ) - 엑셀 다운로드 */
    List<SearchContractRes> selectMcbyAdsbContracts(SearchReq dto);
}
