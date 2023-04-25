package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBuildingBsConsumableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 빌딩별 활동물품 배부현황 (매니저 활동물품)")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/building-bsconsumables")
public class WsnaBuildingBsConsumableController {
    private final WsnaBuildingBsConsumableService service;

    @GetMapping
    public List<SearchRes> getBuildingBsConsumables(SearchReq dto) {
        return null;
    }

    @GetMapping("/paging")
    public PagingResult<SearchRes> getBuildingBsConsumablPages(SearchReq dto, PageInfo pageInfo) {
        return service.getBuildingBsConsumablePages(dto, pageInfo);
    }

    @GetMapping("/items/{mngtYm}")
    public List<SearchItmRes> getItems(
        @PathVariable
        String mngtYm
    ) {
        return service.getItems(mngtYm);
    }

    @GetMapping("/time-limit/{mngtYm}")
    public FindTmlmRes getBuildingBsConsumableAplcClose(
        @PathVariable
        String mngtYm
    ) {
        return service.getBuildingBsConsumableAplcClose(mngtYm);
    }

    @PostMapping("/period-term")
    public SaveResponse createBuildingBsConsumableAplcClose(
        @RequestBody
        @Valid
        CreateTmlmReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createBuildingBsConsumableAplcClose(dto))
            .build();
    }

    @GetMapping("/building-code/{mngtYm}")
    public List<SearchBldRes> selectBuildings(
        @PathVariable
        String mngtYm
    ) {
        return service.getBuildingList(mngtYm);
    }

    @PostMapping
    public SaveResponse createBuildingBsConsumables(
        @RequestBody
        @Valid
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createBuildingBsConsumables(dtos))
            .build();
    }
}
