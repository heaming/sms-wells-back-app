package com.kyowon.sms.wells.web.service.orgcode.rest;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndHumanResourcesDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.orgcode.service.WsndHumanResourcesService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSND] 인사기본정보")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/human-resources")
public class WsndHumanResourcesController {
    private final WsndHumanResourcesService service;

    @ApiOperation(value = "인사기본정보 조회", notes = "조회조건에 따른 작업 인사기본정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngrDvCd", value = "담당구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "deptCd", value = "총국", paramType = "query", required = false),
        @ApiImplicitParam(name = "cnrCd", value = "소속센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "searchText", value = "성명/사번", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getHumanResourcesPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getHumanResourcesPages(dto, pageInfo);
    }

    @ApiOperation(value = "조직 조회", notes = "조직 조회")
    @GetMapping("/organizations")
    public List<SearchOrganizationRes> getOrganizations() {
        return service.getOrganizations();
    }

}
