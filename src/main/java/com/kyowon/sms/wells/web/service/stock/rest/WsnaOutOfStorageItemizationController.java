package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageItemizationDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageItemizationDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageItemizationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/out-of-storage-itemizations")
@Api(tags = "[WSNA] 출고관리 REST API")
@RequiredArgsConstructor
public class WsnaOutOfStorageItemizationController {

    private final WsnaOutOfStorageItemizationService service;

    @GetMapping("/paging")
    @ApiOperation(value = "출고관리 목록 조회", notes = "조회조건에 일치하는 출고관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "시작출고일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "종료출고일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "221"),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "200001", required = true),
        @ApiImplicitParam(name = "strWareDvCd", value = "입고창고구분코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "strWareNoM", value = "입고상위창고번호", paramType = "query", example = "200001"),
        @ApiImplicitParam(name = "strWareNoD", value = "입고창고번호", paramType = "query", example = "201477")
    })
    public PagingResult<SearchRes> getOutOfStorageItemizations(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo) {
        return this.service.getOutOfStorageItemizations(dto, pageInfo);
    }

    @ApiOperation(value = "출고관리 목록 엑셀 다운로드", notes = "조회조건에 일치하는 출고관리 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stOstrDt", value = "시작출고일자", paramType = "query", example = "20211001", required = true),
        @ApiImplicitParam(name = "edOstrDt", value = "종료출고일자", paramType = "query", example = "20211031", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query", example = "221"),
        @ApiImplicitParam(name = "ostrWareNo", value = "출고창고번호", paramType = "query", example = "200001", required = true),
        @ApiImplicitParam(name = "strWareDvCd", value = "입고창고구분코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "strWareNoM", value = "입고상위창고번호", paramType = "query", example = "200001"),
        @ApiImplicitParam(name = "strWareNoD", value = "입고창고번호", paramType = "query", example = "201477")
    })
    @GetMapping("excel-download")
    public List<SearchRes> getOutOfStorageItemizationExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getOutOfStorageItemizationExcelDownload(dto);
    }

}
