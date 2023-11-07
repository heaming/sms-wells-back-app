package com.kyowon.sms.wells.web.fee.calculation.rest;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.calculation.service.WfebOutcomeAllowanceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WFEB] WELLS 성과수당현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/outcome-allowances")
public class WfebOutcomeAllowanceController {

    private final WfebOutcomeAllowanceService service;

    @ApiOperation(value = "WELLS M조직 성과수당현황 리스트 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "thmInqr", value = "", paramType = "query", required = true),
    })
    @GetMapping("/manager")
    public List<SearchManagerRes> getOutcomeAllowancesManager(
        @Valid
        SearchReq dto
    ) {
        return service.getOutcomeAllowancesManager(dto);
    }

    @ApiOperation(value = "WELLS P조직 성과수당현황 리스트 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "", paramType = "query", required = false),
        @ApiImplicitParam(name = "thmInqr", value = "", paramType = "query", required = true),
    })
    @GetMapping("/planner")
    public List<SearchPlannerRes> getOutcomeAllowancesPlanner(
        @Valid
        SearchReq dto
    ) {
        return service.getOutcomeAllowancesPlanner(dto);
    }

}
