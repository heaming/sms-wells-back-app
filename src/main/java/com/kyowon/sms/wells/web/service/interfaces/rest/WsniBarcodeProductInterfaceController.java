package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarcodeProductInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniBarcodeProductInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/barcode-products")
@RequiredArgsConstructor
@Validated
public class WsniBarcodeProductInterfaceController {

    private final WsniBarcodeProductInterfaceService service;

    @ApiOperation(value = "W-SV-I-0011 바코드를 입력받아 등록된 바코드인지 확인 API")
    @PostMapping
    public EaiWrapper getBarcodeProducts(
        @Valid
        @RequestBody
        EaiWrapper<WsniBarcodeProductInterfaceDto.SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<WsniBarcodeProductInterfaceDto.SearchJsonRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getBarcodeProducts(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "W-SV-I-0012 바코드로 렌탈고객정보를 검색")
    @PostMapping("/search-customers")
    public EaiWrapper getBarcodeSearchCustomers(
        @Valid
        @RequestBody
        EaiWrapper<WsniBarcodeProductInterfaceDto.SearchCustReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<WsniBarcodeProductInterfaceDto.SearchCustJsonRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getBarcodeSearchCustomers(reqWrapper.getBody()));
        return resWrapper;
    }

}
