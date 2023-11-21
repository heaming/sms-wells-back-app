package com.kyowon.sms.wells.web.service.common.rest;

import com.kyowon.sms.wells.web.service.common.dto.WsnyProcPsDto.*;
import com.kyowon.sms.wells.web.service.common.service.WsnyProcPsService;
import com.sds.sflex.system.config.constant.CommConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * [W-SV-U-1301M01] 홈카드 - 처리 현황
 * </pre>
 *
 * @author 김동엽
 * @since 2023-11-16
 */
@Api(tags = "[WSNF] 홈카드 처리 현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/proc-ps")
public class WsnyProcPsController {
    private final WsnyProcPsService service;

    @ApiOperation(value = "Home Card 처리 현황 조회", notes = "일간.월간 처리 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rsbCd", value = "직책코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "searchType", value = "조회조건 일간.월간", paramType = "query", required = true),
    })
    @GetMapping("/homecard-proc-ps")
    public SearchRes getHomeCardProcPs(SearchReq dto) {
        return service.selectProcPs(dto);
    }

    @ApiOperation(value = "Home Card 오늘.내일 배정건수 조회", notes = "오늘.내일 배정 건수 조회")
    @GetMapping("/homecard-search-cnt")
    public SearchCnt selectTodayTomorrowCnt(SearchReq dto) {
        return service.selectTodayTomorrowCnt(dto);
    }
}
