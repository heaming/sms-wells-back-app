package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniWealthContractDetailsDto.FindReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniWealthContractDetailsDto.FindRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniWealthContractDetailsService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 프로그램 명세서 미 존재로 설명 없음
 * 프로그램 아이디 : K-W-SS-S-0002
 * */
@InterfaceController
@Api(tags = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/edicard")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/idvrve/edicard")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniWealthContractDetailsInterfaceController {

    private final WsniWealthContractDetailsService service;

    @ApiOperation(value = "(마이K)웰스_계약 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "CNTR_NO", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "CNTR_SN", value = "계약일련번호", paramType = "query"),
    })
    @PostMapping("/wealth-contract-details")
    public EaiWrapper selectWealthContractDetails(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) throws Exception {

        // Response용 EaiWrapper 생성
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        FindRes res = service.selectWealthContractDetails(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

}
