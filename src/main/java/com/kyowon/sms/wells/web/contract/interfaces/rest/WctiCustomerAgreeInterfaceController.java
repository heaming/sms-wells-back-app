package com.kyowon.sms.wells.web.contract.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerAgreeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.service.WctiCustomerAgreeService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCTI] 고객센터I/F")
@RequestMapping(value = CtContractConst.INTERFACE_URL_V1 + "/customer-centers")
@RequiredArgsConstructor
@Validated
public class WctiCustomerAgreeInterfaceController {
    private final WctiCustomerAgreeService service;

    @ApiOperation(value = "[EAI_WCUI1014] 개인정보 동의 현황 조회", notes = "고객번호 또는 계약번호 단위의 개인정보 동의 현황 조회한다.")
    @PostMapping("/customer-agree-lists")
    public EaiWrapper getCustomerAgree(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getCustomerAgree(reqWrapper.getBody()));

        return resWrapper;
    }
}
