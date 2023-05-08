package com.kyowon.sms.wells.web.fee.calculation.rest;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebB2bFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.service.WfebB2bFeeMgtService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WFEB] B2B 수수료 생성관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/b2b")
@Slf4j
public class WfebB2bFeeMgtController {

     private final WfebB2bFeeMgtService service;

    @ApiOperation(value = "B2B 수수료 생성관리 - 조회(수수료 실적)", notes = "B2B 수수료 생성관리의 수수료 실적을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "strtYm", value = "접수년월 시작월", paramType = "query", required = true),
        @ApiImplicitParam(name = "endYm", value = "접수년월 종료월", paramType = "query", required = true),
        @ApiImplicitParam(name = "cancelStrtYm", value = "취소년월 시작월", paramType = "query"),
        @ApiImplicitParam(name = "cancelEndYm", value = "취소년월 종료월", paramType = "query"),
    })
    @GetMapping("/performance")
    public List<Performance> getB2bPerformance(@Valid SearchPerformanceReq req) throws Exception {
        return service.getB2bPerformance(req);
    }

    @ApiOperation(value = "B2B 수수료 생성관리 - 조회(수수료 실적)", notes = "B2B 수수료 생성관리의 수수료 실적을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
    })
    @GetMapping("/fee")
    public List<Fee> getB2bFee(@Valid SearchFeeReq req) throws Exception {
        return service.getB2bFee(req);
    }

    @ApiOperation(value = "B2B 수수료 생성관리 - 저장(수수료)", notes = "B2B 수수료 생성관리의 수수료 실적을 수정한다.")
    @PostMapping("/fee")
    public SaveResponse editB2bFee(@RequestBody @Valid SaveReq listFees) throws Exception {
        return SaveResponse.builder().processCount(service.editB2bFee(listFees)).build();
    }

    @ApiOperation(value = "B2B 수수료 생성관리 - 집계", notes = "B2B 수수료 생성관리의 데이터를 집계한다.")
    @PostMapping("/aggregate")
    public SaveResponse aggregateB2bPerformance(@RequestBody @Valid CreateReq req) throws Exception {
        return SaveResponse.builder().processCount(service.aggregateB2bPerformance(req)).build();
    }


}
