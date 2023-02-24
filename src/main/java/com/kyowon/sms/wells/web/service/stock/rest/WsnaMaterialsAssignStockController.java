package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMaterialsAssignStockService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/materials-assign-stocks")
@Api(tags = "[WSNA] 물량배정 입고창고관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnaMaterialsAssignStockController {
    private final WsnaMaterialsAssignStockService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202212", required = true),
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query", example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "hmnrscEmpno", value = "인사사원번호", paramType = "query", example = ""),
    })
    @GetMapping
    public PagingResult<SearchRes> getMaterialsAssignStocks(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getMaterialsAssignStocks(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202302", required = true),
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query", example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "wareNo", value = "창고번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "hmnrscEmpno", value = "인사사원번호", paramType = "query", example = ""),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(
        SearchReq dto
    ) {
        return service.getMaterialsAssignStocks(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202302", required = true),
        @ApiImplicitParam(name = "ogId", value = "조직ID", paramType = "query", example = "1417265"),
    })
    @PostMapping
    public SaveResponse createMaterialsAssignStocks(
        @RequestBody
        @Valid
        List<CreateReq> list
    ) {
        return SaveResponse.builder()
            .processCount(service.createMaterialsAssignStocks(list))
            .build();
    }

    /* TODO: 조직/파트너 공통 API가 없어서 임시 사용. 공통API 만들어지먼 삭제.*/
    @GetMapping("/partners")
    public List<PrtnrRes> selectPartners(
        PrtnrReq dto
    ) {
        return service.selectPartners(dto);
    }

    /* TODO: 조직/파트너 공통 API가 없어서 임시 사용. 공통API 만들어지먼 삭제.*/
    @GetMapping("/organizations")
    public List<OgRes> selectOrganizations(OgReq dto) {
        return service.selectOrganizations(dto);
    }
}
