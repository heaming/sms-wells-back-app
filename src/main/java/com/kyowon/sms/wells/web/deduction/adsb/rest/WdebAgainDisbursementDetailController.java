package com.kyowon.sms.wells.web.deduction.adsb.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAgainDisbursementDetailDto.SearchAgainDisbursementReq;
import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAgainDisbursementDetailDto.SearchAgainDisbursementRes;
import com.kyowon.sms.wells.web.deduction.adsb.service.WdebAgainDisbursementDetailService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEB] 재지급 팝업")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/adsb/again-disbursements")
public class WdebAgainDisbursementDetailController {
    private final WdebAgainDisbursementDetailService service;

    @ApiOperation(value = "재지급 팝업 조회", notes = "재지급 팝업을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pstnDv", value = "직급 구분", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = false, example = ""),
    })
    @GetMapping("/paging")
    public PagingResult<SearchAgainDisbursementRes> getAgainDisbursementPages(
        @Valid
        SearchAgainDisbursementReq dto,
        PageInfo pageInfo
    ) {
        return service.getAgainDisbursements(dto, pageInfo);
    }

    @ApiOperation(value = "재지급 팝업 엑셀다운로드", notes = "재지급 팝업을 엑셀다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pstnDv", value = "직급 구분", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", paramType = "query", required = false, example = ""),
    })
    @GetMapping("/excel-download")
    public List<SearchAgainDisbursementRes> getAgainDisbursementForExcelDownload(
        SearchAgainDisbursementReq dto
    ) {
        return service.getAgainDisbursementForExcelDownload(dto);

    }
}
