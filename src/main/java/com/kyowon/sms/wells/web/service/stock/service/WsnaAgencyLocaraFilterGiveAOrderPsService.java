package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAgencyLocaraFilterGiveAOrderPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAgencyLocaraFilterGiveAOrderPsService {

    private final WsnaAgencyLocaraFilterGiveAOrderPsMapper mapper;

    public List getAgencyLocaraFilterGiveAOrderPsAgrgs(
        SearchReq dto
    ) {
        return mapper.selectAgencyLocaraFilterGiveAOrderPsAgrgs(dto);
    }

    public PagingResult getAgencyLocaraFilterGiveAOrderPsPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAgencyLocaraFilterGiveAOrderPss(dto, pageInfo);
    }

    public List getAgencyLocaraFilterGiveAOrderPssForExcelDownload(SearchReq dto) {
        return mapper.selectAgencyLocaraFilterGiveAOrderPss(dto);
    }
}
