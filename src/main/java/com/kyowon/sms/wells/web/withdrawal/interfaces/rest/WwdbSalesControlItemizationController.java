package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.SalesControlItemizationDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferPossibleDateInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbSalesControlItemizationService;
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
import java.util.List;

@InterfaceController
@Api(tags = "[WWDI] Wells 매출조정 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/sales-control")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WwdbSalesControlItemizationController {

    private final WwdbSalesControlItemizationService service;

    @ApiOperation(value = "[EAI_WWDI1028] Wells 매출조정 내역 조회")
    @PostMapping("/itemizations")
    public EaiWrapper getSalesControlItemizations(
        @Valid
        @RequestBody
        EaiWrapper<SalesControlItemizationDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SalesControlItemizationDto.SearchRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<SalesControlItemizationDto.SearchRes> res = service
            .getSalesControlItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
