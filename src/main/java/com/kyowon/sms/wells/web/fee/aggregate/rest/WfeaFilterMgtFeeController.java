package com.kyowon.sms.wells.web.fee.aggregate.rest;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFilterMgtFeeDto.SearchReq;
import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFilterMgtFeeDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaFilterMgtFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WFEA]수수료 기준금액 체크리스트")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/filter-mgt-fees")
@Slf4j
public class WfeaFilterMgtFeeController {

    private final WfeaFilterMgtFeeService service;

    @ApiOperation(value = "필터관리수수료 현황 목록 조회", notes = "조회조건에 따른 필터관리수수료 현황 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "param1", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "param2", value = "업무구분", paramType = "query", required = true),
    })

    @GetMapping
    public List<SearchRes> getFilterMgtFees(
        @Valid
        SearchReq dto
    ) {
        return this.service.getFilterMgtFees(dto);
    }

}
