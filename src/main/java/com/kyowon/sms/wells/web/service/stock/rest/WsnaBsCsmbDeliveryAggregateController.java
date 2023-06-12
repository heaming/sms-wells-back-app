package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchQtysRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBsCsmbDeliveryAggregateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] B/S소모품 배부집계 현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/delivery-aggregates")
public class WsnaBsCsmbDeliveryAggregateController {

    private final WsnaBsCsmbDeliveryAggregateService service;

    @GetMapping
    public List<SearchRes> getDeliveryAggregates(SearchReq dto) {
        return service.getDeliveryAggregates(dto);
    }

    @GetMapping("/paging")
    public PagingResult<SearchRes> getDeliveryAggregatePages(SearchReq dto, PageInfo pageInfo) {
        return service.getDeliveryAggregatePages(dto, pageInfo);
    }

    @GetMapping("/item-quantity")
    public List<SearchQtysRes> getItemQtys(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getItemQtys(dto, pageInfo);
    }
}
