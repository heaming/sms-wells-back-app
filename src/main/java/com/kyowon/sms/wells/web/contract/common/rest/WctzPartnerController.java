package com.kyowon.sms.wells.web.contract.common.rest;

import static com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBaseRes;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchBranchDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchGeneralDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchRegionalDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.service.WctzPartnerService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTZ] 파트너 계약공통")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/partners")
public class WctzPartnerController {

    private final WctzPartnerService service;

    @ApiOperation(value = "wells 사업자 가입제한 대상 관리 - 사업자 기본 정보 (WELLS)", notes = "사업자 기본 정보를 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bzrno", value = "사업자번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchEntrepreneurBaseRes> getEntrepreneurBases(
        @RequestParam
        String bzrno
    ) {
        return service.getEntrepreneurBases(bzrno);
    }

    @ApiOperation(value = "총괄단 조회", notes = "월조직내역(TB_OGBS_MM_OG_IZ)의 1차레벨 조직정보를 조회")
    @GetMapping("/general-divisions")
    public List<SearchGeneralDivisionsRes> getGeneralDivisions() {
        return service.getGeneralDivisions();
    }

    @ApiOperation(value = "지역단 조회", notes = "월조직내역(TB_OGBS_MM_OG_IZ)의 2차레벨 조직정보를 조회")
    @GetMapping("/regional-divisions")
    public List<SearchRegionalDivisionsRes> getRegionalDivisions() {
        return service.getRegionalDivisions();
    }

    @ApiOperation(value = "지점 조회", notes = "월조직내역(TB_OGBS_MM_OG_IZ)의 3차레벨 조직정보를 조회")
    @GetMapping("/branch-divisions")
    public List<SearchBranchDivisionsRes> getBranchDivisions() {
        return service.getBranchDivisions();
    }
}
