package com.kyowon.sms.wells.web.fee.calculation.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOrganizationFeeDto.*;
import com.kyowon.sms.wells.web.fee.calculation.service.WfebOrganizationFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WFEB] 수수료 생성관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/organization-fees")
@Slf4j
public class WfebOrganizationFeeController {
    private final WfebOrganizationFeeService service;

    @ApiOperation(value = "홈마스터 수수료 생성관리 목록 조회", notes = "조회조건에 따른 홈마스터 수수료 생성관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/hmsts")
    public List<SearchHmstRes> getHomeMasterFees(
        @Valid
        SearchHmstReq dto
    ) {
        return this.service.getHomeMasterFees(dto);
    }

    @ApiOperation(value = "홈마스터 지점장 수수료 생성관리 목록 조회", notes = "조회조건에 따른 홈마스터 지점장 수수료 생성관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/hmsts-brmgr")
    public List<SearchHmstBrmgrRes> getHomeMasterBranchManagerFees(
        @Valid
        SearchHmstReq dto
    ) {
        return this.service.getHomeMasterBranchManagerFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 생성관리 지점장/플래너 목록 조회", notes = "조회조건에 따른 M조직 수수료 생성관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/mngers")
    public List<HashMap<String, Object>> getManagerFees(
        @Valid
        SearchMngerReq dto
    ) {
        return this.service.getManagerFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 생성관리 전체 목록 조회", notes = "조회조건에 따른 M조직 수수료 생성관리 전체 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1Id", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/mngers-total")
    public List<HashMap<String, Object>> getManagerTotalFees(
        @Valid
        SearchMngerReq dto
    ) {
        return this.service.getManagerTotalFees(dto);
    }

    @ApiOperation(value = "M조직 수수료 생성관리 202304 이전 전체 목록 조회", notes = "조회조건에 따른 M조직 수수료 생성관리 전체 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1Id", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/mngers-total-bf")
    public List<HashMap<String, Object>> getManagerTotalBeforeFees(
        @Valid
        SearchMngerReq dto
    ) {
        return this.service.getManagerTotalBeforeFees(dto);
    }

    @ApiOperation(value = "P조직 수수료 생성관리 목록 조회", notes = "조회조건에 따른 P조직 수수료 생성관리 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1Id", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/plars")
    public List<SearchPlarRes> getPlannerFees(
        @Valid
        SearchPlarReq dto
    ) {
        return this.service.getPlannerFees(dto);
    }

    @ApiOperation(value = "P조직 수수료 생성관리 지점장 목록 조회", notes = "조회조건에 따른 P조직 수수료 생성관리 지점장 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1Id", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),
    })

    @GetMapping("/plars-brmgr")
    public List<SearchPlarBrmgrRes> getPlannerBranchManagerFees(
        @Valid
        SearchPlarReq dto
    ) {
        return this.service.getPlannerBranchManagerFees(dto);
    }

    @ApiOperation(value = "P조직 수수료 생성관리 전체 목록 조회", notes = "조회조건에 따른 P조직 수수료 생성관리 전체 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "rsbTpCd", value = "직책유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "feeTcntDvCd", value = "회차", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogLevl1Id", value = "조직레벨1", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl2Id", value = "조직레벨2", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevl3Id", value = "조직레벨3", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "1673419"),

    })

    @GetMapping("/plars-total")
    public List<SearchPlarTotalRes> getPlannerTotalFees(
        @Valid
        SearchPlarReq dto
    ) {
        return this.service.getPlannerTotalFees(dto);
    }

}
