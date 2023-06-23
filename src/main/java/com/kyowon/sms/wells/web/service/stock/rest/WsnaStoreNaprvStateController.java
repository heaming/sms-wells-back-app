package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaStoreNaprvStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/store-not-approve")
@Api(tags = "[WSNA] 입고 미승인 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaStoreNaprvStateController {

    private final WsnaStoreNaprvStateService service;

    @ApiOperation(value = "입고 미승인현황 조회", notes = "조회조건에 일치하는 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strWareDvCd", value = "입고창고구분코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "strWareNoM", value = "상위창고코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "strWareNoD", value = "창고코드", paramType = "query", example = "201522"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getStoreNaprvStateDtl(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getStoreNaprvState(dto, pageInfo);
    }

    @ApiOperation(value = "입고 미승인현황 엑셀다운로드", notes = "검색 조건을 입력받아 엑셀 다운로드용 데이터를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getStoreNaprvStateExcelDownload(
        SearchReq dto
    ) {
        return this.service.getStoreNaprvState(dto);
    }
}
