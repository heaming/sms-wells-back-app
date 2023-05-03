package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterFilterShpadrInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterFilterShpadrInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterFilterShpadrInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/filter-shpadr")
@RequiredArgsConstructor
@Validated
public class WsniCenterFilterShpadrInterfaceController {
    private final WsniCenterFilterShpadrInterfaceService service;

    @GetMapping
    public EaiWrapper getFilterShpadrs(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqEaiWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getFilterShpadrs(reqEaiWrapper.getBody()));

        return resEaiWrapper;
    }
}
