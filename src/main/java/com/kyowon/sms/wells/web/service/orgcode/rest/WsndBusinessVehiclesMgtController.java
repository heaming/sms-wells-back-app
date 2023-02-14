package com.kyowon.sms.wells.web.service.orgcode.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndBusinessVehiclesMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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
}
