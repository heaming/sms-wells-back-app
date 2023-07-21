package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncRentalResignConfirmService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 직권해지관리 - 렌탈 해지확정")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/rental-rsg-cnfms")
public class WbncRentalResignConfirmController {
    private final WbncRentalResignConfirmService service;

    @ApiOperation(value = "직권해지관리 - 렌탈 해지확정 조회", notes = "직권해지관리 - 렌탈 해지확정을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fromAuthRsgCnfmdt", value = "확정년월시작", paramType = "query", required = true),
        @ApiImplicitParam(name = "toAuthRsgCnfmdt", value = "확정년월종료", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getRentalResignConfirms(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalResignConfirms(dto);
    }
}
