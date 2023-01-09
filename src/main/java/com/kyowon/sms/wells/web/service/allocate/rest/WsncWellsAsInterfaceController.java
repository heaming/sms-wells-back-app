package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsInterfaceDto.*;
import com.kyowon.sms.wells.web.service.allocate.service.WsncWellsAsInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@InterfaceController
//@RestController
@RequestMapping(SnServiceConst.INTERFACE_URL_V1 + "/wells-as-interfaces")
@Api(tags = "[WSNC] Wells 인터페이스 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncWellsAsInterfaceController {

    private final WsncWellsAsInterfaceService service;

    @ApiOperation(value = "고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @PostMapping("/customer-informations")
    public EaiWrapper getCustomerInformations(
        @Valid
        @RequestBody
        EaiWrapper<SearchCustInfoReq> reqWrapper
    ) {
        EaiWrapper<List<SearchCustInfoRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getCustomerInformations(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "고객서비스AS설치대상내역, 고객서비스수행배정내역, 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @PostMapping("/receipt-Informations")
    public EaiWrapper getReceiptInformations(
        @Valid
        @RequestBody
        EaiWrapper<SearchRecInfoReq> reqWrapper
    ) {
        EaiWrapper<List<SearchRecInfoRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getReceiptInformations(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "Wells 인터페이스 맞춤가이드 사용중인 제품 조회")
    @PostMapping("/using-products")
    public EaiWrapper getUsingProducts(
        @Valid
        @RequestBody
        EaiWrapper<SearchUsingProductsReq> reqWrapper
    ) {
        EaiWrapper<List<SearchUsingProductsRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getUsingProducts(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "Wells 인터페이스 맞춤가이드 서비스 이력 조회")
    @PostMapping("/service-historys/paging")
    public EaiWrapper getServiceHistoryPages(
        @Valid
        @RequestBody
        EaiWrapper<SearchServiceHistoryReq> reqWrapper
    ) {
        return null;
        //return service.getServiceHistoryPages(req);
    }

}
