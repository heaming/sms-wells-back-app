package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbDepositRefundInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbSinglePaymentPerformanceInfoInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbSinglePaymentPerformanceInfoInterfaceService;
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
@Api(tags = "[WWDB] wells 일시불(할부)- 실적 정보 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/single-payment-infos")
@RequiredArgsConstructor
@Validated
public class WwdbSinglePaymentPerformanceInfoInterfaceController {

    private final WwdbSinglePaymentPerformanceInfoInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1005] wells 입금/환불 목록 조회")
    @PostMapping
    public EaiWrapper getSinglePaymentPerformanceInfos(
        @Valid
        @RequestBody
        EaiWrapper<WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdbSinglePaymentPerformanceInfoInterfaceDto.SearchRes> res = service.getSinglePaymentPerformanceInfos(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
