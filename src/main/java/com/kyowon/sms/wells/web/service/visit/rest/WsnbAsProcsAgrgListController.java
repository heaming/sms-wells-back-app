
package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbAsProcsAgrgListDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbAsProcsAgrgListDto.SearchRes;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbAsProcsAgrgListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-procs-agrg")
@Api(tags = "[WSNB] K-W-SV-U-0025M01 A/S 처리 현황 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbAsProcsAgrgListController {

    private final WsnbAsProcsAgrgListService service;

    @ApiOperation(value = "A/S 처리 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202306"),
        @ApiImplicitParam(name = "taskType", value = "업무유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WM01100137"),
        @ApiImplicitParam(name = "chkYn", value = "상품그룹코드", paramType = "query", example = "Y"),
    })
    @GetMapping
    public List<SearchRes> getAsProcsAgrgList(
        SearchReq dto
    ) {
        return service.getAsProcsAgrgList(dto);
    }
}
