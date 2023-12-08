package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaManagerBsConsumableService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0012M01 소모품 배부현황(개인) Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-05
 */

@Api(tags = "[WSNA] 개인별 활동물품 배부현황 (매니저 활동물품)")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/manager-bsconsumables")
public class WsnaManagerBsConsumableController {

    private final WsnaManagerBsConsumableService service;

    @GetMapping("/building-code/{mngtYm}")
    @ApiOperation(value = "빌딩명 조회", notes = "관리년월에 해당하는 빌딩명을 조회한다.")
    public List<SearchBldRes> selectBuildings(@PathVariable
    String mngtYm) {
        return service.selectBuildings(mngtYm);
    }

    @GetMapping("/items/{mngtYm}")
    @ApiOperation(value = "활동물품 조회", notes = "관리년월에 해당하는 활동물품을 조회한다.")
    public List<SearchItmRes> getItems(
        @PathVariable
        String mngtYm
    ) {
        return service.selectItems(mngtYm);
    }

    @GetMapping("/time-limit/{mngtYm}")
    @ApiOperation(value = "등록기간 일정 조회", notes = "관리년월에 해당하는 등록기간 일정을 조회한다.")
    public FindTmlmRes getManagerBsConsumableAplcClose(
        @PathVariable
        String mngtYm
    ) {
        return service.getManagerBsConsumableAplcClose(mngtYm);
    }

    @PostMapping("/period-term")
    @ApiOperation(value = "등록기간 설정", notes = "등록기간을 설정한다.")
    public SaveResponse createManagerBsConsumableAplcClose(
        @RequestBody
        @Valid
        CreateTmlmReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createManagerBsConsumableAplcClose(dto))
            .build();
    }

    @GetMapping("/{mngtYm}/application-limit-qty")
    @ApiOperation(value = "신청제한 수량 조회", notes = "관리년월에 해당하는 신청제한 수량을 조회한다.")
    public List<SearchLmQtyRes> getApplicationLimitQty(
        @PathVariable
        String mngtYm
    ) {
        return service.getApplicationLimitQty(mngtYm);
    }

    @GetMapping
    @ApiOperation(value = "개인 소모품 배부현황 조회", notes = "개인 소모품 배부현황을 조회한다.")
    public List<HashMap<String, Object>> getManagerBsConsumable(@Valid
    SearchReq dto) {
        return service.getManagerBsConsumable(dto);
    }

    @PostMapping
    @ApiOperation(value = "개인 소모품 배부현황 저장", notes = "개인 소모품 배부내역을 저장한다.")
    public SaveResponse createManagerBsConsumables(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createManagerBsConsumables(dtos))
            .build();
    }

    @PostMapping("/request")
    @ApiOperation(value = "개인 소모품 배부현황 출고요청", notes = "개인 소모품 배부내역을 출고요청한다.")
    public SaveResponse createManagerBsConsumablesRequest(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.createManagerBsConsumablesRequest(dtos))
            .build();
    }

}
