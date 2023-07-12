package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaInstallationStockPsByDayDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaInstallationStockPsByDayService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-stock-by-day")
@Api(tags = "[WSNA] 일자별 설치재고 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaInstallationStockPsByDayController {

    private final WsnaInstallationStockPsByDayService service;

    @ApiOperation(value = "일자별 설치재고 현황 조회", notes = "조회일자를 기준으로 14일 범위의 일자별 설치재고 집계 현황을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getReturningGoodsOstrAgrg(
        @Valid
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getInstallationStockPsByDay(dto, pageInfo);
    }

    @ApiOperation(value = "일자별 설치재고 현황 엑셀다운로드", notes = "조회일자를 기준으로 14일 범위의 일자별 설치재고 집계 현황을 엑셀 다운로드한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getReturningGoodsOstrAgrg(
        @Valid
        SearchReq dto
    ) {
        return service.getInstallationStockPsByDay(dto);
    }
}
