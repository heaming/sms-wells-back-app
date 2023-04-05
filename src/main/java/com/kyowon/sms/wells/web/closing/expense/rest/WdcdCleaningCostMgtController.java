package com.kyowon.sms.wells.web.closing.expense.rest;

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
    private final WdcdCleaningCostMgtSearvice searvice;

    @ApiOperation(value = "운영비 등록 관리 - 운영비 금액 현황", notes = "운영비 등록 관리 - 운영비 금액 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fstRgstDtm", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "clinrNm", value = "청소원", paramType = "query"),
        @ApiImplicitParam(name = "bldNm", value = "빌딩명", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getCleaningCost(@Valid SearchReq req, PageInfo pageInfo) {
        return searvice.getCleaningCost(req, pageInfo);
    }

    @ApiOperation(value = "운영비 등록 관리 - 운영비 금액 현황", notes = "운영비 등록 관리 - 운영비 금액 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fstRgstDtm", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "clinrNm", value = "청소원", paramType = "query"),
        @ApiImplicitParam(name = "bldNm", value = "빌딩명", paramType = "query")
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getCleaningCostExcelDownload(@Valid SearchReq req) {
        return searvice.getCleaningCostExcelDownload(req);
    }

    @DeleteMapping
    public int removeCleaningCost(@RequestBody List<String> clinrRgnos) {
        return searvice.removeCleaningCost(clinrRgnos);
    }
}
