package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dto.WsnyBeforeServiceBizClDto;
import com.kyowon.sms.wells.web.service.common.service.WsnyBeforeServiceBizClService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNF] BS 업무시간및 마감관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/business-closes")
public class WsnyBeforeServiceBizClController {
    private final WsnyBeforeServiceBizClService wsnyBeforeServiceBizClService;

    @ApiOperation(value = "BS 업무시간및 마감관리 화면 - BS 업무시간및 마감관리 조회", notes = "조회조건에 따른 BS 업무시간및 마감관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "managementYear", value = "관리년도", paramType = "query", required = false),
        @ApiImplicitParam(name = "managementItem", value = "관리항목", paramType = "query", required = false),
    })
    @GetMapping
    public List<WsnyBeforeServiceBizClDto.SearchRes> getBusinessCloses(
        WsnyBeforeServiceBizClDto.SearchReq dto
    ) {
        return wsnyBeforeServiceBizClService.getBusinessCloses(dto);
    }

    @ApiOperation(value = "BS 업무시간및 마감관리 화면 - BS 업무시간및 마감관리 등록 및 수정", notes = "BS 업무시간및 마감관리 저장")
    @PostMapping
    public SaveResponse saveCalendar(
        @Valid
        @RequestBody
        List<WsnyBeforeServiceBizClDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wsnyBeforeServiceBizClService.saveBusinessCloses(dtos))
            .build();
    }
}
