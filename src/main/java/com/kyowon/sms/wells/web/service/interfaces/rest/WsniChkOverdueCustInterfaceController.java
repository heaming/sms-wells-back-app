package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto.FindReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto.FindRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniChkOverdueCustService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": Wells 연체 고객 체크")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniChkOverdueCustInterfaceController {

    private final WsniChkOverdueCustService service;

    @ApiOperation(value = "Wells 연체 고객 체크")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "dlqMcn", value = "연체개월수", paramType = "query"),
    })
    @PostMapping()
    public EaiWrapper selectChkOverdueCust(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) throws Exception {

        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        FindRes res = service.selectChkOverdueCust(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
