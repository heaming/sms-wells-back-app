package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdMarketableSecuritiesMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdMarketableSecuritiesMgtService;
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

@Api(tags = "[WDCD] 운영비 원천세 정산(유가증권)")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/marketable-securities")
public class WdcdMarketableSecuritiesMgtController {

    private final WdcdMarketableSecuritiesMgtService service;

    @ApiOperation(value = "유가증권 제외 - 정산대상", notes = "조직코드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYM", value = "사용년월", paramType = "query"),
    })
    @GetMapping("adjust-object")
    public List<AdjustObjectRes> getAdjustObject(@Valid AdjustObjectReq req) {

        return service.getAdjustObject(req);
    }

    @ApiOperation(value = "유가증권 제외 - 정산대상", notes = "조직코드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYM", value = "사용년월", paramType = "query"),
    })
    @GetMapping("withholding-tax-adjust")
    public List<WithholdingTaxAdjustRes> getWithholdingTaxAdjust(@Valid WithholdingTaxAdjustReq req) {

        return service.getWithholdingTaxAdjust(req);
    }

    @PutMapping
    public SaveResponse editWithholdingTaxAdjust(@Valid @RequestBody List<EditReq> req) {
        return SaveResponse.builder().processCount(service.editWithholdingTaxAdjust(req)).build();
    }

}
