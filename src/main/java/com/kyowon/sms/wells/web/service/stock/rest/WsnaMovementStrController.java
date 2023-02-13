package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStrDto;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStrDto.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaMovementStrService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 이동입고현황 REST API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/movement-store-pss")
public class WsnaMovementStrController {

    private final WsnaMovementStrService service;

    @ApiOperation(value = "이동입고현황 조회", notes = "조회조건에 해당하는 이동입고 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNoM", value = "출고창고번호마스터", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "ostrWareNoD", value = "출고창고번호디테일", paramType = "query"),

    })
    @GetMapping()
    public List<WsnaMovementStrDto.SearchRes> getMovementStorePss(
        @Valid
        SearchReq dto
    ) {
        return service.getMovementStorePss(dto);
    }

    @ApiOperation(value = "이동입고현황 엑셀 다운로드", notes = "조회조건에 해당하는 이동입고 현황을 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "strOjWareNo", value = "입고창고번호", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "2", required = true),
        @ApiImplicitParam(name = "ostrWareNoM", value = "출고창고번호마스터", paramType = "query", example = "200371", required = true),
        @ApiImplicitParam(name = "ostrWareNoD", value = "출고창고번호디테일", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getMovementStrExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMovementStrExcelDownload(dto);
    }
}
