package com.kyowon.sms.wells.web.closing.payment.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchAfterRes;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaMembershipCheckDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaMembershipCheckService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WDCA] 멤버십확정 체크리스트")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = DcClosingConst.COMMON_URL_V1)
public class WdcaMembershipCheckController {
    private final WdcaMembershipCheckService service;

    @ApiOperation(value = "체크리스트(전) 페이징 조회", notes = "조직선택 값으로 체크리스트(전) 목록 조회")

    @GetMapping("/membership-check-before/paging")
    public PagingResult<SearchRes> getBeforePages(
        PageInfo pageInfo
    ) {
        return service.getBeforePages(pageInfo);
    }

    @GetMapping("/membership-check-before/excel-download")
    public List<SearchRes> getBeforeForExcelDownload() {
        return service.getBeforeForExcelDownload();
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "deptGubun", value = "조직선택", paramType = "query")
    })
    @ApiOperation(value = "체크리스트(후) 페이징 조회", notes = "조직선택 값으로 체크리스트(후) 목록 조회")

    @GetMapping("/membership-check-after/paging")
    public PagingResult<SearchAfterRes> getAfterPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getAfterPages(dto, pageInfo);
    }

    @GetMapping("/membership-check-after/excel-download")
    public List<SearchAfterRes> getAfterForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getAfterForExcelDownload(dto);
    }
}
