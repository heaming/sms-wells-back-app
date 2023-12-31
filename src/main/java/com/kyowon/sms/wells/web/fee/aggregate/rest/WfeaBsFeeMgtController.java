package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaBsFeeMgtDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaBsFeeMgtService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/bs-fees")
@Api(tags = "[WFEA] BS 실적 및 수수료")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeaBsFeeMgtController {

    private final WfeaBsFeeMgtService service;

    @ApiOperation(value = "BS 수수료 내역 조회", notes = "조회조건에 일치하는 실적년월에 생성된 BS 수수료 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형", paramType = "query", example = "M조직", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "수수료차수구분코드", paramType = "query", example = "01", required = true),
        @ApiImplicitParam(name = "strtPdCd", value = "상품코드", paramType = "query", example = "4130"),
        @ApiImplicitParam(name = "endPdCd", value = "상품코드", paramType = "query", example = "4130"),
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202304", required = true),
        @ApiImplicitParam(name = "strtVstDt", value = "방문일자", paramType = "query", example = "20230401"),
        @ApiImplicitParam(name = "endVstDt", value = "방문일자", paramType = "query", example = "20230430"),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })
    @GetMapping("/list")
    public List<WfeaBsFeeMgtDto.SearchRes> getBsFees(
        WfeaBsFeeMgtDto.SearchReq dto
    ) {
        return service.getBsFees(dto);
    }

    @ApiOperation(value = "BS 실적 집계", notes = "BS실적을 집계한다.")
    @PostMapping
    public String saveBsAggregates(
        @RequestBody
        @Valid
        WfeaBsFeeMgtDto.SaveReq dto
    ) throws Exception {
        return service.saveBsAggregates(dto);
    }

    @ApiOperation(value = "BS실적 집계 여부 체크", notes = "BS실적 집계 여부 체크한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfAgrgCrtDvCd", value = "", paramType = "query", required = true),
    })
    @GetMapping("/check")
    public WfeaBsFeeMgtDto.SearchCheckRes getFeeNetOrderStat(
        @Valid
        WfeaBsFeeMgtDto.SearchCheckReq dto
    ) {
        return service.getFeeAgrgStat(dto);
    }

}
