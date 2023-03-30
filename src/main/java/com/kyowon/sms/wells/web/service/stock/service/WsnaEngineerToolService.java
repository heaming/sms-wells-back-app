package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaEngineerToolConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEngineerToolMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaEngineerToolService {
    private final WsnaEngineerToolMapper mapper;
    private final WsnaEngineerToolConverter converter;

    public List<SearchRes> getEngineerToolDsbHist(SearchReq dto) {
        return mapper.selectEngineerToolDsbHist(dto);
    }

    public PagingResult<SearchRes> getEngineerToolDsbHistPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectEngineerToolDsbHist(dto, pageInfo);
    }

    @Transactional
    public int removeEngineerTools(List<RemoveReq> dtos) {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WsnaEngineerToolDvo dvo = converter.mapRemoveReqToEngineerToolDsb(dto);
            int result = mapper.deleteEngineerTools(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }

    public PagingResult<SearchPartsRes> getEngineerToolParts(PageInfo pageInfo) {
        return mapper.selectEngineerToolParts(pageInfo);
    }

    @Transactional
    public int createEngineerToolsDsb(CreateReq dto) {
        WsnaEngineerToolDvo dvo = converter.mapCreatReqToEngineerToolDsb(dto);

        return mapper.insertEngineerToolsDsb(dvo);
    }
}
