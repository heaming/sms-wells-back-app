package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyByStockPsDto.*;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchPdRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaMonthlyByStockPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0136M01 월별 재고현황 Controller
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-07
 */

@Api(tags = "[WSNA] 월별 재고현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/monthly-by-stock-state")
public class WsnaMonthlyByStockPsController {

    private final WsnaMonthlyByStockPsService service;

    @GetMapping("/products")
    @ApiOperation(value = "품목 조회", notes = "품목을 조회한다.")
    public List<SearchPdRes> getProducts() {
        return this.service.getProducts();
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "창고 조회", notes = "월별 재고현황 창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202308", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200609")
    })
    public List<SearchWareRes> getMonthlyStateWareHouses(@Valid
    SearchWareReq dto) {
        return this.service.getMonthlyStateWareHouses(dto);
    }

    @GetMapping
    @ApiOperation(value = "월별 재고현황 조회", notes = "월별 재고현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202308", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "200609"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "200898"),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "21"),
        @ApiImplicitParam(name = "itmGdCd", value = "상품등급코드", paramType = "query", example = "A"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "4"),
        @ApiImplicitParam(name = "itmPdCds", value = "품목코드 리스트", paramType = "query", example = "[WM07102157]"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목코드", paramType = "query", example = "WM07102157"),
        @ApiImplicitParam(name = "strtSapCd", value = "시작 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "endSapCd", value = "종료 SAP코드", paramType = "query", example = "300006248"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query", example = "01")
    })
    public List<SearchRes> getMonthlyByStocksState(@Valid
    SearchReq dto) {
        return this.service.getMonthlyByStocksState(dto);
    }

}
