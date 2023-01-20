package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCenterLocalAreaTfDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncCenterLocalAreaTfService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNF] 센터지역 이관")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/center-area")
public class WsncCenterLocalAreaTfController {

    private final WsncCenterLocalAreaTfService wsncCenterLocalAreaTfService;

    @ApiOperation(value = "센터지역 이관 화면 - 센터지역 이관 조회", notes = "조회조건에 따른 센터지역 이관 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wareDvCd", value = "관리구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "wareAreaCd", value = "관리지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "zipFrom", value = "우편번호(From)", paramType = "query", required = false),
        @ApiImplicitParam(name = "zipTo", value = "우편번호(To)", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsonCd", value = "이관사유", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<WsncCenterLocalAreaTfDto.SearchRes> getCenterAreas(
        WsncCenterLocalAreaTfDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return wsncCenterLocalAreaTfService.getCenterAreas(dto, pageInfo);
    }

    @ApiOperation(value = "센터지역 이관 화면 - 센터지역 이관 등록 및 수정", notes = "센터지역 이관 저장")
    @PostMapping
    public SaveResponse insertCenterAreas(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsncCenterLocalAreaTfDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wsncCenterLocalAreaTfService.insertCenterAreas(dtos))
            .build();
    }

}
