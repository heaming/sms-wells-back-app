package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.*;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniSidingServiceChangesService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 모종 상품/서비스 변경 처리")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/siding-product-service-change")
@RequiredArgsConstructor
@Validated
public class WsniSidingServiceChangesController {

    private final WsniSidingServiceChangesService service;

    @ApiOperation(value = "모종 상품/서비스 변경 처리", notes = "W-SV-I-0025 모종 상품/서비스 변경 처리하는 API")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약순번", paramType = "query", required = true),
    })
    @PostMapping
    public EaiWrapper saveSidingProductChange(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqEaiWrapper
    ) throws Exception {
        EaiWrapper<SaveRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.saveSidingProductChange(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
