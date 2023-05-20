package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.service.WsniEaiCommonService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/sn-eai-commons")
@RequiredArgsConstructor
@Validated
public class WsniEaiCommonInterfaceController {

    private final WsniEaiCommonService service;

    @ApiOperation(value = "EAI Interface (SN Common)")
    @PostMapping
    public EaiWrapper processEaiCommonInterface(@RequestBody EaiWrapper<Map<String, Object>> reqWrapper) throws IOException, Exception {
        EaiWrapper<Map<String, Object>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.doit(reqWrapper.getBody()));
        return resWrapper;
    }
}
