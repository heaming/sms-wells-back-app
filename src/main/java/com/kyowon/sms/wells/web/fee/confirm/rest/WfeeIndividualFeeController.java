package com.kyowon.sms.wells.web.fee.confirm.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto;
import com.kyowon.sms.wells.web.fee.confirm.service.WfeeIndividualFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/individual-fees")
@Api(tags = "[WFEE] 수수료 개인 상세")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeeIndividualFeeController {
    private final WfeeIndividualFeeService service;

    @ApiOperation(value = "수수료 개인별 실적 상세 조회(공통)", notes = "조회조건 실적년월에 해당하는 사번의 개인별 상세 실적 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419", required = true),
    })
    @GetMapping("/details")
    public List<WfeeIndividualFeeDto.SearchRes> getIndividualPerformanceDetails(
        WfeeIndividualFeeDto.SearchReq dto
    ) {
        return this.service.getIndividualPerformanceDetails(dto);
    }
}
