package com.kyowon.sms.wells.web.closing.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.closing.interfaces.dto.WdccHomeCareBreachPromiseAmtInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.interfaces.dto.WdccHomeCareBreachPromiseAmtInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.closing.interfaces.service.WdccHomeCareBreachPromiseAmtInterfaceService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WDCC] EAI_WCLI1001 홈케어멤버십위약금조회 I/F ")
@RequestMapping(DcClosingConst.INTERFACE_URL_V1 + "/home-care-breach-promise")
@RequiredArgsConstructor
@Validated
public class WdccHomeCareBreachPromiseAmtInterfaceController {
    private final WdccHomeCareBreachPromiseAmtInterfaceService service;

    @ApiOperation(value = "[EAI_WCLI1001] 홈케어멤버십위약금조회", notes = "고객응대를 위한 고객센터 홈케어멤버십 계약의 위약금액 조회")
    @PostMapping
    public EaiWrapper getHomeCareBreachPromiseAmt(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출
        FindRes res = service.getOtherLumpSumPerformance(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
