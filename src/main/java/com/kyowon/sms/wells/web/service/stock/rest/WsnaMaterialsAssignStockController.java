package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaMaterialsAssignStockService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/materials-assign-stocks")
@Api(tags = "[WSNA] 물량배정 입고창고관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnaMaterialsAssignStockController {

    private final WsnaMaterialsAssignStockService service;

    @GetMapping("/paging")
    @ApiOperation(value = "물량배정 입고창고 페이징 조회", notes = "물량배정 입고창고를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "307002"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "308647"),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "31"),
        @ApiImplicitParam(name = "prtnrNo", value = "사번", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "prtnrKnm", value = "담당자명", paramType = "query", example = "김")
    })
    public PagingResult<SearchRes> getMaterialsAssignStocksPaging(@Valid
    SearchReq dto, @Valid
    PageInfo pageInfo
    ) {
        return this.service.getMaterialsAssignStocksPaging(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "물량배정 입고창고 엑셀 다운로드", notes = "조회조건에 해당하는 물량배정 입고창고 데이터를 엑셀 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분코드", paramType = "query", example = "3", required = true),
        @ApiImplicitParam(name = "hgrWareNo", value = "상위창고번호", paramType = "query", example = "307002"),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = "308647"),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고세부구분코드", paramType = "query", example = "31"),
        @ApiImplicitParam(name = "prtnrNo", value = "사번", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "prtnrKnm", value = "담당자명", paramType = "query", example = "김")
    })
    public List<SearchRes> selectMaterialsAssignStocksExcelDownload(@Valid
    SearchReq dto) {
        return this.service.selectMaterialsAssignStocksExcelDownload(dto);
    }

    @PostMapping
    @ApiOperation(value = "물량배정 입고창고 저장", notes = "물량배정 입고창고 데이터를 저장한다.")
    public SaveResponse createMaterialsAssignStocks(
        @RequestBody
        @Valid
        @NotEmpty
        List<CreateReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.createMaterialsAssignStocks(dtos)).build();
    }
}
