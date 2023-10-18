package com.kyowon.sms.wells.web.competence.interfaces.rest;

import com.kyowon.sms.common.web.competence.zcommon.constants.PsCompetenceConst;
import com.kyowon.sms.wells.web.competence.interfaces.service.WpskPinatMetgCheckService;
import com.kyowon.sms.wells.web.competence.interfaces.dto.WpskPinatMetgCheckDto.CreateRes;
import com.kyowon.sms.wells.web.competence.interfaces.dto.WpskPinatMetgCheckDto.CreateReq;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@InterfaceController
@Api(tags = "[WPSK] 핀앳) 출결앱 미팅 체크 I/F")
@Validated
@RequiredArgsConstructor
@RequestMapping(PsCompetenceConst.INTERFACE_URL_V1 + "/pinat-meeting-check")
public class WpskPinatMetgCheckInterfaceController {

    private final WpskPinatMetgCheckService service;

    @ApiOperation(value = "[] 핀앳) 출결앱 미팅 체크", notes = "핀앳 미팅 체크")
    @PostMapping
    public EaiWrapper createBeaconMeetingCheck(
        @RequestBody
        @Valid
        EaiWrapper<CreateReq> reqWrapper
    ) {
         // Response용 EaiWrapper 생성
        EaiWrapper<CreateRes> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        CreateRes res = service.createBeaconMeetingCheck(reqWrapper.getBody());
        // Response Body 세팅
        resWrapper.setBody(res);
        return resWrapper;
    }


}
