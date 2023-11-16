package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTotAsRtSdingPsDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbTotAsRtSdingPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/tot-as-rt-sding-ps")
@Api(tags = "[WSNB] 총A/S율_모종 현황 조회 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbTotAsRtSdingPsController {
    private final WsnbTotAsRtSdingPsService service;

    @ApiOperation(value = "설치1년 누적A/S율 조회(품질)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseY", value = "기준년도", paramType = "query", example = "2023"),
        @ApiImplicitParam(name = "svType", value = "서비스유형", paramType = "query"),
        @ApiImplicitParam(name = "badDivide", value = "불량구분", paramType = "query"),
        @ApiImplicitParam(name = "pdGrp", value = "상품그룹", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품명", paramType = "query")
    })
    @GetMapping("/search")
    public List<SearchRes> getTotAsRtSdingPss(SearchReq dto){
        return service.getTotAsRtSdingPss(dto);
    }

    @ApiOperation(value = "모종 패키지 조회")
    @GetMapping("/sdingPackage")
    public List<SdingPackageRes> getSdingPackage(){
        return service.getSdingPackage();
    }

    @ApiOperation(value = "모종 상세 조회")
    @GetMapping("/sdingDtlInfo")
    public List<SdingDtlRes> getSdingDtlInfo(SdingDtlReq dtlDto){
        return service.getSdingDtlInfo(dtlDto);
    }
}
