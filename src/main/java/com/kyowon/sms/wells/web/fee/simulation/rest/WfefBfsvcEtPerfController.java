package com.kyowon.sms.wells.web.fee.simulation.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefBfsvcEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefBfsvcEtPerfDto.SearchRes;
import com.kyowon.sms.wells.web.fee.simulation.service.WfefBfsvcEtPerfService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WFEF] BS 예상 실적 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/bs-estimate-performance")
@Slf4j
public class WfefBfsvcEtPerfController {

    private final WfefBfsvcEtPerfService service;

    @ApiOperation(value = "BS 예상실적 조회", notes = "BS 예상실적을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtPerfDt", value = "시작실적일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endPerfDt", value = "종료실적일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "총괄단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd3", value = "지점", paramType = "query", required = false),
    })
    @GetMapping
    public List<SearchRes> getBsEstimatePerformance(SearchReq dto) {
        return service.getBsEstimatePerformance(dto);
    }
}
