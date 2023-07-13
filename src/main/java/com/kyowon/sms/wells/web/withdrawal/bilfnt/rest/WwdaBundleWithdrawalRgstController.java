package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaBundleWithdrawalRgstDto.SearchUnrgPsRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaBundleWithdrawalRgstService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_V1 + "/bilfnt")
@Api(tags = "[WWDA] 자동이체 묶음 출금 등록 관리")
public class WwdaBundleWithdrawalRgstController {

    private final WwdaBundleWithdrawalRgstService service;

    @ApiOperation(value = "묶음출금 미등록 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "unrgRs", value = "대상구분, 처리결과", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "fullCntr", value = "계약상세번호", paramType = "query", required = false, example = "W202200860151"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세", paramType = "query", required = false, example = "W20220086015"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "접수시작일", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "접수종료일", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/bundle-withdrawal-unrgs")
    public PagingResult<SearchUnrgPsRes> getUnregistrationPsInqrPages(
        @Valid
        SearchReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getUnregistrationPsInqrPages(req, pageInfo);
    }

    @ApiOperation(value = "묶음 등록")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false, example = "W20220086015"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "접수시작일", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "접수종료일", paramType = "query", required = false, example = "20230215"),
    })
    @PostMapping("/bundle-registration")
    public SaveResponse saveBundleRegistration(
        @RequestBody
        List<WwdaBundleWithdrawalRgstDto.SaveReq> req
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBundleRegistration(req))
            .build();
    }

    @ApiOperation(value = "묶음출금 미등록 현황 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "unrgRs", value = "대상구분, 처리결과", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "fullCntr", value = "계약상세번호", paramType = "query", required = false, example = "W202200860151"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세", paramType = "query", required = false, example = "W20220086015"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "접수시작일", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "접수종료일", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/bundle-withdrawal-unrgs/excel-download")
    public List<SearchUnrgPsRes> getUnregistrationPsInqrExcels(
        @Valid
        SearchReq req
    ) {
        return service.getUnregistrationPsInqrExcels(req);
    }

    @ApiOperation(value = "묶음 출금 등록 이력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "unrgRs", value = "대상구분, 처리결과", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "fullCntr", value = "계약상세번호", paramType = "query", required = false, example = "W202200860151"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세", paramType = "query", required = false, example = "W20220086015"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "접수시작일", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "접수종료일", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/bundle-withdrawal-hist")
    public PagingResult<SearchRgstHistRes> getgetBundleRgstRsInqrPages(
        @Valid
        SearchReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBundleRgstRsInqrPages(req, pageInfo);
    }

    @ApiOperation(value = "묶음 출금 등록 이력 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "unrgRs", value = "대상구분, 처리결과", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "fullCntr", value = "계약상세번호", paramType = "query", required = false, example = "W202200860151"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세", paramType = "query", required = false, example = "W20220086015"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "접수시작일", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "접수종료일", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/bundle-withdrawal-hist/excel-download")
    public List<SearchRgstHistRes> getgetBundleRgstRsInqrPages(
        @Valid
        SearchReq req
    ) {
        return service.getBundleRgstRsInqrPages(req);
    }
}
