package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.CodeRes;
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

    public List<CodeRes> getBuilDingCd() {
        return mapper.selectBuilDingCd();
    }

    public PagingResult<SearchRes> getCleaningCost(SearchReq req, PageInfo pageInfo) {

        return mapper.selectCleaningCost(req, pageInfo);
    }

    public List<SearchRes> getCleaningCostExcelDownload(SearchReq req) {

        return mapper.selectCleaningCost(req);
    }

    public int removeCleaningCost(List<String> clingCostAdjRcpNos) {
        int count = 0;
        for (String clingCostAdjRcpNo : clingCostAdjRcpNos) {
            count += mapper.removeCleaningCost(clingCostAdjRcpNo);
        }
        return count;
    }
}
