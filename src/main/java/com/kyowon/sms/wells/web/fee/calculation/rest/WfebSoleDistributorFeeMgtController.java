package com.kyowon.sms.wells.web.fee.calculation.rest;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebSoleDistributorFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.calculation.service.WfebSoleDistributorFeeMgtService;
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

@Api(tags = "[WFEB] 총판수수료 생성관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/sole-distributor")
@Slf4j
public class WfebSoleDistributorFeeMgtController {

     private final WfebSoleDistributorFeeMgtService service;

    @ApiOperation(value = "총판수수료 생성관리 - 조회(수수료 실적)", notes = "총판수수료 생성관리의 수수료 실적을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "strtYm", value = "접수년월 시작월", paramType = "query", required = true),
        @ApiImplicitParam(name = "endYm", value = "접수년월 종료월", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query"),
        @ApiImplicitParam(name = "cancelStrtYm", value = "취소년월 시작월", paramType = "query"),
        @ApiImplicitParam(name = "cancelEndYm", value = "취소년월 종료월", paramType = "query"),
    })
    @GetMapping("/performance")
    public List<Performance> getDistributorPerformance(@Valid SearchPerformanceReq req) throws Exception {
        return service.getDistributorPerformance(req);
    }

    @ApiOperation(value = "총판수수료 생성관리 - 조회(수수료 실적)", notes = "총판수수료 생성관리의 수수료 실적을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query"),
    })
    @GetMapping("/fee")
    public List<Fee> getDistributorFee(@Valid SearchFeeReq req) throws Exception {
        return service.getDistributorFee(req);
    }

    @ApiOperation(value = "총판수수료 생성관리 - 저장(수수료)", notes = "총판수수료 생성관리의 수수료 실적을 수정한다.")
    @PostMapping("/fee")
    public SaveResponse editDistributorFee(@RequestBody @Valid SaveReq listFees) throws Exception {
        return SaveResponse.builder().processCount(service.editDistributorFee(listFees)).build();
    }

    @ApiOperation(value = "총판수수료 생성관리 - 집계", notes = "총판수수료 생성관리의 데이터를 집계한다.")
    @PostMapping("/aggregate")
    public SaveResponse aggregateDistributorPerformance(@RequestBody @Valid CreateReq req) throws Exception {
        return SaveResponse.builder().processCount(service.aggregateDistributorPerformance(req)).build();
    }


}
