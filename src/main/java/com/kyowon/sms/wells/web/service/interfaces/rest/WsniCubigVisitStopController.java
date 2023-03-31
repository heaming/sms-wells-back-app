package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCubigVisitStopDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCubigVisitStopService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/visit-stops")
public class WsniCubigVisitStopController {
    private final WsniCubigVisitStopService service;

    @ApiOperation(value = "Cubig CC 방문중지 등록 인터페이스")
    @PostMapping
    public EaiWrapper createCubigVisitStop(
        @RequestBody
        @Valid
        EaiWrapper<CreateReq> reqEaiWrapper
    ) throws Exception {
        EaiWrapper<CreateRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.createCubigVisitStop(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }
}
