package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbDepositRefundInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbDepositRefundInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
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
import java.util.List;

@InterfaceController
@Api(tags = "[WWDB] wells 입금/환불 목록 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/deposit-refunds")
@RequiredArgsConstructor
@Validated
public class WwdbDepositRefundInterfaceController {

    private final WwdbDepositRefundInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1004] wells 입금/환불 목록 조회")
    @PostMapping
    public EaiWrapper getDepositRefunds(
        @Valid
        @RequestBody
        EaiWrapper<WwdbDepositRefundInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdbDepositRefundInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdbDepositRefundInterfaceDto.SearchRes> res = service.getDepositRefunds(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
