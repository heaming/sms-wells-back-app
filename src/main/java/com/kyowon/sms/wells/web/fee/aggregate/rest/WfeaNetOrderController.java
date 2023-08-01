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
@RequestMapping(CtFeeConst.REST_URL_V1 + "/monthly-net")
@Slf4j
public class WfeaNetOrderController {
    private final WfeaNetOrderService service;

    @ApiOperation(value = "월 순주문 집계 원천데이터 목록 조회", notes = "조회조건에 따른 월 순주문 집계 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dvCd", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "tcntDvCd", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogDvCd", value = "조직구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "divCd", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdctTpCd", value = "제품유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "selTpCd", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "strtDt", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "cancStrtDt", value = "취소시작일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "cancEndDt", value = "취소종료일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdStrtCd", value = "상품시작코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdEndCd", value = "상품종료코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "pkgStrtCd", value = "패키지시작코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "pkgEndCd", value = "패키지종료코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1Id", value = "총괄단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2Id", value = "지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "지점", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
    })

    @GetMapping("orders")
    public List<SearchRes> getNetOrders(
        @Valid
        SearchReq dto
    ) {
        return this.service.getNetOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 목록 조회", notes = "조회조건에 따른 월 순주문 집계 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schDvCd", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "tcntDvCd", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogDvCd", value = "조직구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYm", value = "번호", paramType = "query", required = true),
    })

    @GetMapping("aggreateOrders")
    public List<SearchRes> getNetAggregateOrders(
        @Valid
        SearchReq dto
    ) {
        return this.service.getNetAggreateOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 수수료집계대상 목록 조회", notes = "조회조건에 따른 월 순주문 집계 수수료집계대상 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "schDv", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdctTp", value = "제품유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "schPdCdStrt", value = "상품코드 시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "schPdCdEnd", value = "상품코드 종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "schSlDtStrt", value = "매출일자 시작", paramType = "query", required = true),
        @ApiImplicitParam(name = "schSlDtEnd", value = "매출일자 종료", paramType = "query", required = true),
    })
    @GetMapping("fees")
    public List<SearchFeeRes> getNetOrderFees(
        @Valid
        SearchReq dto
    ) {
        return this.service.getNetOrderFees(dto);
    }

    @ApiOperation(value = "월 순주문 집계 확정여부 조회", notes = "전월 차수에 따른 월 순주문 집계 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "tcntDvCd", value = "차수", paramType = "query", required = true),
    })

    @GetMapping("confirmChk")
    public SearchConfirmRes getNetAggregateConfirm(
        @Valid
        SearchReq dto
    ) {
        return this.service.getNetAggregateConfirm(dto);
    }

    @ApiOperation(value = "월 순주문 집계 저장", notes = "월 순주문 집계 데이터를 저장한다.")
    @PostMapping("aggregations")
    public SaveResponse saveByNetOrders(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveByNetOrders(dto))
            .build();
    }

    @ApiOperation(value = "월 순주문 집계 확정", notes = "월 순주문 집계 데이터를 확정한다.")
    @PostMapping("confirm")
    public SaveResponse updateByNetOrders(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.updateByNetOrders(dto))
            .build();
    }
}
