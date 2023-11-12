package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbServiceRefundService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 서비스 환불 등록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/service-refund")
public class WwdbServiceRefundController {

    private final WwdbServiceRefundService service;

    @ApiOperation(value = "환불신청 등록", notes = "서비스 환불 등록되는 환불신청정보를 등록한다")
    @PostMapping
    public SaveResponse saveServiceRefund(
        @RequestBody
        @Valid
        SaveReq req
    ) {
        return SaveResponse.builder()
            .data(service.saveServiceRefund(req))
            .build();
    }

}
