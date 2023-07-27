package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WwfeaAccountNetIncreaseDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WwfeaAccountNetIncreaseService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WFEA] M조직 계정순증 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/account-net-increase")
@Slf4j
public class WfeaAccountNetIncreaseController {

    private final WwfeaAccountNetIncreaseService service;

    @ApiOperation(value = "M조직 계정순증 관리 - 조회", notes = "M조직 계정순증 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrDvCd", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "차수", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfYm", value = "수수료년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "cnclTpCd", value = "취소유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "aggregateTpCd", value = "취소유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", required = false),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping
    public List<WwfeaAccountNetIncreaseDto.SearchRes> getAccountNetIncrease(@Valid
    WwfeaAccountNetIncreaseDto.SearchReq dto) throws Exception {
        return service.getAccountNetIncrease(dto);
    }

    @ApiOperation(value = "M조직 계정순증 관리 - 계정순증집계", notes = "M조직 계정순증 관리 - 계정순증을 집계한다.")
    @PostMapping("/aggregates")
    public String aggregateAccountNetIncrease(
        @Valid @RequestBody
        WwfeaAccountNetIncreaseDto.SaveReq dto
    ) throws Exception {
        return service.aggregateAccountNetIncrease(dto);
    }
}
