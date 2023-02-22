package com.kyowon.sms.wells.closing.expense.service;

import com.kyowon.sms.wells.closing.expense.dto.WwdcdOperatingCostMgtSecuritiesDto.SearchReq;
import com.kyowon.sms.wells.closing.expense.dto.WwdcdOperatingCostMgtSecuritiesDto.SearchRes;
import com.kyowon.sms.wells.closing.expense.mapper.WwdcdOperatingCostMgtSecuritiesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdcdOperatingCostMgtSecuritiesService {

    private final WwdcdOperatingCostMgtSecuritiesMapper mapper;

    public List<SearchRes> getAdjustObjectExcelDownload(SearchReq dto) {
        return mapper.selectAdjustObjectExceptionPages(dto);
    }
}
