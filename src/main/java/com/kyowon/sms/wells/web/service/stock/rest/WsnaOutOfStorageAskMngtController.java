package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageAskMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.Warehouse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

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
    @GetMapping
    public List<SearchRes> getOutOfStorageAsks(WsnaOutOfStorageAskMngtDto.SearchReq dto) {
        return this.service.getOutOfStorageAsks(dto);
    }

    @ApiOperation(value = "출고요청관리 출고요청창고 조회", notes = "조회조건인 출고요청창고를 로그인한 사용자의 아이디로 조회.")
    @GetMapping("/warehouses")
    public List<Warehouse> getOutOfStorageAskWares(WsnaOutOfStorageAskMngtDto.WarehouseReq dto) {
        return this.service.getOutOfStorageAskWares(dto);
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

}
