package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelDto.FindReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterEngineerCancelDto.FindRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterEngineerCancelService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

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
    @PostMapping
    public EaiWrapper getEngineerCancels(
        @RequestBody
        @Valid
        EaiWrapper<FindReq> reqWrapper
    ) {
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerCancelInquiry(reqWrapper.getBody()));

        return resWrapper;

        //        EaiWrapper<List<WsniCustomerCenterInterfaceDto.SearchCancelRes>> resWrapper = reqWrapper.newResInstance();
        //        resWrapper.setBody(service.getEngineerCancels(reqWrapper.getBody()));
        //
        //        return resWrapper;
    }
}
