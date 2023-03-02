package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaIndividualWareOstrConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndividualWareOstrMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaIndividualWareOstrService {

    private final WsnaIndividualWareOstrMapper mapper;
    private final WsnaIndividualWareOstrConverter converter;

    public List<SearchRes> getIndividualWareOstrs(SearchReq dto) {
        return this.mapper.selectIndividualWareOstrs(dto);
    }

    public List<LogisticRes> getLogistic(LogisticReq dto) {
        return this.mapper.selectLogistic(dto);
    }

    public List<ItmRes> getItemKndCode(ItmReq dto) {
        return this.mapper.selectItemKndCode(dto);
    }
}
