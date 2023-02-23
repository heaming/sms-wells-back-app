package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.service.WwdcdOperatingCostMgtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "운영비 등록 관리")
@RequiredArgsConstructor
@RestController
@Slf4j
//@RequestMapping(CtContractConst.REST_URL_V1 + "/operationgCost")
@RequestMapping("/api/v1/sms/wells/expense/operating-cost")
public class WwdcdOperatingCostMgtController {

    private final WwdcdOperatingCostMgtService service;

    @ApiOperation(value = "운영비 등록 관리 - 운영비 금액 현황", notes = "운영비 등록 관리 - 운영비 금액 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "befLimitAmt", value = "이월잔액", paramType = "query"),
        @ApiImplicitParam(name = "befUseAmt", value = "당월지급", paramType = "query"),
        @ApiImplicitParam(name = "befJanAmt", value = "당월 이용가능 금액", paramType = "query"),
        @ApiImplicitParam(name = "addAmt", value = "당월 사용금액", paramType = "query"),
        @ApiImplicitParam(name = "cardLimAmt", value = "당월 취소금액", paramType = "query"),
        @ApiImplicitParam(name = "cardUseAmt", value = "진행상태", paramType = "query"),
        @ApiImplicitParam(name = "cmscrUseAmt", value = "잔액", paramType = "query"),
        @ApiImplicitParam(name = "cardResAmt", value = "진행상태", paramType = "query"),
    })
    @GetMapping()
    public SearchRes getWellsOrtCsRgstMngts(@Valid
                                            SearchReq req) {
        //운영비 금액현황
        //운영비 적요 현황
        return service.getWellsOrtCsRgstMngts(req);
    }

    @PostMapping()
    public void saveWithholdingTaxCfdcAdd(@Valid
                                          FileReq req) {
        service.saveWithholdingTaxCfdcAdd(req);
    }

    @GetMapping("withholdingTaxCfdcDld")
    public SaveRes getWithholdingTaxCfdcDld(@Valid SaveReq req) {

        return service.getWithholdingTaxCfdcDld(req);
    }

}
