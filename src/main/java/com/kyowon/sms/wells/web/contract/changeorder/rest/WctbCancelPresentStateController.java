package com.kyowon.sms.wells.web.contract.changeorder.rest;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.service.WctbCancelPresentStateService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] 취소현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = CtContractConst.REST_URL_V1 + "/changeorder")
public class WctbCancelPresentStateController {

    private final WctbCancelPresentStateService service;

    @ApiOperation(value = "렌탈 취소현황 조회", notes = "렌탈 취소배정 관리에서 등록이 된 렌탈 취소건을 조회하는 화면")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/rental-cancels")
    public List<SearchRentalRes> getRentalCancelPresentStates(
            @Valid
            SearchReq dto
    ) {
        return service.getRentalCancelPresentStates(dto);
    }

    @ApiOperation(value = "정기배송 취소현황 조회", notes = "정기배송 취소현황 - 취소현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/regular-shipping-cancels")
    public List<SearchRegularShippingRes> getRegularShippingCancelPresentStates(
            @Valid
            SearchReq dto
    ) {
        return service.getRegularShippingCancelPresentStates(dto);
    }

    @ApiOperation(value = "멤버십 취소현황 조회", notes = "멤버십 취소현황 - 취소현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/membership-cancels")
    public List<SearchMembershipRes> getMembershipCancelPresentStates(
            @Valid
            SearchReq dto
    ) {
        return service.getMembershipCancelPresentStates(dto);
    }
}





