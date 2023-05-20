package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SaveSlpnoReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchSapPdDvCdRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchSlpnoRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbBusinessAtamAdjustMgtService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 영업선수금 정산 관리")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/business-atam-adjusts")
public class WdcbBusinessAtamAdjustMgtController {

    private final WdcbBusinessAtamAdjustMgtService service;

    @ApiOperation(value = "대표고객코드 조회", notes = "대표고객코드 정보를 조회")
    @GetMapping("/codes")
    public List<SearchSapPdDvCdRes> getSapPdDvCds() {
        return service.getSapPdDvCds();
    }

    @ApiOperation(value = "영업선수금 정산 관리(집계)", notes = "조회조건에 따른 영업선수금 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dpKndCd", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/total")
    public List<SearchTotalRes> getBusinessAtamTotals(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessAtamTotals(dto);
    }

    @ApiOperation(value = "영업선수금 정산 관리(상세)", notes = "조회조건에 따른 영업선수금 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dpKndCd", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/detail")
    public List<SearchDetailRes> getBusinessAtamDetails(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessAtamDetails(dto);
    }

    /* @ApiOperation(value = "반제전표 생성", notes = "반제전표 생성")
    @PostMapping
    public SaveResponse saveSlipCreate(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder().processCount(slipCreateservice.saveSlipCreate(dto)).build();
    }*/

    @ApiOperation(value = "채권반제 조회", notes = "조회조건에 따른 채권반제 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
    })
    @GetMapping("/sapAlrpySlpno")
    public List<SearchSlpnoRes> getSapAlrpySlpnos(
        @RequestParam
        String sapAlrpySlpno
    ) {
        return service.getSapAlrpySlpnos(sapAlrpySlpno);
    }

    @ApiOperation(value = "전표 초기화", notes = "전표 초기화")
    @PostMapping("/sapAlrpySlpno")
    public SaveResponse saveSlpnoInitializes(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveSlpnoReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveSlpnoInitializes(dtos)).build();
    }
}
