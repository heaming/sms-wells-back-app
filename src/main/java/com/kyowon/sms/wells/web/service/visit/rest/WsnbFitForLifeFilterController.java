package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchInfoReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchInfoRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchFiltersReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchFiltersRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchHistoriesReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchHistoriesRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SaveFilterReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbFitForLifeFilterService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/customer-center")
@Api(tags = "[WSNB] 차기 방문 스케쥴 및 생활맞춤형 필터 REST API")
@RequiredArgsConstructor
public class WsnbFitForLifeFilterController {
    private final WsnbFitForLifeFilterService service;

    @ApiOperation(value = "차기 방문 스케쥴 및 생활맞춤형 필터 정보 조회", notes = "차기 방문 스케쥴 및 생활맞춤형 필터 정보 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", example = "", dataType = "int"),
    })
    @GetMapping("/next-bs-cust-info")
    public SearchInfoRes getNextBSCustInfo(
        SearchInfoReq dto
    ) {
        return service.getNextBSCustInfo(dto);
    }

    @ApiOperation(value = "차기 방문 스케쥴 및 생활맞춤형 필터 목록 조회", notes = "차기 방문 스케쥴 및 생활맞춤형 필터 목록 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", example = "", dataType = "int"),
        @ApiImplicitParam(name = "vstDuedt", value = "계약일련번호", paramType = "query", example = ""),
    })
    @GetMapping("/next-bs-cust-info/filter-items")
    public List<SearchFiltersRes> getFilterItems(
        SearchFiltersReq dto
    ) {
        return service.getFilterItems(dto);
    }

    @ApiOperation(value = "등록/변경 이력 정보 조회", notes = "등록/변경 이력 정보  조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", example = "@20233136471"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", example = "1", dataType = "int"),
    })
    @GetMapping("/next-bs-cust-info/histories")
    public List<SearchHistoriesRes> getFilterHistories(
        SearchHistoriesReq dto
    ) {
        return service.getFilterHistories(dto);
    }

    @ApiOperation(value = "생활맞춤형 필터 저장", notes = "생활맞춤형 필터를 저장한다.")
    @PostMapping("/next-bs-cust-info/filter")
    public SaveResponse saveFilter(
        @Valid
        @RequestBody
        SaveFilterReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveFilter(dto))
            .build();
    }
}
