package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaEngineerToolDsbConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.SearchPartsRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEngineerToolDsbDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEngineerToolDsbDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEngineerToolDsbMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaEngineerToolDsbService {
    private final WsnaEngineerToolDsbMapper mapper;
    private final WsnaEngineerToolDsbConverter converter;

    public List<SearchRes> getEngrToolDsbHist(SearchReq dto) {
        return mapper.selectEngrToolDsbHist(dto);
    }

    public PagingResult<SearchPartsRes> getEngineerToolParts(PageInfo pageInfo) {
        return mapper.selectEngineerToolParts(pageInfo);
    }

    @Transactional
    public int createEngineerToolsDsb(CreateReq dto) {
        WsnaEngineerToolDsbDvo dvo = converter.mapCreatReqToEngineerToolDsb(dto);

        return mapper.insertEngineerToolsDsb(dvo);
    }
}
