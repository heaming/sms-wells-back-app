package com.kyowon.sms.wells.web.service.orgcode.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.*;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndBusinessVehiclesMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSND] 업무차량 지급관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/business-vehicles")
public class WsndBusinessVehiclesMgtController {
    private final WsndBusinessVehiclesMgtService service;

    @ApiOperation(value = "엔지니어 지급차량 조회", notes = "조회조건에 일치하는 지급차량 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "hgrOgId", value = "상위조직ID(광역센터)", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCd", value = "조직코드(지점)", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getBusinessVehicles(SearchReq dto) {
        return service.getBusinessVehicles(dto);
    }

    @ApiOperation(value = "엔지니어 지급차량 조회(paging)", notes = "조회조건에 일치하는 지급차량 정보를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "hgrOgId", value = "상위조직ID(광역센터)", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogCd", value = "조직코드(지점)", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBusinessVehiclesPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBusinessVehiclesPages(dto, pageInfo);
    }

    @ApiOperation(value = "차량지급 상세 조회", notes = "엔지니어 차량지급 상세조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "vhcMngtNo", value = "차량관리번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "vhcMngtSn", value = "차량관리일련번호", paramType = "query", required = true)
    })
    @GetMapping("/{vhcMngtNo}-{vhcMngtSn}")
    public FindRes getBusinessVehicle(
        @PathVariable
        String vhcMngtNo,
        @PathVariable
        String vhcMngtSn
    ) {
        return service.getBusinessVehicle(vhcMngtNo, vhcMngtSn);
    }

    @ApiOperation(value = "차량지급 등록", notes = "입력된 데이터에 따른 차량지급 등록")
    @PostMapping
    public SaveResponse createBusinessVehicle(
        @RequestBody
        @Valid
        CreateReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.createBusinessVehicle(dto))
            .build();
    }

    @ApiOperation(value = "차량지급 수정", notes = "입력된 데이터에 따른 차량지급 수정")
    @PutMapping
    public SaveResponse editBusinessVehicle(
        @RequestBody
        @Valid
        EditReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.editBusinessVehicle(dto))
            .build();
    }

    @ApiOperation(value = "차량정보 목록 조회", notes = "SAP에서 인터페이스한 차량 정보 목록 조회")
    @GetMapping("/all-vehicles")
    public List<SearchVehiclesRes> getAllVehicles() {
        return service.getAllVehicles();
    }
}
