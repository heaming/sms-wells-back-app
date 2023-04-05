package com.kyowon.sms.wells.web.deduction.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.service.WdecRdsProductDisbursementHoldingService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst; //wells
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "wells RDS 상품 지급 보류 인터페이스")
@RequestMapping(value = DeDeductionConst.INTERFACE_URL_V1)
@RequiredArgsConstructor
@Validated
public class WdecRdsProductDisbursementHoldingInterfaceController {

    private final WdecRdsProductDisbursementHoldingService service;

    @ApiOperation(value = "wells RDS 상품 지급 보류 상세 조회", notes = "EDU에서 파트너 개별 RDS 상품 지급 보류 상세 조회 요청 시 wells 파트너 개별 RDS 상품 지급 보류 상세 조회을 위한 인터페이스")
    @GetMapping("/rds-product-disbursement-holdings")
    public EaiWrapper getRdsProductDisbursementHoldings(
        @Valid
        @RequestBody
        EaiWrapper<FindReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        // 조회 결과는 Integer 이지만, 인터페이스 응답양식(key-value 형태)에 맞추기 위해 DTO 형식으로 선언
        EaiWrapper<FindRes> resWrapper = reqWrapper.newResInstance();

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(service.getRdsProductDisbursementHoldings(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "wells RDS 상품 지급 보류금액 처리", notes = "EDU에서 RDS 상품 지급 보류금액 생성 요청 시 wells RDS 상품 지급 보류금액 생성을 위한 인터페이스")
    @PostMapping("/rds-product-disbursement-holdings")
    public EaiWrapper createRdsProductDisbursementHoldings(
        @Valid
        @RequestBody
        EaiWrapper<WdecRdsProductDisbursementHoldingDto.SaveReq> reqWrapper
    ) {
        //Response용 EaiWrapper 생성
        EaiWrapper<WdecRdsProductDisbursementHoldingDto.SaveRes> resWrapper = reqWrapper.newResInstance();

        //return 파라미터
        String[] returnValue = service.createRdsProductDisbursementHoldings(reqWrapper.getBody());

        // 서비스 메소드 호출 및 Response Body 세팅
        resWrapper.setBody(
            WdecRdsProductDisbursementHoldingDto.SaveRes.builder()
                .rdsDsbDuedt(returnValue[0]) //RDS지급예정일자
                .rsCd(returnValue[1]) //결과코드
                .rsMsg(returnValue[2]) //결과메시지
                .build()
        );

        return resWrapper;
    }

}
