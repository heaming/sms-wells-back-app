package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBsServiceHistInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniBsServiceHistInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/bs-servicehists")
@RequiredArgsConstructor
@Validated
public class WsniBsServiceHistInterfaceController {
    private final WsniBsServiceHistInterfaceService service;

    @ApiOperation(value = "W-SV-I-0006 웰스홈페이지 서비스이력 조회")
    @PostMapping
    public EaiWrapper getBsServiceHistories(
        @Valid
        @RequestBody
        EaiWrapper<WsniBsServiceHistInterfaceDto.SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<WsniBsServiceHistInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getBsServiceHistories(reqWrapper.getBody()));
        return resWrapper;
    }
}
