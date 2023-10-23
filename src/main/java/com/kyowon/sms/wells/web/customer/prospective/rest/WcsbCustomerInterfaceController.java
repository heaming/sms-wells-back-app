package com.kyowon.sms.wells.web.customer.prospective.rest;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbCustomerInterfaceDto.CreateNewReceiptInquiryReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbCustomerInterfaceDto.CreateNewReceiptInquiryRes;
import com.kyowon.sms.wells.web.customer.prospective.service.WcsbProspectCustomerService;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CstCommonConstant;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 웰스홈페이지 신규접수 I/F Controller
 * </pre>
 *
 * @author jeongeon.kim
 * @since 2023-06-26
 */
@InterfaceController
@Api(tags = "[WCSB] 웰스홈페이지 신규접수")
@RequestMapping(CstCommonConstant.INTERFACE_URL_V1 + "/customer/prospect-customers")
@RequiredArgsConstructor
@Validated
@Transactional
public class WcsbCustomerInterfaceController {
    private final WcsbProspectCustomerService service;

    /**
    * (wells홈페이지, 고객센터) 신규접수 외부인터페이스 처리
    * @param reqWrapper 진단검사 쿠폰 완료처리 I/F 수신정보(주요 PARMA: 진단쿠폰번호, 진단검사일자, 관계자한글명, 관리파트너번호 등)
    * @return 신규접수 (I/F) 처리 건수
    */
    @ApiOperation(value = "(wells홈페이지, 고객센터) 신규접수 외부인터페이스 처리", notes = "고객번호에 해당하는 고객 기본/상세 정보 조회")
    @PostMapping
    public EaiWrapper createNewReceiptInquiry(
        @Valid
        @RequestBody
        EaiWrapper<CreateNewReceiptInquiryReq> reqWrapper
    ) throws Exception {
        //W-CU-I-0003 (EAI_WCUI1007)
        EaiWrapper<CreateNewReceiptInquiryRes> resWrapper = reqWrapper.newResInstance();
        try {
            resWrapper.setBody(service.createNewReceiptInquiry(reqWrapper.getBody()));
        } catch (Exception e) {
            resWrapper.setBody(new CreateNewReceiptInquiryRes("N", null, "문의 접수 중 오류가 발생했습니다.", null));
        }

        return resWrapper;
    }

}
