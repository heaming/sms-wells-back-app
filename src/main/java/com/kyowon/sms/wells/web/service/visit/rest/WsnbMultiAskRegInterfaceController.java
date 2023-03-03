package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultiAskRegInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultiAskRegInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbMultiAskRegInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

// TODO: API 스펙 확인 후 수정 필요
@InterfaceController
@RequestMapping(SnServiceConst.INTERFACE_URL_V1 + "multiple-ask-registration")
@Api(tags = "[WSNB] A/S, 분리, 재설치 및 설치정보 변경 등록 API")
@RequiredArgsConstructor
@Validated
public class WsnbMultiAskRegInterfaceController {

    private final WsnbMultiAskRegInterfaceService service;

    @ApiOperation(value = "A/S, 분리, 재설치, 설치 정보 변경", notes = "고객이 요청한 A/S, 분리, 재설치, 설치 정보를 변경 등록한다.")
    @PostMapping
    public EaiWrapper saveMultiAsks(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqEaiWrapper
    ) {
        EaiWrapper<SaveRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.saveMultiAsks(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
