package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountDepositInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountDepositInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbVirtualAccountDepositInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WWDB] wells 가상계좌 입금 처리 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve")
@RequiredArgsConstructor
@Validated
public class WwdbVirtualAccountDepositInterfaceController {

    private final WwdbVirtualAccountDepositInterfaceService service;

    @ApiOperation(value = "[EAI_] wells 가상계좌 입금 처리")
    @PostMapping("/virtual-account-deposit")
    public EaiWrapper saveVirtualAccountDeposit(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<SearchRes> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        SearchRes res = service.saveVirtualAccountDeposit(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
