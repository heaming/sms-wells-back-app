package com.kyowon.sms.wells.web.deduction.redf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAllowanceDelinquentRedemptionFeeDto;
import com.kyowon.sms.wells.web.deduction.redf.mapper.WdeaAllowanceDelinquentRedemptionFeeMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdeaAllowanceDelinquentRedemptionFeeService {

    private final WdeaAllowanceDelinquentRedemptionFeeMapper mapper;

    /**
     * 수당 되물림 현황
     * @param dto
     * @return PagingResult
     */
    public PagingResult<WdeaAllowanceDelinquentRedemptionFeeDto.SearchRes> getAllowanceDelinquentRedemptionFees(
        WdeaAllowanceDelinquentRedemptionFeeDto.SearchReq dto, PageInfo pageInfo
    )
        throws Exception {

        return mapper.selectAllowanceDelinquentRedemptionFees(dto, pageInfo);
    }

    /**
     * 수당 되물림 현황 엑셀 다운로드
     * @param dto
     * @return SXSSFWorkbook
     */
    public List<WdeaAllowanceDelinquentRedemptionFeeDto.SearchRes> getAllowanceDelinquentRedemptionFeesForExcelDownload(
        WdeaAllowanceDelinquentRedemptionFeeDto.SearchReq dto
    ) throws Exception {
        return mapper.selectAllowanceDelinquentRedemptionFees(dto);
    }

}
