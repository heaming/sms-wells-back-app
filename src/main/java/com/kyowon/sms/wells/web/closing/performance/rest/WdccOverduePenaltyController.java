package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;
import com.kyowon.sms.wells.web.closing.performance.service.WdccOverduePenaltyService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCC] 매출채권/선수금 현황 - 영업선수금")
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/overdue-penalty")
@RequiredArgsConstructor
@Validated
public class WdccOverduePenaltyController {

    private final WdccOverduePenaltyService service;

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(집계)", notes = "매출채권/선수금 현황 - 연체가산금")
    @GetMapping("/code")
    public List<FindCodeRes> getCode() {
        return service.getCode();
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(mainGrid 집계)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/mainAggregate")
    public List<SearchMainGridRes> getMainGridAggregate(
        @Valid SearchReq req
    ) {
        return service.getMainGridAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(mainGrid 일자별)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/mainDates")
    public List<SearchMainGridRes> getMainGridDates(
        @Valid SearchReq req
    ) {
        return service.getMainGridDates(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(subGrid주문별)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/subOrder")
    public List<SearchSubGridRes> getSubGridOrder(
        @Valid SearchReq req
    ) {
        return service.getSubGridOrder(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(thirdGrid집계)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/thirdAggregate")
    public List<SearchThirdGridRes> getThirdGridAggregate(
        @Valid SearchReq req
    ) {
        return service.getThirdGridAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(fourthGrid집계)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/fourthOrder")
    public List<SearchFourthGridRes> getFourthGridOrder(
        @Valid SearchReq req
    ) {
        return service.getFourthGridOrder(req);
    }
}


