package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbDepositService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WWDB] 입금처리")
@RestController
@RequiredArgsConstructor
@RequestMapping(WdWithdrawalConst.REST_URL_V1 + "/idvrve/deposit")
public class WwdbDepositController {

    private final WwdbDepositService service;

    @ApiOperation(value = "입금상세")
    @PostMapping
    public SaveResponse saveDepositDetails(
        WwdbDepositDto.SaveReq dto
    ) {
        return service.saveDepositDetails(dto);
    }
}
