package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbVirtualAccountInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WWDB] wells 가상계좌 발급 내역 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/virtual-account")
@RequiredArgsConstructor
@Validated
public class WwdbVirtualAccountInterfaceController {

    private final WwdbVirtualAccountInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1008] wells 가상계좌 발급 내역 조회")
    @PostMapping("/issues")
    public EaiWrapper getVirtualAccountIssues(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchRes> res = service.getVirtualAccountIssues(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1025] Wells 가상계좌 발급 - W-WD-I-0027")
    @PostMapping
    public EaiWrapper createVirtualAccount(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqWrapper
    ) throws ParseException {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SaveRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        SaveRes res = service.createVirtualAccount(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(Collections.singletonList(res));

        return resWrapper;
    }
}
