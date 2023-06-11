package com.kyowon.sms.wells.web.deduction.adsb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchContractRes;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchPartnerhRes;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebMonthlyAdsbMgtDto.SearchReq;
import com.kyowon.sms.wells.web.deduction.adsb.mapper.WdebMonthlyAdsbMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdebMonthlyAdsbMgtService {

    private final WdebMonthlyAdsbMgtMapper mapper;

    /**
     * WELLS 월별 재지급 관리 파트너별 목록조회
     * @param dto
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchPartnerhRes> getMcbyAdsbMgtPartnerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMcbyAdsbPartners(dto, pageInfo);
    }

    /**
     * WELLS 월별 재지급 관리 파트너별 목록조회 - 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchPartnerhRes> getMcbyAdsbMgtPartnerForExcelDownload(SearchReq dto) {
        return mapper.selectMcbyAdsbPartners(dto);
    }

    /**
     * WELLS 월별 재지급 관리 계약별 목록조회
     * @param dto
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchContractRes> getMcbyAdsbMgtContractPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectMcbyAdsbContracts(dto, pageInfo);
    }

    /**
     * WELLS 월별 재지급 관리 계약별 목록조회 - 엑셀다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<SearchContractRes> getMcbyAdsbMgtContractForExcelDownload(SearchReq dto) {
        return mapper.selectMcbyAdsbContracts(dto);
    }

}
