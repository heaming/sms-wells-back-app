package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdCleaningCostMgtSearvice;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WDCD] 청소 용품비 관리")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/cleaning-cost")
public class WdcdCleaningCostMgtController {
    private final WdcdCleaningCostMgtSearvice service;

    @GetMapping("/code")
    public List<CodeRes> getBuilDingCd() {
        return service.getBuilDingCd();
    }

    @ApiOperation(value = "청소 용품비 관리", notes = "청소 용품비 관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "aplcStartDt", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "aplcEndtDt", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "bldCd", value = "빌딩코드", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getCleaningCost(@Valid SearchReq req, PageInfo pageInfo) {
        return service.getCleaningCost(req, pageInfo);
    }

    @ApiOperation(value = "청소 용품비 관리", notes = "청소 용품비 관리 엑셀다운")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "aplcStartDt", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "aplcEndtDt", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "bldCd", value = "빌딩코드", paramType = "query")
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getCleaningCostExcelDownload(@Valid SearchReq req) {
        return service.getCleaningCostExcelDownload(req);
    }

    @DeleteMapping
    public int removeCleanerCost(@RequestBody List<String> clingCostAdjRcpNos) {
        return service.removeCleaningCost(clingCostAdjRcpNos);
    }
}
