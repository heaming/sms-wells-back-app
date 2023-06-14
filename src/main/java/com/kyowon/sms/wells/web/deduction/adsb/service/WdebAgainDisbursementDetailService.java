package com.kyowon.sms.wells.web.deduction.adsb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAgainDisbursementDetailDto.SearchAgainDisbursementReq;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAgainDisbursementDetailDto.SearchAgainDisbursementRes;
import com.kyowon.sms.wells.web.deduction.adsb.mapper.WdebAgainDisbursementDetailMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdebAgainDisbursementDetailService {

    private final WdebAgainDisbursementDetailMapper mapper;

    /**
     * 재지급 팝업 조회
     * @param dto
     * @return PagingResult<SearchAgainDisbursementRes>
     */
    public PagingResult<SearchAgainDisbursementRes> getAgainDisbursements(
        SearchAgainDisbursementReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectAgainDisbursements(dto, pageInfo);
    }

    /**
     * 재지급 팝업 조회 엑셀다운로드
     * @param dto
     * @return List<SearchAgainDisbursementRes>
     */
    public List<SearchAgainDisbursementRes> getAgainDisbursementForExcelDownload(SearchAgainDisbursementReq dto) {
        return mapper.selectAgainDisbursements(dto);
    }

}
