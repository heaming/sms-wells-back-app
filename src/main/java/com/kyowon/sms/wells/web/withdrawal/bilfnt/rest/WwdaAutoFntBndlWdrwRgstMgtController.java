package com.kyowon.sms.wells.web.withdrawal.bilfnt.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntBndlWdrwRgstMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntBndlWdrwRgstMgtDto.SearchRgstHistRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaAutoFntBndlWdrwRgstMgtDto.SearchUnrgPsRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.service.WwdaAutoFntBndlWdrwRgstMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WithdrawalConst;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(WithdrawalConst.REST_URL_V1)
@Api(tags = "[WWDA] 자동이체 묶음 출금 등록 관리")
public class WwdaAutoFntBndlWdrwRgstMgtController {

    private final WwdaAutoFntBndlWdrwRgstMgtService service;

    @ApiOperation(value = "묶음출금 미등록 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "unrgRs", value = "대상구분, 처리결과", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "fullCntr", value = "계약상세번호", paramType = "query", required = false, example = "W202200860151"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세", paramType = "query", required = false, example = "W20220086015"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세일련번호", paramType = "query", required = false, example = "1"),
        @ApiImplicitParam(name = "cntrPdStrtdt", value = "접수시작일", paramType = "query", required = false, example = "20230208"),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "접수종료일", paramType = "query", required = false, example = "20230215"),
    })
    @GetMapping("/w-bundle-wdrw-unrg-ps-inqr") // url은 추후에 수정
    public PagingResult<SearchUnrgPsRes> getUnregistrationPsInqrPages(
        @ApiParam
        @Valid
        SearchReq req
    ) {
        return service.getUnregistrationPsInqrPages(req);
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
    @GetMapping("/wwda-bndl-wdrw-rgst-hist-inqr") // url은 추후에 수정
    public PagingResult<SearchRgstHistRes> getgetBundleRgstRsInqrPages(
        @ApiParam
        @Valid
        SearchReq req
    ) {
        return service.getBundleRgstRsInqrPages(req);
    }
}
