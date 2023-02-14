package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaQomAsnStrWareConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStrWareDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnStrWareDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaQomAsnStrWareMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaQomAsnStrWareService {

    private final WsnaQomAsnStrWareMapper mapper;
    private final WsnaQomAsnStrWareConverter converter;

    /**
     *
     * @param dto
     * @return
     */
    public PagingResult<SearchRes> getQomAsnStrWares(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectQomAsnStrWares(dto, pageInfo);
    }

    public List<SearchRes> getQomAsnStrWares(
        SearchReq dto
    ) {
        return mapper.selectQomAsnStrWares(dto);
    }

    @Transactional
    public int createQomAsnStrWare(
        List<CreateReq> list
    ) {
        List<WsnaQomAsnStrWareDvo> voList = converter.mapCreateReqToWsnaQomAsnStrWareDvo(list);
        return mapper.insertQomAsnStrWare(voList);
    }

    public List<SearchWareRes> getWareGroups(
        SearchWareReq dto
    ) {
        return mapper.selectWareGroup(dto);
    }

    public List<prtnrRes> selectPartners(
        prtnrRes dto
    ) {
        return mapper.selectPartners(dto);
    }

    public List<ogRes> selectOrganizations(ogRes dto) {
        return mapper.selectOrganizations(dto);
    }
}
