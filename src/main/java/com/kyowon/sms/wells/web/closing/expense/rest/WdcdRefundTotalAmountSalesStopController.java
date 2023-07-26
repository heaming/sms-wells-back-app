package com.kyowon.sms.wells.web.closing.expense.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdRefundTotalAmountSalesStopDto.FindRes;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdRefundTotalAmountSalesStopService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WDCD] 환불총액/매출중지 내역 조회 I/F")
@RequestMapping(DcClosingConst.INTERFACE_URL_V1 + "/refund-total-amount-sales-stop")
@RequiredArgsConstructor
@Validated
public class WdcdRefundTotalAmountSalesStopController {

    private final WdcdRefundTotalAmountSalesStopService service;

    @ApiOperation(value = "[EAI_WCLI1006] 환불총액/매출중지 내역 조회", notes = "자료종류구분 1. 매출조정")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "BSDT", value = "매출인식일자", paramType = "query"),
        @ApiImplicitParam(name = "GUBUN_CODE", value = "자료종류", paramType = "query"),
    })
    @PostMapping
    public EaiWrapper getSalesControl(@RequestBody
    EaiWrapper<FindReq> reqWrapper) {

        // Response용 EaiWrapper 생성
        EaiWrapper<List<FindRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        List<FindRes> res = service.getSalesControl(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
