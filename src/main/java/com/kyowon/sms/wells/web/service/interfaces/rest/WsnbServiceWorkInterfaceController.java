package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbServiceWorkInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbServiceWorkInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbServiceWorkInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

// TODO: API 스펙 확인 후 수정 필요
@InterfaceController
@RequestMapping(SnServiceConst.INTERFACE_URL_V1 + "service-works")
@Api(tags = "[WSNB] 타시스템용 설치/AS/BS/홈케어 서비스 작업 오더 생성 API")
@RequiredArgsConstructor
@Validated
public class WsnbServiceWorkInterfaceController {

    private final WsnbServiceWorkInterfaceService service;

    @ApiOperation(value = "설치/AS/BS/홈케어 서비스 작업 오더 생성", notes = "타시스템(kyowonwells, CubigCC, kmembers)에서 설치/AS/BS/홈케어 서비스 작업 오더 생성을 위해 사용한다.")
    @PostMapping
    public EaiWrapper createServiceWorks(
        @Valid
        @RequestBody
        EaiWrapper<CreateReq> reqEaiWrapper
    ) throws Exception {
        EaiWrapper<CreateRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.createServiceWorks(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
