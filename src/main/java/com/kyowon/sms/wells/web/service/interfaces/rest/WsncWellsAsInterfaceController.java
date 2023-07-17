package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.service.WsncWellsAsInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1)
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/wells-as-interfaces")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncWellsAsInterfaceController {

    private final WsncWellsAsInterfaceService service;

    @ApiOperation(value = "W-SV-I-0021 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @PostMapping("/customers")
    public EaiWrapper getCustomerInformations(
        @Valid
        @RequestBody
        EaiWrapper<SearchCustInfoReq> reqWrapper
    ) {
        EaiWrapper<List<SearchCustInfoRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getCustomerInformations(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "W-SV-I-0022 고객서비스AS설치대상내역, 고객서비스수행배정내역, 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회")
    @PostMapping("/receipts")
    public EaiWrapper getReceiptInformations(
        @Valid
        @RequestBody
        EaiWrapper<SearchRecInfoReq> reqWrapper
    ) {
        EaiWrapper<List<SearchRecInfoRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getReceiptInformations(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "W-SV-I-0004 Wells 인터페이스 맞춤가이드 사용중인 제품 조회")
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

    @ApiOperation(value = "W-SV-I-0001 Wells 인터페이스 맞춤가이드 서비스 이력 조회")
    @PostMapping("/service-historys/paging")
    public EaiWrapper getServiceHistoryPages(
        @Valid
        @RequestBody
        EaiWrapper<SearchServiceHistoryReq> reqWrapper
    ) {
        EaiWrapper<PagingResult<SearchServiceHistoryRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getServiceHistoryPages(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "W-SV-I-0003 Wells 인터페이스 맞춤가이드 서비스 내용 조회")
    @PostMapping("/service-contents")
    public EaiWrapper getServiceContents(
        @Valid
        @RequestBody
        EaiWrapper<SearchServiceContentsReq> reqWrapper
    ) {
        EaiWrapper<List<SearchServiceContentsRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getServiceContents(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "W-SV-I-0035 고객센터 인터페이스 특이사항 조회")
    @PostMapping("/customer-informations")
    public EaiWrapper getCustomerInformation(
        @Valid
        @RequestBody
        EaiWrapper<SearchCustomerInformationReq> reqWrapper
    ) {
        EaiWrapper<SearchCustomerInformationRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getCustomerInformation(reqWrapper.getBody()));
        return resWrapper;
    }

    @ApiOperation(value = "W-SV-I-0023 Wells 인터페이스 맞춤가이드 관리자페이지 AS접수 모종변경 조회")
    @PostMapping("/as-siding-changes")
    public EaiWrapper getAsSidingChanges(
        @Valid
        @RequestBody
        EaiWrapper<SearchAsSidingChangeReq> reqWrapper
    ) {
        EaiWrapper<SearchAsSidingChangeRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getAsSidingChanges(reqWrapper.getBody()));
        return resWrapper;
    }

}
