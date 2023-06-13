package com.kyowon.sms.wells.web.deduction.adsb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAgainDisbursementDetailDto.SearchAgainDisbursementReq;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAgainDisbursementDetailDto.SearchAgainDisbursementRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdebAgainDisbursementDetailMapper {

    /* 재지급 팝업 조회 */
    PagingResult<SearchAgainDisbursementRes> selectAgainDisbursements(
        SearchAgainDisbursementReq dto,
        PageInfo pageInfo
    );

    /* 재지급 팝업 조회 엑셀다운로드 */
    List<SearchAgainDisbursementRes> selectAgainDisbursements(SearchAgainDisbursementReq dto);

}
