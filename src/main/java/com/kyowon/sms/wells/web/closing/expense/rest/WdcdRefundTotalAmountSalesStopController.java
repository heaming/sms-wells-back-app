package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindRefundRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindSalesControlRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindSalesStopRes;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdRefundTotalAmountSalesStopService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCD] 환불총액/매출중지 내역 조회 I/F")
@Validated
@RequiredArgsConstructor
@InterfaceController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/refund-total-amount-sales-stop")
public class WdcdRefundTotalAmountSalesStopController {

    private final WdcdRefundTotalAmountSalesStopService service;

    @ApiOperation(value = "환불총액/매출중지 내역 조회", notes = "자료종류구분 1. 매출조정")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bsdt", value = "매출인식일자", paramType = "query"),
    })
    @GetMapping("/sales-control")
    public List<FindSalesControlRes> getSalesControl(@Valid FindReq req) {
        return service.getSalesControl(req);
    }

    @ApiOperation(value = "환불총액/매출중지 내역 조회", notes = "자료종류구분 1. 매출조정")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bsdt", value = "매출인식일자", paramType = "query"),
    })
    @GetMapping("/refund")
    public List<FindRefundRes> getRefund(@Valid FindReq req) {
        return service.getRefund(req);
    }

    @ApiOperation(value = "환불총액/매출중지 내역 조회", notes = "자료종류구분 1. 매출조정")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bsdt", value = "매출인식일자", paramType = "query"),
    })
    @GetMapping("/sales-stop")
    public List<FindSalesStopRes> getSalesStop(@Valid FindReq req) {
        return service.getSalesStop(req);
    }
}
