package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto.*;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaSeedingTruckArrangementMapService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/seeding-truck-arrangement-map")
@Api(tags = "[WSNA] 모종 출하대차 MAP REST API")
@RequiredArgsConstructor
@Validated
public class WsnaSeedingTruckArrangementMapController {

    private final WsnaSeedingTruckArrangementMapService service;

    @ApiOperation(value = "모종 출고센터 조회", notes = "요일별 출고센터를 조회한다")
    @GetMapping("/today-gglct")
    public List<SearchTodayGgLct> getTodayGgLct(
        SearchReq dto
    ) {
        return this.service.getTodayGgLct(dto);
    }

    @ApiOperation(value = "모종 출하대차 MAP", notes = "검색 조건을 입력받아 데이터를 조회한다.")
    @GetMapping("/map")
    public SearchRes getSeedingTruckArragementMap(
        SearchReq dto
    ) {
        return this.service.getSeedingTruckArragementMap(dto);
    }

    //    @ApiOperation(value = "모종 출하대차 MAP", notes = "검색 조건을 입력받아 데이터를 조회한다.")
    //    @GetMapping("/total")
    //    public List<WsnaSeedingTruckArrangementMapSeedDvo> getSeedingTruckArragementMap() {
    //        return this.service.getSeedTotal();
    //    }
}
