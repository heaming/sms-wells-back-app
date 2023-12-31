package com.kyowon.sms.wells.web.closing.performance.rest;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccDelinquentAdditionalChargesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.service.WdccDelinquentAdditionalChargesService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCC] 매출채권/선수금 현황 - 연체가산금")
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/performance/delinquent-additional-charges")
@RequiredArgsConstructor
@RestController
@Slf4j
public class WdccDelinquentAdditionalChargesController {

    private final WdccDelinquentAdditionalChargesService service;

    @ApiOperation(value = "매출채권/선수금 현황 - 연체가산금", notes = "매출채권/선수금 현황 - 연체가산금")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "slClYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "agrgDv", value = "집계구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtlCd", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getDelinquentAdditionalCharges(
        @Valid
        SearchReq req
    ) {
        return service.getDelinquentAdditionalCharges(req);
    }
}
