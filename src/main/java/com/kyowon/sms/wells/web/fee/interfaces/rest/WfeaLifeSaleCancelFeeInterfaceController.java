package com.kyowon.sms.wells.web.fee.interfaces.rest;

import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto.SaveRes;
import com.kyowon.sms.wells.web.fee.interfaces.service.WfeaLifeSaleCancelFeeInterfaceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
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

@InterfaceController
@Api(tags = "[WFEA] EAI_WCOI1001 LIFE 상조 판매/취소 실적 수신 서비스")
@RequestMapping(value = CtFeeConst.INTERFACE_URL_V1)
@RequiredArgsConstructor
@Validated
public class WfeaLifeSaleCancelFeeInterfaceController {

    private final WfeaLifeSaleCancelFeeInterfaceService service;

    @ApiOperation(value = "[EAI_WCOI1001] LIFE 상조 판매/취소 실적 수신 서비스(일일/월마감)", notes = "LIFE 상조 판매/취소 실적 수신 서비스(일일/월마감) - 호출 시스템 : KSS LIFE")
    @PostMapping("/alnc-fee")
    public EaiWrapper createRdsProductDisbursementHoldings(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqWrapper
    ) throws Exception {
        // 1. Response용 EaiWrapper 생성
        EaiWrapper<SaveRes> resWrapper = reqWrapper.newResInstance();

        // 2. return 파라미터
        String[] returnValue = service.updateLifeFeeSync(reqWrapper.getBody());

        // 3. 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(
            SaveRes.builder()
                .rsCd(returnValue[0])
                .rsMsg(returnValue[1])
                .build()
        );
        return resWrapper;
    }

}
