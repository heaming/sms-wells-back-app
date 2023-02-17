package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNetOrderDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaNetOrderService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Api(tags = "[WFEA] 월순주문 집계")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/net-orders")
@Slf4j
public class WfeaNetOrderController {
    private final WfeaNetOrderService service;

    @ApiOperation(value = "월 순주문 집계 목록 조회", notes = "조회조건에 따른 월 순주문 집계 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCdStrt", value = "상품코드 시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdCdEnd", value = "상품코드 종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "slDtStrt", value = "매출일자 시작", paramType = "query", required = true),
        @ApiImplicitParam(name = "slDtEnd", value = "매출일자 종료", paramType = "query", required = true),
    })

    @GetMapping()
    public List<SearchRes> getNetOrders(
        @Valid
        SearchReq dto
    ) {
        return this.service.getNetOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 저장", notes = "월 순주문 집계 데이터를 저장한다.")
    @PostMapping
    public SaveResponse saveByNetOrders(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveByNetOrders(dto))
            .build();
    }
}
