
package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTaskTypeItemSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbTaskTypeItemSearchDto.SearchRes;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbTaskTypeItemSearchService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/task-type-item")
@Api(tags = "[WSNB] K-W-SV-U-0075M01 업무유형별 품목 현황 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbTaskTypeItemSearchController {

    private final WsnbTaskTypeItemSearchService service;

    @ApiOperation(value = "업무유형별 품목 현황")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "taskType", value = "업무유형", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "inqrBase", value = "조회기준", paramType = "query", example = "0"),
        @ApiImplicitParam(name = "baseDateFrom", value = "기준일자from", paramType = "query", example = "20230601"),
        @ApiImplicitParam(name = "baseDateTo", value = "기준일자to", paramType = "query", example = "20230608"),
        @ApiImplicitParam(name = "wkSts", value = "작업상태", paramType = "query", example = "20"),
        @ApiImplicitParam(name = "istBase", value = "설치기준", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query", example = "Y"),
    })
    @GetMapping
    public List<SearchRes> getTaskTypeItemList(
        SearchReq dto
    ) {
        return service.getTaskTypeItemList(dto);
    }
}
