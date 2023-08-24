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

    @ApiOperation(value = "매출채권/선수금 현황", notes = "매출채권/선수금 현황 - 매출채권")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준월", paramType = "query"),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널상세코드", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getSalesBondAggregate(
        @Valid
        SearchReq req
    ) {
        return service.getSalesBondAggregate(req);
    }
}
