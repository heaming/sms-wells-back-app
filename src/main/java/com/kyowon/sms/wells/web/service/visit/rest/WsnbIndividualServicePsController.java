package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualServicePsDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualServicePsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/individual-service-ps")
@Api(tags = "[WSNB] 개인별 서비스 현황(W-SV-U-0072M01)")
@RequiredArgsConstructor
@Validated
public class WsnbIndividualServicePsController {
    private final WsnbIndividualServicePsService service;

    @ApiOperation(value = "개인별 서비스 현황", notes = "개인별 서비스 현황조회 프로세스")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "bcNo", value = "바코드번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "sppIvcNo", value = "송장번호", paramType = "query", required = true),
    })
    @GetMapping
    public SearchRes getIndividualServicePs(@Valid SearchReq dto){
        return service.getIndividualServicePs(dto);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "가구화정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/household")
    public List<SearchHouseholdRes> getIndividualServiceHousehold(@Valid SearchReq dto){
        return service.getIndividualServiceHousehold(dto);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "컨택현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/contract")
    public List<SearchContactRes> getIndividualServiceContact(@Valid SearchReq dto){
        return service.getIndividualServiceContact(dto);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "연계코드 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/farm-code")
    public List<SearchFarmRes> getIndividualServiceFarm(@Valid SearchReq dto){
        return service.getIndividualServiceFarm(dto);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "연체정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/delinquent")
    public List<SearchDelinquentRes> getIndividualDelinquent(@Valid SearchReq dto){
        return service.getIndividualDelinquent(dto);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "처리내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/process-state")
    public PagingResult<SearchStateRes> getIndividualProcessState(@Valid SearchReq dto, @Valid PageInfo pageInfo){
        return service.getIndividualProcessState(dto, pageInfo);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "상담내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
    })
    @GetMapping("/counsel")
    public PagingResult<SearchCounselRes> getIndividualCounsel(@Valid SearchReq dto, @Valid PageInfo pageInfo){
        return service.getIndividualCounsel(dto, pageInfo);
    }

    @ApiOperation(value = "개인별 서비스 현황", notes = "고객특이사항 저장")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "소속코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkPrtnrNo", value = "작성파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstUnuitmCn", value = "고객특이사항", paramType = "query", required = true),
    })
    @PostMapping
    public SaveResponse saveUnusualItem(@Valid @RequestBody SaveReq dto){
        return SaveResponse.builder().processCount(service.saveUnusualItem(dto)).build();
    }
}
