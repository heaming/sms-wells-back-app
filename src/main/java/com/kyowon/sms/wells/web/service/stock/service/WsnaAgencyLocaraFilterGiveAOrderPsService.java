package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAgencyLocaraFilterGiveAOrderPsConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchAgrgRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAgencyLocaraFilterGiveAOrderPsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAgencyLocaraFilterGiveAOrderPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAgencyLocaraFilterGiveAOrderPsService {

    private final WsnaAgencyLocaraFilterGiveAOrderPsMapper mapper;

    private final WsnaAgencyLocaraFilterGiveAOrderPsConverter converter;

    public List<SearchAgrgRes> getAgencyLocaraFilterGiveAOrderPsAgrgs(
        SearchReq dto
    ) {
        return mapper.selectAgencyLocaraFilterGiveAOrderPsAgrgs(dto);
    }

    public PagingResult getAgencyLocaraFilterGiveAOrderPsPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        PagingResult<WsnaAgencyLocaraFilterGiveAOrderPsDvo> dvos = mapper
            .selectAgencyLocaraFilterGiveAOrderPss(dto, pageInfo);
        PagingResult<SearchRes> pagingResult = converter.mapWsnaAgencyLocaraFilterGiveAOrderPsDvoToSearchRes(dvos);
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    public List<SearchRes> getAgencyLocaraFilterGiveAOrderPssForExcelDownload(SearchReq dto) {

        List<WsnaAgencyLocaraFilterGiveAOrderPsDvo> dvos = mapper.selectAgencyLocaraFilterGiveAOrderPss(dto);
        return converter.mapWsnaAgencyLocaraFilterGiveAOrderPsExcelDvoToSearchRes(dvos);
    }
}
