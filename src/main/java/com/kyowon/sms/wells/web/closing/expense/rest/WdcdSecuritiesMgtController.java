package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdSecuritiesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdSecuritiesMgtService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCD] 운영비 등록 관리 - 유가증권 Tab")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/marketable-securities")
public class WdcdSecuritiesMgtController {

    private final WdcdSecuritiesMgtService service;

    @ApiOperation(value = "유가증권 - 정산대상", notes = "조직코드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYM", value = "사용년월", paramType = "query"),
        @ApiImplicitParam(name = "entrpDvCd", value = "사업자 구분코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr4LevlOgId", value = "센터단", paramType = "query"),
    })
    @GetMapping("adjust-object")
    public List<SearchAdjustObjectRes> getAdjustObject(@Valid SearchAdjustObjectReq req) {

        return service.getAdjustObject(req);
    }

    @ApiOperation(value = "유가증권 - 정산대상", notes = "조직코드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYM", value = "사용년월", paramType = "query"),
        @ApiImplicitParam(name = "entrpDvCd", value = "사업자 구분코드", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr4LevlOgId", value = "센터단", paramType = "query"),
    })
    @GetMapping("withholding-tax-adjust")
    public List<SearchWithholdingTaxAdjustRes> getWithholdingTaxAdjust(@Valid SearchWithholdingTaxAdjustReq req) {

        return service.getWithholdingTaxAdjust(req);
    }

    @PutMapping
    public SaveResponse editWithholdingTaxAdjust(@RequestBody List<SaveReq> req) {
        return SaveResponse.builder().processCount(service.editWithholdingTaxAdjust(req)).build();
    }

}
