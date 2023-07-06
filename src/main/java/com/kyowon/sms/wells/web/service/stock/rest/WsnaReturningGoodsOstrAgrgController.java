package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsOstrAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/returning-goods-out-of-storages-agrg")
@Api(tags = "[WSNA] 반품출고집계현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaReturningGoodsOstrAgrgController {

    private final WsnaReturningGoodsOstrAgrgService service;

    @ApiOperation(value = "반품출고집계현황 조회", notes = "반품으로 출고된 상품들의 집계 현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getReturningGoodsOstrAgrg(
        @Valid
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getReturningGoodsOstrAgrg(dto, pageInfo);
    }

    @ApiOperation(value = "반품출고집계현황 엑셀다운로드", notes = "반품으로 출고된 상품들의 집계 현황을 엑셀 다운로드한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getReturningGoodsOstrAgrg(
        @Valid
        SearchReq dto
    ) {
        return service.getReturningGoodsOstrAgrg(dto);
    }
}
