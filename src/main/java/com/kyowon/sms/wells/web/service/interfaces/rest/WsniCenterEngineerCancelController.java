package com.kyowon.sms.wells.web.service.interfaces.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterEngineerCancelService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WSNI] 고객센터 엔지니어 취소 건수 조회 인터페이스")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/engineer-cancels")
public class WsniCenterEngineerCancelController {
    private final WsniCenterEngineerCancelService service;

    @ApiOperation(value = "고객센터 엔지니어 취소 건수 조회", notes = "고객센터 엔지니어 취소 건수 조회")
    @GetMapping
    public int getEngineerCancels(
        @RequestParam
        String userId
    ) {
        return service.getEngineerCancelInquiry(userId);
    }
}
