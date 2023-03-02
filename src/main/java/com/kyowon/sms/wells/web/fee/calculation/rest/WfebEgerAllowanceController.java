package com.kyowon.sms.wells.web.fee.calculation.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.calculation.service.WfebEgerAllowanceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/eger-allowances")
@Api(tags = "[WFEB] 엔지니어 수당 생성관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfebEgerAllowanceController {

    private final WfebEgerAllowanceService service;

    @ApiOperation(value = "엔지니어 수당 목록 조회", notes = "조회조건에 일치하는 실적년월에 생성된 엔지니어 수당 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "rsbTp", value = "직책유형", paramType = "query", example = "엔지니어", required = true),
        @ApiImplicitParam(name = "ogLevl1", value = "조직레벨1", paramType = "query", example = "경남서비스센터", required = true),
        @ApiImplicitParam(name = "ogLevl2", value = "조직레벨2", paramType = "query", example = "부산지점", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419"),
    })
    @GetMapping("/engineers")
    public List<WfebEgerAllowanceDto.SearchEngineerRes> getEngineerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    ) {
        return this.service.getEngineerAllowances(dto);
    }

    @ApiOperation(value = "엔지니어 관리자 수당 목록 조회", notes = "조회조건에 일치하는 실적년월에 생성된 엔지니어 관리자 수당 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "rsbTp", value = "직책유형", paramType = "query", example = "엔지니어", required = true),
        @ApiImplicitParam(name = "ogLevl1", value = "조직레벨1", paramType = "query", example = "경남서비스센터", required = true),
        @ApiImplicitParam(name = "ogLevl2", value = "조직레벨2", paramType = "query", example = "부산지점", required = true),
        @ApiImplicitParam(name = "no", value = "번호", paramType = "query", example = "1673419"),
    })
    @GetMapping("/engineer-managers")
    public List<WfebEgerAllowanceDto.SearchEngineerManagerRes> getEngineerManagerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    ) {
        return this.service.getEngineerManagerAllowances(dto);
    }

}
