package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.*;
import com.kyowon.sms.wells.web.closing.performance.service.WdccSalesPerformService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Api(tags = "[WDCC] 매출실적현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-performs")
public class WdccSalesPerformController {

    private final WdccSalesPerformService service;

    @ApiOperation(value = "매출실적현황", notes = "매출실적현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "fromSlClYy", value = "검색시작일", paramType = "query"),
        @ApiImplicitParam(name = "toSlClYy", value = "검색종료일", paramType = "query"),
    })
    @GetMapping
    public PagingResult<SearchRes> getSalesPerformancePresentStatePages(
        @Valid
        SearchReq req
    ) {
        return service.getSalesPerformancePresentStatePages(req);
    }

}
