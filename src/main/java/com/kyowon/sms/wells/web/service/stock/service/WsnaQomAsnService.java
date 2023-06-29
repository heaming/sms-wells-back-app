package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaQomAsnConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.CreateIndependenceWareReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.CreateIndividualWareReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaRenewalWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaQomAsnMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnaQomAsnService {
    private final WsnaQomAsnMapper mapper;
    private final WsnaQomAsnConverter converter;

    public PagingResult<SearchRes>  getIndependenceWareQomAsns(SearchReq dto, PageInfo pageInfo){
        return mapper.selectIndependenceQomAsns(dto, pageInfo);
    }
    public PagingResult<SearchRes>  getIndependenceQomAsnsAfter(SearchReq dto, PageInfo pageInfo){
        return mapper.selectIndependenceQomAsnsAfter(dto, pageInfo);
    }

    public PagingResult<SearchRes>  getIndividualWareQomAsns(SearchReq dto, PageInfo pageInfo){
        return mapper.selectIndependenceQomAsns(dto, pageInfo);
    }

    public int getCountQomAsn(SearchReq dto){
        return mapper.selectCountQomAsn(dto);
    }

    public List<WsnaQomAsnDto.WareRes> getOstrWarehouse(SearchReq dto){
        return mapper.selectOstrWarehouse(dto);
    }
    public List<WsnaQomAsnDto.WareRes> getStrWarehouse(SearchReq dto){
        return mapper.selectStrWarehouse(dto);
    }

    @Transactional
    public int editWarehouseRenewals(SearchReq dto){
        WsnaRenewalWareHouseDvo vo = converter.searchReqToWsnaRenewalWareHouseDvo(dto);
        return mapper.updateWareHouse(vo);
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
