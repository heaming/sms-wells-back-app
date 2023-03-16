package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchRes;
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

@Api(tags = "[DCC] 매출채권/선수금 현황 - 매출채권")
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/sales-bond")
@RequiredArgsConstructor
@Validated
public class WdccSalesBondController {
    private final WdccSalesBondService service;

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
    public List<SearchRes> getSalesBondAggregateList(
        @Valid SearchReq req
    ) {
        return service.getSalesBondAggregateList(req);
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
    @GetMapping("/date")
    public List<SearchRes> getSalesBondDateList(
        @Valid SearchReq req
    ) {
        return service.getSalesBondDateList(req);
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
    @GetMapping("/order")
    public List<SearchRes> getSalesBondOrderList(
        @Valid SearchReq req
    ) {
        return service.getSalesBondOrderList(req);
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
    @GetMapping("/member")
    public List<SearchRes> getSalesBondMemberList(
        @Valid SearchReq req
    ) {
        return service.getSalesBondMemberList(req);
    }
}
