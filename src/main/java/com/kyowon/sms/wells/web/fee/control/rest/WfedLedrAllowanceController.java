package com.kyowon.sms.wells.web.fee.control.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.control.dto.WfedLedrAllowanceDto;
import com.kyowon.sms.wells.web.fee.control.service.WfedLedrAllowanceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/ledr-allowances")
@Api(tags = "[WFED] 단장 수당 관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfedLedrAllowanceController {

    private final WfedLedrAllowanceService service;

    @ApiOperation(value = "단장 수당 목록 조회(개인별)", notes = "조회조건에 일치하는 실적년월에 생성된 단장 수당 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책유형", paramType = "query", example = "총괄단장"),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "123456"),
    })
    @GetMapping("/indv")
    public List<WfedLedrAllowanceDto.SearchIndividualRes> getIndividualLeaderAllowances(
        WfedLedrAllowanceDto.SearchReq dto
    ) {
        return service.getIndividualLeaderAllowances(dto);
    }

    @ApiOperation(value = "단장 수당 목록 조회(합계)", notes = "조회조건에 일치하는 실적년월에 생성된 단장 수당 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책유형", paramType = "query", example = "총괄단장"),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "123456"),
    })
    @GetMapping("/sum")
    public List<WfedLedrAllowanceDto.SearchSumRes> getSumLeaderAllowances(WfedLedrAllowanceDto.SearchReq dto) {
        return service.getSumLeaderAllowances(dto);
    }

}
