package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageAskMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/out-of-storage-asks")
@Api(tags = "[WSNA] 출고요청 관리 REST API")
@RequiredArgsConstructor
public class WsnaOutOfStorageAskMngtController {

    private final WsnaOutOfStorageAskMngtService service;

    @ApiOperation(value = "출고요청 조회", notes = "조회조건에 일치하는 출고요청 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strOjWareNo", value = "출고요청창고", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "ostrAkTpCd", value = "출고요청유형코드", paramType = "query", example = "310"),
        @ApiImplicitParam(name = "startStrHopDt", value = "입고희망일자 시작일", paramType = "query", example = "20221128", required = true),
        @ApiImplicitParam(name = "endStrHopDt", value = "입고희망일자 종료일", paramType = "query", example = "20221128", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "출고요청 접수창고", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "wareLocaraCd", value = "창고지역코드", paramType = "query", example = "")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getOutOfStorageAsks(WsnaOutOfStorageAskMngtDto.SearchReq dto, PageInfo pageInfo) {
        return this.service.getOutOfStorageAsks(dto, pageInfo);
    }

    @ApiOperation(value = "출고요청등록 상단영역 조회", notes = "부모창에서 넘어온 파라미터로 출고요청 등록 상단영역을 조회.")
    @GetMapping("/out-of-storage-ask-items")
    public FindRes getOutOfStorageAskItems(FindReq dto) {

        return this.service.getOutOfStorageAskItems(dto);
    }

    @ApiOperation(value = "출고요청등록 하단영역 조회", notes = "부모창에서 넘어온 파라미터로 출고요청 등록 하단영역을 조회")
    @GetMapping("/out-of-storage-items/paging")
    public PagingResult<WsnaOutOfStorageAskMngtDto.OutOfRes> getOutOfStorageItemPages(
        WsnaOutOfStorageAskMngtDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {

        return this.service.getOutOfStorageItemPages(dto, pageInfo);
    }

    @ApiOperation(value = "출고대상창고 조회", notes = "조건에 맞는 출고대상창고들을 조회한다.")
    @GetMapping("/ostr-object-warehouses")
    public List<SearchOstrObjectWarehouseRes> getOstrObjectWarehouses(SearchOstrObjectWarehouseReq dto) {

        return this.service.getOstrObjectWarehouses(dto);
    }

    @ApiOperation(value = "출고요청 삭제", notes = "선택한 출고요청을 삭제한다.")
    @DeleteMapping
    public int removeOutOfStorageAskItems(
        @Valid
        @RequestBody
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return this.service.removeOutOfStorageAskItems(dtos);
    }

    @ApiOperation(value = "출고요청 저장", notes = "출고요청을 저장한다.")
    @PostMapping
    public SaveResponse saveOutOfStorageAskItems(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveOutOfStorageAskItems(dtos))
            .build();
    }
}
