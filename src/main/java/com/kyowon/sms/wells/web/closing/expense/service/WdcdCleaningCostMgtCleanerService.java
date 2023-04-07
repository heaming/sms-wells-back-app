package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtCleanerDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtCleanerDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCleaningCostMgtCleanerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdCleaningCostMgtCleanerService {

    private final WdcdCleaningCostMgtCleanerMapper mapper;

    public PagingResult<SearchRes> getCleaningCostCleaner(SearchReq req, PageInfo pageInfo) {

        PagingResult<SearchRes> res = new PagingResult<SearchRes>();
        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            res = mapper.selectCleaningCostCleanerBusinessManager(req, pageInfo);
        } else {
            res = mapper.selectCleaningCostCleanerPersonInCharge(req, pageInfo);
        }

        return res;
    }

    public List<SearchRes> getCleaningCostExcelDownload(SearchReq req) {

        List<SearchRes> res = new PagingResult<SearchRes>();
        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            res = mapper.selectCleaningCostCleanerBusinessManager(req);
        } else {
            res = mapper.selectCleaningCostCleanerPersonInCharge(req);
        }

        return res;
    }

    public int removeCleanerCostCleanerManagement(List<String> clinrRgnos) {
        int count = 0;
        for (String clinrRgno : clinrRgnos) {
            count += mapper.removeCleanerCostCleanerManagement(clinrRgno);
        }
        return count;
    }
}
