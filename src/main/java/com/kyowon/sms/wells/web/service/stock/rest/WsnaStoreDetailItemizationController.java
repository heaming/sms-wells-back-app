package com.kyowon.sms.wells.web.service.stock.rest;

import com.kyowon.sms.wells.web.service.stock.service.WsnaStoreDetailItemizationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/store-detail-itemizations")
@Api(tags = "[WSNA] 입고상세내역 REST API")
@RequiredArgsConstructor
public class WsnaStoreDetailItemizationController {

    private final WsnaStoreDetailItemizationService service;

    @ApiOperation(value = "입고상세내역 조회", notes = "조회조건에 일치하는 입고상세내역 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stStrDt", value = "입고시작일", paramType = "query", example = "20230101", required = true),
        @ApiImplicitParam(name = "edStrDt", value = "입고종료일", paramType = "query", example = "20230117"),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "입고창고구분코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "pgGdCd", value = "등급코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목상품코드", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "", required = true),
    })
    @GetMapping
    public List<SearchRes> getStoreDetailItemizations(
        SearchReq dto
    ) {
        return this.service.getStoreDetailItemizations(dto);
    }

    @ApiOperation(value = "입고상세내역조회 엑셀 다운로드", notes = "조회조건에 일치하는 엑셀 다운로드용 입고상세내역 정보를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getStoreDetailItemizationExcelDownload(SearchReq dto) {
        return this.service.getStoreDetailItemizationExcelDownload(dto);
    }

}
