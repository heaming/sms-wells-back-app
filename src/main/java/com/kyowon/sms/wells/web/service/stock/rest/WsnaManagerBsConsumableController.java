package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaManagerBsConsumableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 개인별 활동물품 배부현황 (매니저 활동물품)")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/manager-bsconsumables")
public class WsnaManagerBsConsumableController {
    private final WsnaManagerBsConsumableService service;

    /**
     * 고정, 신청품목 조회(그리드 헤더 표시용)
     * @param mngtYm
     * @return
     */
    @GetMapping("/items/{mngtYm}")
    public List<SearchItmRes> getItems(
        @PathVariable
        String mngtYm
    ) {
        return service.selectItems(mngtYm);
    }

    /**
     * 빌딩 목록 조회
     * @return
     */
    @GetMapping("/building-code")
    public List<SearchBldRes> selectBuildings() {
        return service.selectBuildings();
    }

    /**
     * 소모품 배부현황 목록 조회
     * @param dto
     * @return
     */
    @GetMapping
    public List<HashMap<String, Object>> getManagerBsConsumable(SearchReq dto) {
        return service.getManagerBsConsumable(dto);
    }

    /**
     * 등록기간 조회
     * @param mngtYm
     * @return
     */
    @GetMapping("/time-limit/{mngtYm}")
    public FindTmlmRes getManagerBsConsumableAplcClose(
        @PathVariable
        String mngtYm
    ) {
        return service.getManagerBsConsumableAplcClose(mngtYm);
    }

    /**
     * 등록기간 설정
     * @param dto
     * @return
     */
    @PostMapping("/period-term")
    public SaveResponse createManagerBsConsumableAplcClose(
        @RequestBody
        @Valid
        CreateTmlmReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createManagerBsConsumableAplcClose(dto))
            .build();
    }

    /**
     * 소모품 배부현황 저장
     * @param dtos
     * @return
     */
    @PostMapping
    public SaveResponse createManagerBsConsumables(
        @RequestBody
        @Valid
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createManagerBsConsumables(dtos))
            .build();
    }

    /**
     * 소모품 출고요청
     * @param dtos
     * @return
     */
    @PostMapping("/request")
    public SaveResponse createManagerBsConsumablesRequest(
        @RequestBody
        @Valid
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createManagerBsConsumablesRequest(dtos))
            .build();
    }

    /**
     * 신청제한수량 조회
     * @param mngtYm
     * @return
     */
    @GetMapping("/{mngtYm}/application-limit-qty")
    public List<SearchLmQtyRes> getApplicationLimitQty(
        @PathVariable
        String mngtYm
    ) {
        return service.getApplicationLimitQty(mngtYm);
    }
}
