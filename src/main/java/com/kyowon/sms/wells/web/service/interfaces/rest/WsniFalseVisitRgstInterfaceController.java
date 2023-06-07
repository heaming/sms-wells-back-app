package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFalseVisitRgstInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFalseVisitRgstInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniFalseVisitRgstInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 고객 센터 인터페이스 허위 방문 등록 인터페이스")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/false-visit")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniFalseVisitRgstInterfaceController {

    private final WsniFalseVisitRgstInterfaceService service;

    @ApiOperation(value = "고객 센터 인터페이스 허위 방문 등록", notes = "고객센터 상담원이 BS 허위방문 내용을 등록 한다")
    @PostMapping
    public EaiWrapper createFalseVisit(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqWrapper
    ) {
        EaiWrapper<SaveRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.createFalseVisit(reqWrapper.getBody()));
        return resWrapper;
    }
}
