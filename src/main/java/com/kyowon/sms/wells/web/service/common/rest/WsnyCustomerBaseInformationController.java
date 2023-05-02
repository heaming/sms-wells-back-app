package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.service.WsnyCustomerBaseInformationService;
import com.kyowon.sms.wells.web.service.stock.dto.WsnyCustomerBaseInformationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnyCustomerBaseInformationDto.SearchRes;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNY] 고객 기본정보")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/customer-base-informations")
public class WsnyCustomerBaseInformationController {
    private final WsnyCustomerBaseInformationService service;

    @GetMapping
    public List<SearchRes> getCustomerBases(SearchReq dto) {
        return service.getCustomerBases(dto);
    }

    @GetMapping("/paging")
    public PagingResult<SearchRes> getCustomerBases(SearchReq dto, PageInfo pageInfo) {
        return service.getCustomerBases(dto, pageInfo);
    }
}
