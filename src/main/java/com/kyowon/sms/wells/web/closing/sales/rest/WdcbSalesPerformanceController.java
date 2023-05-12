package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchLeaseMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchLeaseRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchMembershipMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRegularMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRegularRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRentalMonthlyRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesPerformanceDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSalesPerformanceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 매출 실적 현황 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-performance")
public class WdcbSalesPerformanceController {
    private final WdcbSalesPerformanceService service;

    @ApiOperation(value = "매출 실적 현황 - 기본정보", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/base-information")
    public SearchRes getBaseInformation(
        @Valid
        SearchReq dto
    ) {
        return service.getBaseInformation(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 기본정보(렌탈)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/rental")
    public SearchRentalRes getRental(
        @Valid
        SearchReq dto
    ) {
        return service.getRental(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 기본정보(리스)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/lease")
    public SearchLeaseRes getLease(
        @Valid
        SearchReq dto
    ) {
        return service.getLease(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 기본정보(멤버십)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/membership")
    public SearchMembershipRes getMembership(
        @Valid
        SearchReq dto
    ) {
        return service.getMembership(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 기본정보(정기배송)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/regular")
    public SearchRegularRes getRegular(
        @Valid
        SearchReq dto
    ) {
        return service.getRegular(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/rental/paging")
    public PagingResult<SearchRentalMonthlyRes> getRentalPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getRentalPages(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/rental/excel-download")
    public List<SearchRentalMonthlyRes> getRentalExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getRentalExcelDownload(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/lease/paging")
    public PagingResult<SearchLeaseMonthlyRes> getLeasePages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getLeasePages(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/lease/excel-download")
    public List<SearchLeaseMonthlyRes> getLeaseExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getLeaseExcelDownload(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/membership/paging")
    public PagingResult<SearchMembershipMonthlyRes> getMembershipPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getMembershipPages(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/membership/excel-download")
    public List<SearchMembershipMonthlyRes> getMembershipExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getMembershipExcelDownload(dto);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별)", notes = "조회조건에 따른 매출 실적 현황을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/regular/paging")
    public PagingResult<SearchRegularMonthlyRes> getRegularPages(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getRegularPages(dto, pageInfo);
    }

    @ApiOperation(value = "매출 실적 현황 - 매출실적(월별) 엑셀 다운로드", notes = "조회조건에 따른 매출 실적을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "baseYearFrom", value = "매출시작년도", paramType = "query"),
        @ApiImplicitParam(name = "baseYearTo", value = "매출종료년도", paramType = "query"),
    })
    @GetMapping("/regular/excel-download")
    public List<SearchRegularMonthlyRes> getRegularExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getRegularExcelDownload(dto);
    }
}
