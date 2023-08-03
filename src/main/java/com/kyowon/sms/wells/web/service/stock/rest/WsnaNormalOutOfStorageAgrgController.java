package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.FindWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgWareDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaNormalOutOfStorageAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 정상출고집계현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/normal-out-of-storage-agrg")
public class WsnaNormalOutOfStorageAgrgController {

    private final WsnaNormalOutOfStorageAgrgService service;

    @ApiOperation(value = "정상출고집계현황", notes = "조회조건에 일치하는 정상출고집계현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startStrHopDt", value = "입고희망일 시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "endStrHopDt", value = "입고희망일 종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareLocaraCd", value = "창고지역코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrOjWareNo", value = "출고대상창고번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrCnfmYn", value = "출고확정코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "출고품목코드", paramType = "query"),
    })
    @GetMapping
    public List<HashMap<String, String>> getNormalOutOfStorageAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getNormalOutOfStorageAgrgs(dto);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "정상출고집계현황 창고 조회", notes = "정상출고집계현황 창고를 조회한다.")
    public List<WsnaOutOfStorageAgrgWareDvo> getWareHouses(
        @Valid
        SearchReq dto
    ) {
        return this.service.getWareHouses(dto);
    }

    @ApiOperation(value = "로그인사용자 창고조회", notes = "로그인사용자 창고를 조회한다.")
    @GetMapping("/login-ware-houses")
    public List<FindWarehouseRes> getLoginWarehouses(
        @Valid
        SearchReq dto
    ) {
        return service.getLoginWarehouses(dto);
    }

}
