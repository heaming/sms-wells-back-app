package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto.FindReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniChkOverdueCustDto.FindRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniChkOverdueCustService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 프로그램 명세서 미 존재로 설명 없음
 *
 * 프로그램 아이디 : K-W-SS-S-0003
 * 인터페이스 아이디 :  EAI_WSSI1097 
 * */
@InterfaceController
@Api(tags = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/edicard")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/edicard")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniChkOverdueCustInterfaceController {

    private final WsniChkOverdueCustService service;

    @ApiOperation(value = "Wells 연체 고객 체크")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "CNTR_CST_NO", value = "계약고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "DLQ_MCN", value = "연체개월수", paramType = "query"),
    })
    @PostMapping("/wells-check-overdue-customers")
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
