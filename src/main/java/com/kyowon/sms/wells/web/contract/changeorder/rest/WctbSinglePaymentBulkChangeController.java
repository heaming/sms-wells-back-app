package com.kyowon.sms.wells.web.contract.changeorder.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbSinglePaymentBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbSinglePaymentBulkChangeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "일시불 일괄변경 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder/SinglePayment-bulk-changes")
public class WctbSinglePaymentBulkChangeController {
    private final WctbSinglePaymentBulkChangeService service;

    @ApiOperation(value = "일시불 일괄변경 조회", notes = "일시불 일괄변경 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "rfDt", value = "반영일자", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getSinglePaymentBulkChangs(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn,
        @RequestParam
        String rfDt
    ) {
        return service.getSinglePaymentBulkChangs(cntrNo, cntrSn, rfDt);
    }
}
