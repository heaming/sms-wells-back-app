package com.kyowon.sms.wells.web.customer.contact.rest;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.CreateCustomerForNaverRentalReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.CreateCustomerForNaverRentalRes;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SaveCustomerAgreementReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SaveCustomerAgreementRes;
import com.kyowon.sms.wells.web.customer.contact.service.WcsaCustomerInterfaceService;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CstCommonConstant;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 고객 인터페이스 관리 - wells Controller
 * </pre>
 *
 * @author Jaeyeol.Lee
 * @since 2023-02-01
 */

@InterfaceController
@Api(tags = "[WCSA] 고객 인터페이스 관리")
@RequestMapping(CstCommonConstant.INTERFACE_URL_V1 + "/customers")
@RequiredArgsConstructor
@Validated
@Transactional
public class WcsaCustomerInterfaceController {
    private final WcsaCustomerInterfaceService wcsaCustomerInterfaceService;

    /**
    * 고객번호 기준으로 고객정보를 조회 - 고객번호에 해당하는 고객 기본/상세 정보 조회
    * @param reqWrapper 고객정보 조회 조건 (주요 PARAM: 고객번호 )
    * @return 고객 정보
    */
    @ApiOperation(value = "고객번호 기준으로 고객정보를 조회(IF ID:EAI_WCUI1011)", notes = "고객번호에 해당하는 고객 기본/상세 정보 조회")
    @PostMapping
    public EaiWrapper getCustomerByCstNo(
        @Valid
        @RequestBody
        EaiWrapper<WcsaCustomerInterfaceDto.SearchCustomerInfoReq> reqWrapper
    ) {
        EaiWrapper<WcsaCustomerInterfaceDto.SearchCustomerRes> resWrapper = reqWrapper.newResInstance();

        resWrapper.setBody(wcsaCustomerInterfaceService.getCustomerByCstNo(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "고객센터 Wells 계약고객 정보 변경 처리 서비스. 연관 I/F : EAI_WCUI1017", notes = "고객번호에 해당하는 고객 기본/상세 변경")
    @PostMapping("/contract-customers")
    public EaiWrapper editCustomerByCc(
        @Valid
        @RequestBody
        EaiWrapper<WcsaCustomerInterfaceDto.SearchCustomerInfoEditReq> reqWrapper
    ) {
        EaiWrapper<WcsaCustomerInterfaceDto.SearchCustomerInfoEditRes> resWrapper = reqWrapper.newResInstance();

        resWrapper.setBody(wcsaCustomerInterfaceService.editCustomerByCc(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "wells 사업본부 동의정보 등록/변경(IF ID:EAI_WCUI1009)", notes = "wells 사업본부 채널별 고객 동의정보 수신 데이터를 사업본부별 고객동의정보 반영한다.")
    @PostMapping("/agree-provisions")
    public EaiWrapper<SaveCustomerAgreementRes> saveCustomerAgreements(
        @Valid
        @RequestBody
        EaiWrapper<SaveCustomerAgreementReq> reqWrapper
    ) {
        EaiWrapper<SaveCustomerAgreementRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(wcsaCustomerInterfaceService.saveCustomerAgreements(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "(WELLS) 네이버렌탈 고객등록 I/F", notes = "Wells 미인증 고객 등록")
    @PostMapping("/naverrental-customers")
    public EaiWrapper createCustomerForNaverRental(
        @Valid
        @RequestBody
        EaiWrapper<CreateCustomerForNaverRentalReq> reqWrapper
    ) throws Exception {
        EaiWrapper<CreateCustomerForNaverRentalRes> resWrapper = reqWrapper.newResInstance();

        resWrapper.setBody(wcsaCustomerInterfaceService.createCustomerForNaverRental(reqWrapper.getBody()));

        return resWrapper;
    }
}
