package com.kyowon.sms.wells.web.closing.expense.rest;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchRes;
import com.kyowon.sms.wells.web.closing.expense.service.WdcdCleanersService;
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

@Api(tags = "[WDCD] 청소 용픔비 관리 - 청소원관리")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/expense/cleaners")
public class WdcdCleanersController {

    private final WdcdCleanersService service;

    @ApiOperation(value = "청소 용픔비 관리 - 청소원 관리", notes = "청소원 관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fstRgstDtm", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "clinrNm", value = "청소원", paramType = "query"),
        @ApiImplicitParam(name = "bldNm", value = "빌딩명", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getCleanerPages(@Valid SearchReq req, PageInfo pageInfo) {
        return service.getCleanerPages(req, pageInfo);
    }

    @ApiOperation(value = "청소 용픔비 관리 - 청소원 관리", notes = "청소원 관리 엑셀다운")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fstRgstDtm", value = "신청년월", paramType = "query"),
        @ApiImplicitParam(name = "clinrNm", value = "청소원", paramType = "query"),
        @ApiImplicitParam(name = "bldNm", value = "빌딩명", paramType = "query")
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getCleanersForExcelDownload(@Valid SearchReq req) {
        return service.getCleanersForExcelDownload(req);
    }

    @DeleteMapping
    public int removeCleanersManagement(@RequestBody List<String> clinrRgnos) {
        return service.removeCleanersManagement(clinrRgnos);
    }
}
