package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnBilNrcvListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnBilNrcvListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnDpNcrtCheckListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnDpNcrtCheckListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnSlPerfCheckInqrRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchResultBundleErrorRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaNotReceivedCheckListService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_V1 + "/bilfnt")
@Api(tags = "[WWDA] 자동이체 미수신 체크 목록 관리")
public class WwdaNotReceivedCheckListController {

    private final WwdaNotReceivedCheckListService service;

    @ApiOperation(value = "자동이체 미수신 체크 목록")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bilDt", value = "기준일자", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "fntDvCd", value = "이체구분", paramType = "query", required = false, example = "01"),
    })
    @GetMapping("/not-received-checks")
    public PagingResult<SearchAftnBilNrcvListRes> getAftnBilNrcvListPages(
        @Valid
        SearchAftnBilNrcvListReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAftnBilNrcvListPages(req, pageInfo);
    }

    @ApiOperation(value = "자동이체 미수신 체크 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bilDt", value = "기준일자", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "fntDvCd", value = "이체구분", paramType = "query", required = false, example = "01"),
    })
    @GetMapping("/not-received-checks/excel-download")
    public List<SearchAftnBilNrcvListRes> getAftnBilNrcvListExcels(
        @Valid
        SearchAftnBilNrcvListReq req
    ) {
        return service.getAftnBilNrcvListExcels(req);
    }

    @ApiOperation(value = "자동이체 입금 미생성 체크 목록")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bilDt", value = "기준일자", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "fntDvCd", value = "이체구분", paramType = "query", required = false, example = "01"),
    })
    @GetMapping("/deposit-ncrt-check")
    public PagingResult<SearchAftnDpNcrtCheckListRes> getAftnDpNcrtCheckListPages(
        SearchAftnDpNcrtCheckListReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAftnDpNcrtCheckListPages(req, pageInfo);
    }

    @ApiOperation(value = "자동이체 입금 미생성 체크 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bilDt", value = "기준일자", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "fntDvCd", value = "이체구분", paramType = "query", required = false, example = "01"),
    })
    @GetMapping("/deposit-ncrt-check/excel-download")
    public List<SearchAftnDpNcrtCheckListRes> getAftnDpNcrtCheckListExcels(
        @Valid
        SearchAftnDpNcrtCheckListReq req
    ) {
        return service.getAftnDpNcrtCheckListExcels(req);
    }

    @ApiOperation(value = "자동이체 매출실적 체크 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bilYm", value = "기준년월", paramType = "query", required = false, example = "202302"),
    })
    @GetMapping("/sales-perf-checks")
    public PagingResult<SearchAftnSlPerfCheckInqrRes> getAftnSlPerfCheckInqrPages(
        @RequestParam
        String bilYm,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAftnSlPerfCheckInqrPages(bilYm, pageInfo);
    }

    @ApiOperation(value = "자동이체 매출실적 체크 목록 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bilYm", value = "기준년월", paramType = "query", required = false, example = "202302"),
    })
    @GetMapping("/sales-perf-checks/excel-download")
    public List<SearchAftnSlPerfCheckInqrRes> getAftnSlPerfCheckInqrExcels(
        @RequestParam
        String bilYm
    ) {
        return service.getAftnSlPerfCheckInqrExcels(bilYm);
    }

    @ApiOperation(value = "자동이체 결과 묶음 오류 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dpTpCd", value = "자동이체 체크", paramType = "query", required = false, example = "01"),
    })
    @GetMapping("/result-bundle-error")
    public PagingResult<SearchResultBundleErrorRes> getAftnRsBndlErrInqrPages(
        @RequestParam
        String dpTpCd,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAftnRsBndlErrInqrPages(dpTpCd, pageInfo);
    }

    @ApiOperation(value = "자동이체 결과 묶음 오류 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dpTpCd", value = "자동이체 체크", paramType = "query", required = false, example = "01"),
    })
    @GetMapping("/result-bundle-error/excel-download")
    public List<SearchResultBundleErrorRes> getAftnRsBndlErrInqrExcels(
        @RequestParam
        String dpTpCd
    ) {
        return service.getAftnRsBndlErrInqrExcels(dpTpCd);
    }
}
