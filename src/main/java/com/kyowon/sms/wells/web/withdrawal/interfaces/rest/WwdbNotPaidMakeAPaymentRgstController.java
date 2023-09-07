package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbNotPaidMakeAPaymentRgstDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentRgstResDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbNotPaidMakeAPaymentRgstService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
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
@Api(tags = "[WWDB] 미납금 납부 등록 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/not-paid/make-payment-registrations")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WwdbNotPaidMakeAPaymentRgstController {
    private final WwdbNotPaidMakeAPaymentRgstService service;

    @ApiOperation(value = "[EAI_WDEI1003] 미납금 납부 등록(W-WD-S-0050)")
    @PostMapping
    public EaiWrapper saveDepositRegistration(
        @Valid
        @RequestBody
        EaiWrapper<WwdbNotPaidMakeAPaymentRgstDto.SaveReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<WwdbNotPaidMakeAPaymentRgstDto.SaveRes> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        WwdbNotPaidMakeAPaymentRgstResDvo res = service.saveDepositRegistration(reqWrapper.getBody());
        // Response Body 세팅
        resWrapper.setBody(
            WwdbNotPaidMakeAPaymentRgstDto.SaveRes.builder()
                .procsRs(res.getProcsRs())
                .errMsg(res.getErrMsg())
                .build()
        );

        return resWrapper;
    }
}
