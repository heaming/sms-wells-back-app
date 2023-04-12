package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCleanersMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdCleanersService {

    private final WdcdCleanersMapper mapper;

    public PagingResult<SearchRes> getCleanerPages(SearchReq req, PageInfo pageInfo) {

        PagingResult<SearchRes> res = new PagingResult<SearchRes>();
        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            res = mapper.selectCleanersBusinessManager(req, pageInfo);
        } else {
            res = mapper.selectCleanersPersonInCharge(req, pageInfo);
        }

        return res;
    }

    public List<SearchRes> getCleanersForExcelDownload(SearchReq req) {

        List<SearchRes> res = new PagingResult<SearchRes>();
        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            res = mapper.selectCleanersBusinessManager(req);
        } else {
            res = mapper.selectCleanersPersonInCharge(req);
        }

        return res;
    }

    public int removeCleanersManagement(List<String> clinrRgnos) {
        int count = 0;
        for (String clinrRgno : clinrRgnos) {
            count += mapper.deleteCleanersManagement(clinrRgno);
        }
        return count;
    }
}
