package com.kyowon.sms.wells.web.service.common.service;

import com.kyowon.sms.wells.web.service.common.converter.WsnyRecapAsSvCsMngtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsSvCsMngtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyRecapAsSvCsMngtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyRecapAsSvCsMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.constant.CommConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnyRecapAsSvCsMngtService {
    private final WsnyRecapAsSvCsMngtMapper mapper;
    private final WsnyRecapAsSvCsMngtConverter converter;

    public PagingResult<SearchRes> getRecapAsSvCsMngts(
        SearchReq dto, PageInfo pageInfo
    ){
        return mapper.selectRecapAsSvCsMngts(dto, pageInfo);
    }

    public List<PdRes> getHgrPdCd(PdReq dto){
        return this.mapper.selectHgrPdCd(dto);
    }

    public List<SearchRes> getRecapAsSvCsMngtsExcelDownload(SearchReq dto)throws Exception{
        return mapper.selectRecapAsSvCsMngts(dto);
    }

    public int saveRecapAsSvCsMngts(List<SaveReq> dtos){
        int proccessCount = 0;
        for(SaveReq dto : dtos){
            WsnyRecapAsSvCsMngtDvo dvo = converter.mapSaveReqToWsnyRecapAsSvCsMngtDvo(dto);
            proccessCount += mapper.updateRecapAsSvCsMngts(dvo);
        }
        return proccessCount;
    }

}
