package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverdueQenaltyDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverdueQenaltyDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccOverdueQenaltyService;
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
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/overdue-qenalty")
@RequiredArgsConstructor
@Validated
public class WdccOverdueQenaltyController {

    private final WdccOverdueQenaltyService service;

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(집계)", notes = "매출채권/선수금 현황 - 연체가산금")
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
    public List<SearchRes> getSalesBondAdditionalChargesAggregate(
        @Valid SearchReq req
    ) {
        return service.getSalesBondAdditionalChargesAggregate(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(일자별)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/dates")
    public List<SearchRes> getSalesBondAdditionalChargesDates(
        @Valid SearchReq req
    ) {
        return service.getSalesBondAdditionalChargesDates(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(주문별)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/orders")
    public List<SearchRes> getSalesBondAdditionalChargesOrders(
        @Valid SearchReq req
    ) {
        return service.getSalesBondAdditionalChargesOrders(req);
    }

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금(가로계산식 틀린회원)", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "업무구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntr", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "mlgBtdPrpdAmt", value = "포인트 조회", paramType = "query")
    })
    @GetMapping("/members")
    public List<SearchRes> getSalesBondAdditionalChargesMembers(
        @Valid SearchReq req
    ) {
        return service.getSalesBondAdditionalChargesMembers(req);
    }

}


