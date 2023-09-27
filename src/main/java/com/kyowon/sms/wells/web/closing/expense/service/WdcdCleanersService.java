package com.kyowon.sms.wells.web.closing.expense.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.expense.converter.WdcdCleanersConverter;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanersDvo;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdCleanersMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcdCleanersService {

    private final WdcdCleanersMapper mapper;
    private final WdcdCleanersConverter converter;

    public PagingResult<SearchRes> getCleanerPages(SearchReq req, PageInfo pageInfo) {

        PagingResult<WdcdCleanersDvo> dvos = null;
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();

        if (!"W1580".equals(userSession.getBaseRleCd())) {
            dvos = mapper.selectCleanersBusinessManager(req, pageInfo);
        } else {
            dvos = mapper.selectCleanersPersonInCharge(req, pageInfo);
        }

        List<SearchRes> serchResList = new ArrayList<>();
        for (WdcdCleanersDvo dvo : dvos.getList()) {
            serchResList.add(converter.mapSearchResToWdcdCleanersDvo(dvo));
        }

        PagingResult<SearchRes> res = new PagingResult<>();
        res.setList(serchResList);
        res.setPageInfo(dvos.getPageInfo());

        return res;
    }

    public List<SearchRes> getCleanersForExcelDownload(SearchReq req) {

        List<WdcdCleanersDvo> dvos = null;
        List<SearchRes> res = new PagingResult<SearchRes>();

        // TODO. 본사 영업담당자, 본사 담당자 구분 해야함
        if ("".equals(req.flag())) {
            dvos = mapper.selectCleanersBusinessManager(req);
        } else {
            dvos = mapper.selectCleanersPersonInCharge(req);
        }

        for (WdcdCleanersDvo dvo : dvos) {
            res.add(converter.mapSearchResToWdcdCleanersDvo(dvo));
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
