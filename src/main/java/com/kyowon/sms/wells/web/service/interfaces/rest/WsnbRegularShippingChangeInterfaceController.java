package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.*;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbRegularShippingChangeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@InterfaceController
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/regular-shipping-change")
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": [WSNB] 정기배송 변경 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbRegularShippingChangeInterfaceController {

    private final WsnbRegularShippingChangeService service;

    @ApiOperation(value = "정기배송 변경처리")
    @ApiImplicitParams(value = {
    })
    @PostMapping
    public EaiWrapper editRegularShippingChange(
        @RequestBody
        @Valid
        EaiWrapper<SaveReq> reqEaiWrapper
    ) {
        EaiWrapper<SaveRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.saveRegularShippingChange(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }
}
