package com.kyowon.sms.wells.web.contract.common.rest;

import static com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBasesRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        @ApiImplicitParam(name = "dlpnrCd", value = "사업자번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchEntrepreneurBasesRes> getEntrepreneurBases(
        @Valid
        String dlpnrCd
    ) {
        return service.getEntrepreneurBases(dlpnrCd);
    }
}
