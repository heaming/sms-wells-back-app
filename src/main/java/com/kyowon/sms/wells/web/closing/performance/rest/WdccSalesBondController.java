package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.*;
import com.kyowon.sms.wells.web.closing.performance.service.WdccSalesBondService;
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

@Api(tags = "[WDCC] 매출채권/선수금 현황 - 매출채권")
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/sales-bond")
@RequiredArgsConstructor
@Validated
public class WdccSalesBondController {
    private final WdccSalesBondService service;

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(일시블 집계)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query"),
    })
    @GetMapping("/aggregate")
    public List<SearchlumpSumAggregationRes> getSalesBondAggregate(
        @Valid SearchReq req
    ) {
        return service.getSalesBondAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(일시블 (일자별, 주문별, 가로세로 주문))", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/order-date")
    public List<SearchLumpSumPaymentByDateRes> getAggregateOrderDate(
        @Valid SearchReq req
    ) {
        return service.getAggregateOrderDate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(렌탈, 리스, 집계)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/rental-aggregate")
    public List<SearchRentalAggregateRes> getRentalAggregate(
        @Valid SearchReq req
    ) {
        return service.getRentalAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(렌탈, 리스, 집계)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/lease-aggregate")
    public List<SearchLeaseAggregateRes> getLeaseAggregate(
        @Valid SearchReq req
    ) {
        return service.getLeaseAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(렌탈,일자별, 주문별, 가로계산식 틀린 회원)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/rental-day-perOrder")
    public List<SearchRentalDayPerOrderRes> getRentalDayPerOrder(
        @Valid SearchReq req
    ) {
        return service.getRentalDayPerOrder(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(리스, 일자별, 주문별, 가로계산식 틀린 회원)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/lease-day-perOrder")
    public List<SearchLeaseDayPerOrderRes> getLeaseDayPerOrder(
        @Valid SearchReq req
    ) {
        return service.getLeaseDayPerOrder(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(맴버쉽, 집계)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/member-aggregate")
    public List<SearchMemberAggregateRes> getMemberAggregate(@Valid SearchReq req) {
        return service.getMemberAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(맴버쉽, 일자별, 주문별, 가로계산식 틀린 회원)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/member-day-perOrder")
    public List<SearchMemberDayPerOrderRes> getMemberDayPerOrder(@Valid SearchReq req) {
        return service.getMemberDayPerOrder(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(정기배송, 집계)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/regular-delivery-aggregate")
    public List<SearchRegularDeliveryAggregateRes> getRegularDeliveryAggregate(@Valid SearchReq req) {
        return service.getRegularDeliveryAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(정기배송, 일자별, 주문별, 가로계산식 틀린 회원)", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/regular-delivery-day-perOrder")
    public List<SearchRegularDeliveryDayPerOrdereRes> getRegularDeliveryDayPerOrder(@Valid SearchReq req) {
        return service.getRegularDeliveryDayPerOrder(req);
    }
}
