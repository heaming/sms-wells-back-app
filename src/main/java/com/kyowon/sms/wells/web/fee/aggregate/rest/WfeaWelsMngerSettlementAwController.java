package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaWelsMngerSettlementAwDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaWelsMngerSettlementAwService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/wm-settlement-allowances")
@Api(tags = "[WFEA] 웰스매니저 정착수당")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeaWelsMngerSettlementAwController {
    private final WfeaWelsMngerSettlementAwService service;

    @ApiOperation(value = "웰스매니저 정착수당정보 조회\", notes = \"조회조건 실적년월에 해당하는 웰스매니저 정착수당 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "tcntDvCd", value = "차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrKnm", value = "이름", paramType = "query", required = false),
        @ApiImplicitParam(name = "schDiv", value = "구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "schRsbDvCd", value = "직급구분", paramType = "query", required = false),
    })

    @GetMapping
    public List<WfeaWelsMngerSettlementAwDto.SearchRes> getWelsMngers(
        @Valid
        WfeaWelsMngerSettlementAwDto.SearchReq dto
    ) {
        return this.service.getWelsMngers(dto);
    }

    @ApiOperation(value = "웰스매니저 정착수당정보 생성")
    @PostMapping("insert")
    public SaveResponse saveWelsMngerOpngs(
        @RequestBody
        @Valid
        WfeaWelsMngerSettlementAwDto.SaveOpngReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveWelsMngerOpngs(dto))
            .build();
    }

    @ApiOperation(value = "웰스매니저 정착수당 개시정보 수정", notes = "웰스매니저 정착수당 개시정보를 저장한다.")
    @PostMapping("update")
    public SaveResponse saveWelsMngers(
        @RequestBody
        @Valid
        List<WfeaWelsMngerSettlementAwDto.SaveReq> info
    ) {
        return SaveResponse.builder().processCount(service.saveWelsMngers(info)).build();
    }

    @ApiOperation(value = "웰스매니저 정착수당 개시정보 확정/취소", notes = "웰스매니저 정착수당 개시정보 확정정보를 저장한다.")
    @PostMapping("confirm")
    public SaveResponse saveWelsMngerConfirms(
        @RequestBody
        @Valid
        WfeaWelsMngerSettlementAwDto.SaveConfirmReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveWelsMngerConfirms(dto))
            .build();
    }
}
