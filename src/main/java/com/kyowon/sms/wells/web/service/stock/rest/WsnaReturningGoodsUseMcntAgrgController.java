package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsUseMcntAgrgDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsUseMcntAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] 반품 사용개월별 집계 관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/returning-goods-use-mcnt-agrg")

public class WsnaReturningGoodsUseMcntAgrgController {

    private final WsnaReturningGoodsUseMcntAgrgService service;

    @ApiOperation(value = "반품 사용개월별 집계 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmGdCd", value = "상품등급", paramType = "query", example = "R"),
        @ApiImplicitParam(name = "startDt", value = "시작일자", paramType = "query", example = "20230720"),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", example = "20230720"),
        @ApiImplicitParam(name = "serviceCenter", value = "서비스센터", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "업무유형", paramType = "query", example = "3260"),
    })
    @GetMapping
    public List<WsnaReturningGoodsUseMcntAgrgDto.SearchRes> getReturningGoodsUseMcntAgrg(
        @Valid
        WsnaReturningGoodsUseMcntAgrgDto.SearchReq dto
    ) {
        return service.getReturningGoodsUseMcntAgrg(dto);
    }

    @ApiOperation(value = "반품 사용개월별 집계 조회 (엑셀 다운로드)", notes = "반품 사용개월별 집계를 조회한다.")
    @GetMapping("/excel-download")
    public List<WsnaReturningGoodsUseMcntAgrgDto.SearchRes> getReturningGoodsUseMcntAgrgForExcelDownload(
        @Valid
        WsnaReturningGoodsUseMcntAgrgDto.SearchReq dto
    ) {
        return service.getReturningGoodsUseMcntAgrgForExcelDownload(dto);
    }

}
