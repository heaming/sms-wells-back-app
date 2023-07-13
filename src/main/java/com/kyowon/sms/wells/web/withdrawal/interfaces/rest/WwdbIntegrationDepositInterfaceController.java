package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.*;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdbIntegrationDepositInterfaceService;
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
@Api(tags = "[WWDB] 공통 통합입금 내역 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/integration-deposit")
@RequiredArgsConstructor
@Validated
public class WwdbIntegrationDepositInterfaceController {

    private final WwdbIntegrationDepositInterfaceService service;

    @ApiOperation(value = "[EAI_WDEI1011] 공통 통합입금 내역 회사선택 코드 조회 - W-WD-I-0023")
    @PostMapping("/company-codes")
    public EaiWrapper getIntegrationDepositCompanyCode(
        @Valid
        @RequestBody
        EaiWrapper<SearchCompanyCodeReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchCompanyCodeRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchCompanyCodeRes> res = service.getIntegrationDepositCompanyCode(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WDEI1012] 공통 통합입금 내역 입금유형 코드 조회 - W-WD-I-0024")
    @PostMapping("/deposit-codes")
    public EaiWrapper getIntegrationDepositDepoCode(
        @Valid
        @RequestBody
        EaiWrapper<SearchDepoCodeReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchDepoCodeRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchDepoCodeRes> res = service.getIntegrationDepositDepoCode(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WDEI1013] 공통 통합입금 내역 세부 거래내역 조회 - W-WD-I-0025")
    @PostMapping("/detail-trades")
    public EaiWrapper getIntegrationDepositDetailTrades(
        @Valid
        @RequestBody
        EaiWrapper<SearchDetailTradeReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchDetailTradeRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchDetailTradeRes> res = service.getIntegrationDepositDetailTrades(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WDEI1014] 공통 통합입금 내역 조회 - W-WD-I-0026")
    @PostMapping("/itemizations")
    public EaiWrapper getIntegrationDepositItemizations(
        @Valid
        @RequestBody
        EaiWrapper<SearchItemizationReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchItemizationRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<SearchItemizationRes> res = service.getIntegrationDepositItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
