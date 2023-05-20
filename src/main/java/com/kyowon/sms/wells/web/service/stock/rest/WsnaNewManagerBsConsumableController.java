package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaNewManagerBsConsumableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 신입 웰스매니저 활동물품 배부현황 (매니저 활동물품)")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/newmanager-bsconsumables")
public class WsnaNewManagerBsConsumableController {
    private final WsnaNewManagerBsConsumableService service;

    @GetMapping("/items/{mngtYm}")
    public List<SearchItmRes> getItems(
        @PathVariable
        String mngtYm
    ) {
        return service.getItems(mngtYm);
    }

    @GetMapping("/building-code/{mngtYm}")
    public List<SearchBldRes> selectBuildings(
        @PathVariable
        String mngtYm
    ) {
        return service.selectBuildings(mngtYm);
    }

    @GetMapping("paging")
    public PagingResult<SearchRes> getNewManagerBsConsumablePages(SearchReq dto, PageInfo pageInfo) {
        return service.getNewManagerBsConsumablePages(dto, pageInfo);
    }

    @GetMapping("/time-limit/{mngtYm}")
    public FindTmlmRes getNewManagerBsConsumableAplcClose(
        @PathVariable
        String mngtYm
    ) {
        return service.getNewManagerBsConsumableAplcClose(mngtYm);
    }

    @PostMapping("/period-term")
    public SaveResponse createNewManagerBsConsumableAplcClose(
        @RequestBody
        @Valid
        CreateTmlmReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createBuildingBsConsumableAplcClose(dto))
            .build();
    }

    @PostMapping
    public SaveResponse createNewManagerBsConsumables(
        @RequestBody
        @Valid
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createNewManagerBsConsumables(dtos))
            .build();
    }
}
