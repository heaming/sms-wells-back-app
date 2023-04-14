package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchAmountRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.SearchSummaryRes;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdOperatingCostMgtService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCD] 운영비 등록 관리")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/operating-cost")
public class WdcdOperatingCostMgtController {

    private final WdcdOperatingCostMgtService service;

    @ApiOperation(value = "운영비 등록 관리 - 운영비 금액 현황", notes = "운영비 등록 관리 - 운영비 금액 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYM", value = "사용년월", paramType = "query"),
        @ApiImplicitParam(name = "entrpDvCd", value = "사업자 구분코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr4LevlOgId", value = "센터단", paramType = "query"),
    })
    @GetMapping("/amount")
    public List<SearchAmountRes> getAmount(@Valid
                                           SearchReq req) {

        return service.getAmount(req);
    }

    @ApiOperation(value = "운영비 등록 관리 - 운영비 금액 현황", notes = "운영비 등록 관리 - 운영비 금액 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYM", value = "사용년월", paramType = "query"),
        @ApiImplicitParam(name = "entrpDvCd", value = "사업자 구분코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr4LevlOgId", value = "센터단", paramType = "query"),
    })
    @GetMapping("/summary")
    public List<SearchSummaryRes> getSummary(@Valid
                                             SearchReq req) {

        return service.getSummary(req);
    }

}
