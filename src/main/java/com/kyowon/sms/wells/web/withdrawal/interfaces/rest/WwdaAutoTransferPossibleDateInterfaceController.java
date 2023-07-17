package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferPossibleDateInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdaAutoTransferPossibleDateInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@InterfaceController
@Api(tags = "[WWDB] wells 자동이체 가능일자 조회 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/automatic-transfer-possible-date")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WwdaAutoTransferPossibleDateInterfaceController {

    private final WwdaAutoTransferPossibleDateInterfaceService service;

    @ApiOperation(value = "[EAI_WWDI1011] wells 카드 자동이체 가능일자 조회 - W-WD-I-0012")
    @PostMapping("/cards")
    public EaiWrapper getPassibleDatesByCard(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferPossibleDateInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferPossibleDateInterfaceDto.SearchRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferPossibleDateInterfaceDto.SearchRes> res = service
            .getPassibleDatesByCard(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1012] wells 계좌 자동이체 가능일자 조회 - W-WD-I-0013")
    @PostMapping("/accounts")
    public EaiWrapper getPassibleDatesByAcount(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferPossibleDateInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferPossibleDateInterfaceDto.SearchRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferPossibleDateInterfaceDto.SearchRes> res = service
            .getPassibleDatesByAcount(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
