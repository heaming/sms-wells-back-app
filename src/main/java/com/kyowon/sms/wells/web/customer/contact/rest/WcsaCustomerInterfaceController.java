package com.kyowon.sms.wells.web.customer.contact.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto;
import com.kyowon.sms.wells.web.customer.contact.service.WcsaCustomerInterfaceService;
import com.kyowon.sms.wells.web.customer.zcommon.constants.CstCommonConstant;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = "[WCSA] 고객센터 WELLS 고객정보 조회")
@RequestMapping(CstCommonConstant.INTERFACE_URL_V2 + "/customers")
@RequiredArgsConstructor
@Validated
public class WcsaCustomerInterfaceController {
    private final WcsaCustomerInterfaceService wcsaCustomerInterfaceService;

    @ApiOperation(value = "고객번호 기준으로 고객정보를 조회(IF ID:EAI_WCUI1011)", notes = "고객번호에 해당하는 고객 기본/상세 정보 조회")
    @PostMapping("/")
    public EaiWrapper getCustomerByCstNo(
        @Valid
        @RequestBody
        EaiWrapper<WcsaCustomerInterfaceDto.SearchCustomerInfoReq> reqWrapper
    ) {
        EaiWrapper<WcsaCustomerInterfaceDto.SearchCustomerRes> resWrapper = reqWrapper.newResInstance();

        resWrapper.setBody(wcsaCustomerInterfaceService.getCustomerByCstNo(reqWrapper.getBody()));

        return resWrapper;
    }

}
