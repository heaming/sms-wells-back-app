package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyOutOfStorageAgrgDto.FindWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMonthlyOutOfStorageAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 월별출고집계현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/monthly-out-of-storage-agrg")
public class WsnaMonthlyOutOfStorageAgrgController {

    private final WsnaMonthlyOutOfStorageAgrgService service;

    @ApiOperation(value = "월별출고집계현황", notes = "조회조건에 일치하는 월별출고집계현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "출고시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query"),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query"),
        @ApiImplicitParam(name = "wareUseYn", value = "창고사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
    })
    @GetMapping
    public List<HashMap<String, String>> getMonthlyOutOfStorageAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getMonthlyOutOfStorageAgrgs(dto);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "월별출고집계현황 창고 조회", notes = "월별출고집계현황 창고 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query")
    })
    public List<FindWarehouseRes> getWareHouses(
        @Valid
        SearchReq dto
    ) {
        return this.service.getWareHouses(dto);
    }

}
