package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaQomAsnMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WsnaQomAsnService {
    private final WsnaQomAsnMapper mapper;

    public PagingResult<SearchRes>  getIndependenceWareQomAsns(SearchReq dto, PageInfo pageInfo){
        return mapper.selectIndependenceQomAsns(dto, pageInfo);
    }
    public PagingResult<SearchRes>  getIndividualWareQomAsns(SearchReq dto, PageInfo pageInfo){
        return mapper.selectIndependenceQomAsns(dto, pageInfo);
    }

    @Transactional
    public int editWarehouseRenewals(SearchReq dto){
        return mapper.updateWareHouse(dto);
    }

    @Transactional
    public int createIndependenceWareQomAsns(CreateIndependenceWareReq dto){
        return mapper.insertIndependenceWareQomAsns(dto);
    }

    @Transactional
    public int createIndividualWareQomAsns(CreateIndividualWareReq dto){
        return mapper.insertIndividualWareQomAsns(dto);
    }
}
