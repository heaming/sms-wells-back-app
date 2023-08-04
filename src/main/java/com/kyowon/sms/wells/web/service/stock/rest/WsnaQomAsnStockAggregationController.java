package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaQomAsnStockAggregationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0127M01 물량배정 재고이송량 집계 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-04
 */

@Api(tags = "[WSNA] 물량배정 재고이송량 집계")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/qom-asn-stock-aggs")
public class WsnaQomAsnStockAggregationController {

    private final WsnaQomAsnStockAggregationService service;

    @GetMapping
    @ApiOperation(value = "물량배정 재고이송량 집계 조회", notes = "물량배정 재고이송량 집계 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bsItmKndCd", value = "BS품목구분코드", paramType = "query", example = "5"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "cntGb", value = "회차구분", paramType = "query", example = "YNN", required = true),
        @ApiImplicitParam(name = "bsYn", value = "당월BS여부", paramType = "query", example = "Y", required = true),
        @ApiImplicitParam(name = "asnOjYm", value = "BS년월", paramType = "query", example = "202307", required = true),
        @ApiImplicitParam(name = "qomAsnGb", value = "물량배정구분", paramType = "query", example = "NN", required = true),
    })
    public List<SearchRes> getQomAsnStockAggs(@Valid
    SearchReq dto) {
        return this.service.getQomAsnStockAggs(dto);
    }

}
