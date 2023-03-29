package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbMultipleRequestRegInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbMultipleRequestRegInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbMultipleRequestRegInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

// TODO: API 스펙 확인 후 수정 필요
@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1+": A/S, 분리, 재설치 및 설치정보 변경 등록")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "multiple-request-registration")
//@Api(tags = "[WSNB] A/S, 분리, 재설치 및 설치정보 변경 등록 API")
@RequiredArgsConstructor
@Validated
public class WsnbMultipleRequestRegInterfaceController {

    private final WsnbMultipleRequestRegInterfaceService service;

    @ApiOperation(value = "A/S, 분리, 재설치, 설치 정보 변경", notes = "고객이 요청한 A/S, 분리, 재설치, 설치 정보를 변경 등록한다.")
    @PostMapping
    public EaiWrapper saveMultiRequests(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqEaiWrapper
    ) {
        EaiWrapper<SaveRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.saveMultiRequests(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
