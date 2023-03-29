package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaEngineerToolConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchPartsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEngineerToolMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaEngineerToolService {
    private final WsnaEngineerToolMapper mapper;
    private final WsnaEngineerToolConverter converter;

    public List<SearchRes> getEngineerToolDsbHist(SearchReq dto) {
        return mapper.selectEngrToolDsbHist(dto);
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
