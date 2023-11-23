package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStateIndividualWareDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStateIndividualWareWareDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaStockStateIndividualWareService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] 재고현황(개인창고) REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/stock-state-individual-ware")
public class WsnaStockStateIndividualWareController {
    @ApiOperation(value = "재고현황(개인창고) 목록 조회", notes = "조회조건에 일치하는 재고현황(개인창고) 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stockDt", value = "재고일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "baseYm", value = "기준일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareUseYn", value = "창고사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmKindCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "stndUnuseYn", value = "표준미사용여부", paramType = "query"),
    })
    @GetMapping
    public List<HashMap<String, String>> getStockStateIndividualWares(
        @Valid
        SearchReq dto
    ) {
        return service.getStockStateIndividualWare(dto);
    }

    private final WsnaStockStateIndividualWareService service;

    @GetMapping("/ware-houses")
    @ApiOperation(value = "세부 창고 조회", notes = "세부 창고를 조회한다.")
    public List<WsnaStockStateIndividualWareWareDvo> getWareHouseNames(SearchReq dto) {

        return this.service.getWareHouses(dto);
    }

    @GetMapping("/service_center")
    @ApiOperation(value = "서비스센터 조회", notes = "서비스센터를 조회한다.")
    public List<HashMap<String, String>> getServiceCenter(String baseYm) {

        return service.getServiceCenter(baseYm);
    }

}
