package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaWarehouseOrganizationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/warehouse-organizations")
@Api(tags = "[WSNA] 창고조직관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaWarehouseOrganizationController {

    private final WsnaWarehouseOrganizationService service;

    @ApiOperation(value = "창고조직 관리", notes = "조회조건에 일치하는 창고조직 관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDv", value = "창고구분", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "codeUseYn", value = "사용여부", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareLocaraCd", value = "창고지역코드", paramType = "query", example = ""),
    })

    @GetMapping
    public List<SearchRes> getWarehouseOgs(SearchReq dto) {
        return this.service.getWarehouseOgs(dto);
    }

    @ApiOperation(value = "창고조직 관리 목록 엑셀 다운로드", notes = "조회조건에 일치하는 창고조직 관리 데이터를 엑셀다운로드 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "wareDv", value = "창고구분", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "codeUseYn", value = "사용여부", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareLocaraCd", value = "창고지역코드", paramType = "query", example = ""),
    })
    @GetMapping("excel-download")
    public List<SearchRes> getWarehouseOgsExcelDownload(
        SearchReq dto
    ) {
        return this.service.getWarehouseOgsExcelDownload(dto);

    }

    @ApiOperation(value = "창고조직이월 조회", notes = "기준년월에 일치하는 창고조직 관리 데이터를 카운트한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
    })
    @GetMapping("/carried-over")
    public int getWareCarriedCounter(CountReq dto) {
        return this.service.getWareCarriedCounter(dto);
    }

    @ApiOperation(value = "창고조직이월 저장", notes = "기준년월에 일치하는 창고조직 관리 데이터를 저장한다.")
    @PostMapping("/carried-over")
    public SaveResponse createWareCarried(
        @Valid
        @RequestBody
        CreateReq dto
    ) {
        return SaveResponse.builder()
            .processCount(this.service.createWareCarried(dto))
            .build();
    }

    @ApiOperation(value = "창고조직 조회", notes = "창고조직에 대한 정보를 조회한다.")
    @ApiImplicitParam(name = "apyYmWareNo", value = "기준년월창고번호", paramType = "path", example = "202302201245", required = true)
    @GetMapping("/{apyYmWareNo}")
    public FindRes getWarehouseOg(
        @PathVariable
        String apyYmWareNo
    ) {
        return this.service.getWarehouseOg(apyYmWareNo);
    }

    @ApiOperation(value = "창고조직 등록", notes = "창고조직에 대한 정보를 등록 및 수정한다.")
    @PostMapping
    public SaveResponse saveWarehouseOg(
        @Valid
        @RequestBody
        SaveReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.saveWarehouseOg(dto)).build();
    }

}
