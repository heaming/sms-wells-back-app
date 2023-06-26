package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFinishLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFinishLocationDto.SearchReq;

import com.kyowon.sms.wells.web.service.visit.service.WsnbFinishLocationService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/finish-location")
@Api(tags = "[WSNB] 완료 위치 내역 조회")
@RequiredArgsConstructor
@Validated
public class WsnbFinishLocationController {

    private final WsnbFinishLocationService service;

    @ApiOperation(value = "완료 위치 내역 조회", notes = "완료건에 대한 위치 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asnOjYm", value = "방문년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "mngtDvCd", value = "관리구분", paramType = "query", example = ""),
        @ApiImplicitParam(name = "fshBaseDstn", value = "기준거리", paramType = "query", example = ""),
        @ApiImplicitParam(name = "ogTpCd", value = "조직타입코드", paramType = "query", example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너코드", paramType = "query", example = ""),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getFinishLocations(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getFinishLocations(dto, pageInfo);
    }
}
