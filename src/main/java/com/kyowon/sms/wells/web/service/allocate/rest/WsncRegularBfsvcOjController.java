package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcOjDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcOjService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNF] 정기 B/S 대상 선정")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/regular-bs-object")
@Slf4j
public class WsncRegularBfsvcOjController {

    private final WsncRegularBfsvcOjService service;

    @ApiOperation(value = "정기 B/S 대상 선정 - 생성", notes = "정기 B/S 대상 선정")
    @PostMapping
    public SaveResponse createRegularBfsvcOj(
        @Valid
        @RequestBody
        WsncRegularBfsvcOjDto.CreateReq dto
    ) throws Exception {
        log.info(
            "[WsncRegularBfsvcOjController.createRegularBfsvcOj] WsncRegularBfsvcOjJob-Create batch Job execute."
                + dto.asnOjYm() + " / " + dto.createTarget()
        );

        //TODO Job Execution
        return SaveResponse.builder()
            .processCount(service.createRegularBfsvcOj(dto))
            .build();
    }
}
