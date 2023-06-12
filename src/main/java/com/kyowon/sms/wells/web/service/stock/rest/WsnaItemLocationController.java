package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaItemLocationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 품목위치관리 REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/item-locations")
public class WsnaItemLocationController {

    private final WsnaItemLocationService service;

    @GetMapping
    public PagingResult<SearchRes> getItemLocations(SearchReq dto, PageInfo pageInfo) {
        return service.getItemLocations(dto, pageInfo);
    }
    @GetMapping("/excel-download")
    public List<SearchRes> getItemLocationsExcelDownload(SearchReq dto) {
        return service.getItemLocations(dto);
    }

    @PutMapping
    public SaveResponse saveItemLocations(
        @RequestBody
        List<CreateReq> list
    ) {
        return SaveResponse
            .builder()
            .processCount(service.saveItemLocations(list))
            .build();
    }
}
