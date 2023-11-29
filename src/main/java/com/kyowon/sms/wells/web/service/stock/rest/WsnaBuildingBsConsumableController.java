package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBuildingBsConsumableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
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
    public List<HashMap<String, Object>> getBuildingBsConsumables(SearchReq dto) {
        return service.getBuildingBsConsumables(dto);
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

    @GetMapping("/building-code")
    public List<SearchBldRes> selectBuildings() {
        return service.getBuildingList();
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

    @PostMapping("/request")
    public SaveResponse createBuildingBsConsumablesRequest(
        @RequestBody
        @Valid
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createBuildingBsConsumablesRequest(dtos))
            .build();
    }

    @GetMapping("/{mngtYm}/application-limit-qty")
    public List<SearchLmQtyRes> getApplicationLimitQty(
        @PathVariable
        String mngtYm
    ) {
        return service.getApplicationLimitQty(mngtYm);
    }
}
