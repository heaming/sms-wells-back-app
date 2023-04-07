package com.kyowon.sms.wells.web.service.interfaces.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterEngineerCancelFinishService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WSNI] 고객센터 엔지니어 취소 완료처리 인터페이스")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/cancel-finish")
public class WsniCenterEngineerCancelFinishController {
    private final WsniCenterEngineerCancelFinishService service;

    @ApiOperation(value = "고객센터 엔지니어 취소 완료처리")
    @PostMapping
    public String editCenterEngineerCancelFinish(
        @RequestParam(required = true)
        String asIstOjNo
    ) {
        return service.editCenterEngineerCancelFinish(asIstOjNo);
    }
}
