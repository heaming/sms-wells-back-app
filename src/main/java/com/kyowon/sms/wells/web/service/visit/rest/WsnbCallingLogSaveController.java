package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCallingLogSaveDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCallingLogSaveService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/calling-log-save")
@Api(tags = "[WSNB] PR_KIWI_WRK_CREATE_V2M 호출 로그 저장 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsnbCallingLogSaveController {
    private WsnbCallingLogSaveService service;

    @ApiOperation(value = "PR_KIWI_WRK_CREATE_V2M 호출 로그 저장", notes = "PR_KIWI_WRK_CREATE_V2M 호출 로그 저장")
    @PostMapping
    public SaveResponse createCallingLog(
        @Valid
        @RequestBody
        @NotEmpty
        CreateReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createCallingLog(dto))
            .build();
    }

}
