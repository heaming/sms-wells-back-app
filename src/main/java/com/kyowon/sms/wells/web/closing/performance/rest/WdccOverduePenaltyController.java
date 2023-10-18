package com.kyowon.sms.wells.web.closing.performance.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;
import com.kyowon.sms.wells.web.closing.performance.service.WdccOverduePenaltyService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDCC] 매출채권/선수금 현황 - 영업선수금(W-CL-U-0058M01)")
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/overdue-penalty")
@RequiredArgsConstructor
@Validated
public class WdccOverduePenaltyController {

    private final WdccOverduePenaltyService service;

    /**
     * 매출채권/선수금 현황 - SAP상품구분코드 조회
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - SAP상품구분코드 조회", notes = "매출채권/선수금 현황 - 연체가산금")
    @GetMapping("/code")
    public List<FindCodeRes> getCode() {
        return service.getCode();
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트/집계)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트/집계)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/pointAggregates")
    public List<SearchPointAggregateRes> getPointAggregates(
        @Valid
        SearchReq req
    ) {
        return service.getPointAggregates(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트/주문별)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트/주문별)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/pointOrders")
    public List<SearchPointOrderRes> getPointOrders(
        @Valid
        SearchReq req
    ) {
        return service.getPointOrders(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일자별)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일자별)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/anticipationDates")
    public List<SearchAggregateDateRes> getAnticipationDates(
        @Valid
        SearchReq req
    ) {
        return service.getAnticipationDates(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일시불)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일시불)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/anticipationSinglePayments")
    public List<SearchOrderRes> getAnticipationSinglePayments(
        @Valid
        SearchReq req
    ) {
        return service.getAnticipationSinglePayments(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/멤버십)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/멤버십)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/anticipationMemberships")
    public List<SearchOrderRes> getAnticipationMemberships(
        @Valid
        SearchReq req
    ) {
        return service.getAnticipationMemberships(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/정기배송)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/정기배송)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/anticipationRegularShippings")
    public List<SearchOrderRes> getAnticipationRegularShippings(
        @Valid
        SearchReq req
    ) {
        return service.getAnticipationRegularShippings(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/렌탈)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/렌탈)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/anticipationRentals")
    public List<SearchOrderRes> getAnticipationRentals(
        @Valid
        SearchReq req
    ) {
        return service.getAnticipationRentals(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/리스)
     * @param req
     * @return
     */
    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/리스)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "sap상품구분코드명", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query"),
    })
    @GetMapping("/anticipationLeases")
    public List<SearchOrderRes> getAnticipationLeases(
        @Valid
        SearchReq req
    ) {
        return service.getAnticipationLeases(req);
    }
}
