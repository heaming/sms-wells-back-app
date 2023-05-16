package com.kyowon.sms.wells.web.fee.simulation.rest;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgMRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefEstimateFeeMgtDto.SearchOgPRes;
import com.kyowon.sms.wells.web.fee.simulation.service.WfefEstimateFeeMgtService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
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

@Api(tags = "[WFEF] 예상수수료 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/estimate")
@Slf4j
public class WfefEstimateFeeMgtController {

     private final WfefEstimateFeeMgtService service;

    @ApiOperation(value = "예상수수료 - 조회(p조직)", notes = "예상수수료 p조직을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "perType", value = "실적조회", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/p-og")
    public SearchOgPRes getEstimateFeeOgP(@Valid SearchOgPReq req) throws Exception {
        return service.getEstimateFeeOgP(req);
    }

    @ApiOperation(value = "예상수수료 - 조회(m조직)", notes = "예상수수료 m조직을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "perType", value = "실적조회", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/m-og")
    public SearchOgMRes getEstimateFeeOgM(@Valid SearchOgPReq req) throws Exception {
        return service.getEstimateFeeOgM(req);
    }

}
