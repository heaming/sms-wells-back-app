package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyPaidAsCostMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyPaidAsCostMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnyPaidAsCostMgtService {
    private final WsnyPaidAsCostMgtMapper mapper;
    private final WsnyPaidAsCostMgtConverter converter;

    public PagingResult<SearchRes> getPaidAsCostMgts(
        SearchReq dto, PageInfo pageInfo
    ){
        return mapper.selectPaidAsCostMgts(dto, pageInfo);
    }


    public List<SearchRes> getPaidAsCostMgtsExcelDownload(SearchReq dto)throws Exception{
        return mapper.selectPaidAsCostMgts(dto);
    }

    public int savePaidAsCostMgts(List<SaveReq> dtos){
        int proccessCount = 0;
        for(SaveReq dto : dtos){
            WsnyPaidAsCostMgtDvo dvo = converter.mapSaveReqToWsnyPaidAsCostMgtDvo(dto);
            proccessCount += mapper.updatePaidAsCostMgts(dvo);
        }
        return proccessCount;
    }

}
