package com.kyowon.sms.wells.web.fee.calculation.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebOutcomeAllowanceDto.*;

import com.kyowon.sms.wells.web.fee.calculation.service.WfebOutcomeAllowanceService;

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

    @ApiOperation(value = "WELLS 성과수당현황 리스트 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfDt", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "leaderDiv", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "levelDiv", value = "", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getOutcomeAllowances(
        @Valid
        SearchReq dto
    ) {
        return service.getOutcomeAllowances(dto);
    }

}
