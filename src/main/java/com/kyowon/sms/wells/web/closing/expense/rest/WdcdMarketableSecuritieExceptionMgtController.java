package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritieExceptionMgttDto.*;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdMarketableSecuritieExceptionMgtService;
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

@Api(tags = "[WDCD]원천세정산 - 유가증권 제외")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/operating-cost/marketable-securities-excd")
public class WdcdMarketableSecuritieExceptionMgtController {

    private final WdcdMarketableSecuritieExceptionMgtService service;

    @GetMapping("/code")
    public List<FindCodeRes> getBuilDingCd() {
        return service.getBuilDingCd();
    }

    @ApiOperation(value = "원천세정산 - 유가증권 제외", notes = "유가증권 제외 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pstnDvCd", value = "직책", paramType = "query"),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dstOjpsNm", value = "대상저", paramType = "query"),
        @ApiImplicitParam(name = "bldCd", value = "빌딩명", paramType = "query")
    })
    @GetMapping("/subject")
    public List<SearchSubjectRes> getSubject(@Valid SearchSubjectReq req) {
        return service.getSubject(req);
    }

    @ApiOperation(value = "원천세정산 - 유가증권 제외", notes = "유가증권 제외 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pstnDvCd", value = "직책", paramType = "query"),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dstOjpsNm", value = "대상저", paramType = "query"),
        @ApiImplicitParam(name = "bldCd", value = "빌딩명", paramType = "query")
    })
    @GetMapping("/final-withholding-tax-settlement")
    public List<SearchFinalSettlementRes> getFinalWithholdingTaxSettlement(@Valid SearchFinalSettlementReq req) {
        return service.getFinalWithholdingTaxSettlement(req);
    }
}
