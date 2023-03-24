package com.kyowon.sms.wells.web.closing.sales.rest;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbMonthlySalesVolumeTableDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbMonthlySalesVolumeTableService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCB] 월 매출수량 집계표")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales")
public class WdcbMonthlySalesVolumeTableController {
    private final WdcbMonthlySalesVolumeTableService service;

    @ApiOperation(value = "월매출량수량 집계표", notes = "월 매출수량 집계내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "slStartDt", value = "매출일자From", paramType = "query"),
        @ApiImplicitParam(name = "slEmdDt", value = "매출일자To", paramType = "query"),
        @ApiImplicitParam(name = "sppMthdTpCd", value = "배달구분", paramType = "query"),
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "판매유형", paramType = "query"),
    })
    @GetMapping("/rental")
    public List<SearchRentalRes> getRentalMonthSalesQuantity(@Valid SearchReq req) {
        return service.getRentalMonthSalesQuantity(req);
    }

    @ApiOperation(value = "월매출량수량 집계표", notes = "월 매출수량 집계내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "slStartDt", value = "매출일자From", paramType = "query"),
        @ApiImplicitParam(name = "slEmdDt", value = "매출일자To", paramType = "query"),
        @ApiImplicitParam(name = "sppMthdTpCd", value = "배달구분", paramType = "query"),
        @ApiImplicitParam(name = "sellInflwChnlDtlCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "판매유형", paramType = "query"),
    })
    @GetMapping("/payment")
    public List<SearchPaymentRes> getPaymentMonthSalesQuantity(@Valid SearchReq req) {
        return service.getPaymentMonthSalesQuantity(req);
    }
}
