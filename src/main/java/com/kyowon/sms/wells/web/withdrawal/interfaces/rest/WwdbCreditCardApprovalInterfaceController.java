package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaCreditCardApprovalInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbCreditCardApprovalInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WDB] 신용카드 승인 관리")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/credit-card-approvals")
@RequiredArgsConstructor
@Validated
public class WwdbCreditCardApprovalInterfaceController {

    private final WwdbCreditCardApprovalInterfaceService service;

    @ApiOperation(value = "Wells 카드즉시 결제 - W-WD-I-0028")
    @PostMapping
    public EaiWrapper createCreditCardApproval(
        @Valid
        @RequestBody
        EaiWrapper<WwdaCreditCardApprovalInterfaceDto.SaveReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<WwdaCreditCardApprovalInterfaceDto.SaveRes> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        WwdaCreditCardApprovalInterfaceDto.SaveRes res = service.createCreditCardApproval(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "Wells 카드즉시 결제 알림톡 알림톡 발송 - W-WD-I-0029")
    @PostMapping("/sms")
    public EaiWrapper createCreditCardApprovalNotification(
        @Valid
        @RequestBody
        EaiWrapper<WwdaCreditCardApprovalInterfaceDto.SaveNotificationReq> reqWrapper
    ) throws Exception {

        // Response용 EaiWrapper 생성
        EaiWrapper<WwdaCreditCardApprovalInterfaceDto.SaveRes> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        WwdaCreditCardApprovalInterfaceDto.SaveRes res = service
            .createCreditCardApprovalNotification(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
