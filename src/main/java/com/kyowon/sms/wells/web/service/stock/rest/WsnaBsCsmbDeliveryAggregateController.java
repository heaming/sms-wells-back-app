package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryAggregateDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaBsCsmbDeliveryAggregateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0294M01 B/S소모품 배부집계 현황 Controlelr
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-06
 */

@Api(tags = "[WSNA] B/S소모품 배부집계 현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/delivery-aggregates")
public class WsnaBsCsmbDeliveryAggregateController {

    private final WsnaBsCsmbDeliveryAggregateService service;

    @GetMapping("/building-code")
    @ApiOperation(value = "빌딩명 조회", notes = "빌딩정보 조회")
    public List<SearchBldRes> getBuildingList(@RequestParam(name = "mngtYmFrom")
    String mngtYmFrom, @RequestParam(name = "mngtYmTo")
    String mngtYmTo) {
        return service.getBuildingList(mngtYmFrom, mngtYmTo);
    }

    @GetMapping
    @ApiOperation(value = "배부집계 현황 조회", notes = "조회조건에 해당하는 배부집계 현황을 조회한다.")
    public List<WsnaBsCsmbDeliveryAggregateDvo> getDeliveryAggregates(@Valid
    SearchReq dto) {
        return service.getDeliveryAggregates(dto);
    }

}
