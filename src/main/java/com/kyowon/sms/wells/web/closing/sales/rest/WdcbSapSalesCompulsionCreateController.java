package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.RemoveReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SearchSapMapNmRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbSapSalesCompulsionCreateService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] SAP매출강제생성(W-CL-U-0112M01)")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sap-sales-compulsion")
public class WdcbSapSalesCompulsionCreateController {
    private final WdcbSapSalesCompulsionCreateService service;

    /**
     * 자재명 조회
     * @param zsmtrl
     * @return
     */
    @ApiOperation(value = "자재명 조회", notes = "조회조건에 따른 자재명을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zsmtrl", value = "SAP자재코드", paramType = "query"),
    })
    @GetMapping("/sap-map-nm")
    public SearchSapMapNmRes getSapMapNm(
        @RequestParam
        String zsmtrl
    ) {
        return service.getSapMapNm(zsmtrl);
    }

    /**
     * SAP매출 조회
     * @param baseYm
     * @return
     */
    @ApiOperation(value = "SAP매출", notes = "조회조건에 따른 계약상세번호 별 매출상세정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "매출월", paramType = "query"),
    })
    @GetMapping()
    public List<SearchRes> getSapSales(
        @RequestParam
        String baseYm
    ) {
        return service.getSapSales(baseYm);
    }

    /**
     * SAP매출강제생성 수정(저장)
     */
    @ApiOperation(value = "SAP매출강제생성 수정(저장)", notes = "SAP매출강제생성 정보 변경 후 저장")
    @PostMapping
    public SaveResponse saveSapSalesCompulsionCreates(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveSapSalesCompulsionCreates(dtos)).build();
    }

    /**
     * SAP매출강제생성 삭제
     */
    @ApiOperation(value = "SAP매출강제생성 삭제", notes = "SAP매출강제생성 정보 삭제")
    @DeleteMapping
    public SaveResponse removeSapSalesCompulsionCreates(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeSapSalesCompulsionCreates(dtos)).build();
    }
}
