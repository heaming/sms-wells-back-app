package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdcSalesControlItemizationInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdcSalesControlItemizationInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@InterfaceController
@Api(tags = "[WWDC] Wells 매출조정 내역 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/sales-control-itemizations")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WwdcSalesControlItemizationInterfaceController {

    private final WwdcSalesControlItemizationInterfaceService service;

    @ApiOperation(value = "[EAI_WWDI1021] Wells 매출조정 내역 조회 - W-WD-I-0022")
    @PostMapping
    public EaiWrapper getSalesControlItemizations(
        @Valid
        @RequestBody
        EaiWrapper<WwdcSalesControlItemizationInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdcSalesControlItemizationInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdcSalesControlItemizationInterfaceDto.SearchRes> res = service
            .getSalesControlItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
