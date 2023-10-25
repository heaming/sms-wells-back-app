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
        @ApiImplicitParam(name = "dvCd", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "rcpDtFrom", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "rcpDtTo", value = "종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "cancDtFrom", value = "취소시작일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "cancDtTo", value = "취소종료일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdCdFrom", value = "상품시작코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdCdTo", value = "상품종료코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "pkgCdFrom", value = "패키지시작코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "pkgCdTo", value = "패키지종료코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogTpCd", value = "조직구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "feePdctTpCd", value = "제품유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl1", value = "총괄단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2", value = "지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3", value = "지점", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl4", value = "지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl5", value = "지점", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
    })

    @GetMapping("detail-orders")
    public List<SearchDetailRes> getNetDetailOrders(
        @Valid
        SearchDetailReq dto
    ) {
        return this.service.getNetDetailOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 목록 조회", notes = "조회조건에 따른 월 순주문 집계 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dvCd", value = "구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    })

    @GetMapping("aggregate-orders")
    public List<SearchAggregateRes> getAggregateNetOrders(
        @Valid
        SearchAggregateReq dto
    ) {
        return this.service.getAggregateNetOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 수수료집계대상 목록 조회", notes = "조회조건에 따른 월 순주문 집계 수수료집계대상 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    })
    @GetMapping("status-orders")
    public List<SearchStatusRes> getStatusNetOrders(
        @Valid
        SearchStatusReq dto
    ) {
        return this.service.getStatusNetOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 저장", notes = "월 순주문 집계 데이터를 저장한다.")
    @PostMapping("aggregations")
    public String saveByNetOrders(
        @Valid @RequestBody
        SaveReq dto
    ) throws Exception {
        return service.saveByNetOrders(dto);
    }

    @ApiOperation(value = "월 순주문 집계 확정", notes = "월 순주문 집계 데이터를 확정한다.")
    @PostMapping("confirm")
    public SaveResponse updateByNetOrders(
        @RequestBody @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.updateByNetOrders(dto))
            .build();
    }

    @ApiOperation(value = "순주문 집계 미등록 유형 상품 목록 조회", notes = "순주문 집계 미등록 유형 상품의 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    })

    @GetMapping("product-list")
    public List<SearchProductRes> getNetAggregateProducts(
        @Valid
        SearchProductReq dto
    ) {
        return this.service.getNetAggregateProducts(dto);
    }

    //    @ApiOperation(value = "배치 확인", notes = "배치 현재 진행상태를 조회 한다.")
    //    @GetMapping("/end-of-batch")
    //    @ApiImplicitParams(value = {
    //        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query", required = true),
    //        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    //        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = true),
    //        @ApiImplicitParam(name = "feeBatWkId", value = "배치작업ID", paramType = "query", required = true),
    //    })
    //    public String getEndOfBatch(
    //        @Valid
    //        SearchReq dto
    //    ) {
    //        return service.getEndOfBatch(dto);
    //    }
}
