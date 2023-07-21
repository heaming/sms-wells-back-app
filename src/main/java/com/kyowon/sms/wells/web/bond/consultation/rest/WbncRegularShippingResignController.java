package com.kyowon.sms.wells.web.bond.consultation.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SaveConfirmReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.service.WbncRegularShippingResignService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 직권해지관리 - 정기배송 해지")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/regular-shipping-resigns")
public class WbncRegularShippingResignController {
    private final WbncRegularShippingResignService service;

    @ApiOperation(value = "직권해지관리 - 정기배송 해지 조회", notes = "직권해지관리 - 정기배송 해지을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "authRsgDt", value = "직권해지일", paramType = "query", required = true),
        @ApiImplicitParam(name = "clctamDvCd", value = "집금구분코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집금담당자", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getRegularShippingResigns(
        @Valid
        SearchReq dto
    ) {
        return service.getRegularShippingResigns(dto);
    }

    @ApiOperation(value = "직권해지관리 - 정기배송 해지 최종확정", notes = "직권해지관리 - 정기배송 해지을 최종확정한다.")
    @PutMapping
    public SaveResponse editRegularShippingResignFinalConfirms(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveConfirmReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.editRegularShippingResignFinalConfirms(dtos)).build();
    }
}
