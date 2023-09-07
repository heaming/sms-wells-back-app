package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelFinishInterfaceDto.EditReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelFinishInterfaceDto.EditRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterEngineerCancelFinishService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WSNI] 고객센터 엔지니어 취소 완료처리 인터페이스")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/cancel-finish")
public class WsniCenterEngineerCancelFinishInterfaceController {
    private final WsniCenterEngineerCancelFinishService service;

    @ApiOperation(value = "고객센터 엔지니어 취소 완료처리")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "asIstOjNo", value = "계약번호", paramType = "query", required = true),
    })
    @PostMapping
    public EaiWrapper editCenterEngineerCancelFinish(
        @Valid
        @RequestBody
        EaiWrapper<EditReq> reqWrapper
    ) {
        EaiWrapper<EditRes> resEaiWrapper = reqWrapper.newResInstance();
        resEaiWrapper.setBody(service.editCenterEngineerCancelFinish(reqWrapper.getBody()));
        return resEaiWrapper;
    }
}
