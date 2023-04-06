package com.kyowon.sms.wells.web.deduction.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementInterfaceDto;
import com.kyowon.sms.wells.web.deduction.interfaces.service.WdecRdsProductDisbursementInterfaceService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst; //wells
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "Wells RDS 지급액 처리 인터페이스")
@RequestMapping(value = DeDeductionConst.INTERFACE_URL_V1)
@RequiredArgsConstructor
@Validated
public class WdecRdsDisbursementInterfaceController {

    private final WdecRdsProductDisbursementInterfaceService service;

    @ApiOperation(value = "Wells RDS 지급액 처리", notes = "EDU에서 요청한 RDS 지급액을 wells RDS 지급액으로 사용처리를 위한 인터페이스")
    @PostMapping("/rds-disbursement")
    public EaiWrapper createRdsDisbursement(
        @Valid
        @RequestBody
        EaiWrapper<WdecRdsProductDisbursementInterfaceDto.SaveReq> reqWrapper
    ) {

        //Response용 EaiWrapper 생성
        EaiWrapper<WdecRdsProductDisbursementInterfaceDto.SaveRes> resWrapper = reqWrapper.newResInstance();

        //return 파라미터
        String[] returnValue = service.createRdsDisbursement(reqWrapper.getBody());

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(
            WdecRdsProductDisbursementInterfaceDto.SaveRes.builder()
                .rdsDsbRefId(returnValue[0]) //RDS지급참조ID
                .rsCd(returnValue[1]) //결과코드
                .rsMsg(returnValue[2]) //결과메시지
                .build()
        );

        return resWrapper;
    }

}
