package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCleaningCostMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdCleaningCostMgtSearvice {

    private final WdcdCleaningCostMgtMapper mapper;

    public PagingResult<SearchRes> getCleaningCost(SearchReq req, PageInfo pageInfo) {

        PagingResult<SearchRes> res = new PagingResult<SearchRes>();
        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            res = mapper.selectCleaningCostBusinessManager(req, pageInfo);
        } else {
            res = mapper.selectCleaningCostPersonInCharge(req, pageInfo);
        }

        return res;
    }

    public List<SearchRes> getCleaningCostExcelDownload(SearchReq req) {

        List<SearchRes> res = new PagingResult<SearchRes>();
        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            res = mapper.selectCleaningCostBusinessManager(req);
        } else {
            res = mapper.selectCleaningCostPersonInCharge(req);
        }

        return res;
    }

    public int removeCleaningCost(List<String> clinrRgnos) {
        int count = 0;
        for (String clinrRgno : clinrRgnos) {
            count += mapper.removeCleaningCost(clinrRgno);
        }
        return count;
    }
}
