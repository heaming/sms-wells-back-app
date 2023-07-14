package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbCashReceiptApprovalInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbCashReceiptApprovalInterfaceService;
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
@Api(tags = "[WWDB] wells 현금영수증 승인 내역 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/cachreceipt-approval")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WwdbCashReceiptApprovalInterfaceController {

    private final WwdbCashReceiptApprovalInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1010] wells 현금영수증 승인 내역 조회 - W-WD-I-0009")
    @PostMapping("/itemizations")
    public EaiWrapper getCashReceiptApprovalItemizations(
        @Valid
        @RequestBody
        EaiWrapper<WwdbCashReceiptApprovalInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdbCashReceiptApprovalInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdbCashReceiptApprovalInterfaceDto.SearchRes> res = service
            .getCashReceiptApprovalItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
