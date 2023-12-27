package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCustomerCenterInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + "고객센터 인터페이스")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/customer-centers")
@RequiredArgsConstructor
@Validated
public class WsniCustomerCenterInterfaceController {

    private final WsniCustomerCenterInterfaceService service;

    @ApiOperation(value = "EAI_WSVI1047 W-SV-I-0043 엔지니어 컨택 현황 조회", notes = "고객센터에서 엔지니어의 컨택 현황을 조회하는 인터페이스")
    @PostMapping("/enginner-contact")
    public EaiWrapper getEngineerContactPs(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchContactRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerContactPs(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "EAI_WSVI1048 W-SV-I-0044 엔지니어 약속변경 이력 조회", notes = "고객센터에서 엔지니어의 약속변경 이력 현황을 조회하는 인터페이스")
    @PostMapping("/engineer-promise-change")
    public EaiWrapper getEngineerPromChHist(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchPromChRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerPromChHist(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "엔지니어 취소 목록 조회", notes = "고객센터에서 설치 및 A/S배정된 대상 중 엔지니어 취소 목록을 조회하는 인터페이스")
    @PostMapping("/engineer-cancel")
    public EaiWrapper getEngineerCancels(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchCancelRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getEngineerCancels(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WSVI1049] 모종 정기배송 제품 조회", notes = "고객센터에서 모종 정기배송 제품을 조회하는 인터페이스")
    @PostMapping("/seeding-product")
    public EaiWrapper getSeedingRegularShippingPdct(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchSppPdctRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getSeedingRegularShippingPdct(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "모종 정기배송 방문정보 조회", notes = "고객센터에서 모종 정기배송 방문정보를 조회하는 인터페이스")
    @PostMapping("/seeding-visit")
    public EaiWrapper getSeedingRegularShippingVst(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchSppVstRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getSeedingRegularShippingVst(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "EAI_WSVI1044 W-SV-I-0040 AS 업체정보 조회", notes = "고객센터에서 Wells AS 업체정보를 조회하는 인터페이스")
    @PostMapping("/as-business")
    public EaiWrapper getAfterServiceBusinessInf(
        @RequestBody
        @Valid
        EaiWrapper<SearchReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchAsRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getAfterServiceBusinessInf(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "EAI_WSVI1042 W-SV-I-0038 필터 배송지 등록", notes = "고객센터에서 고객에게 배송할 필터 배송지 정보를 등록하는 인터페이스")
    @PostMapping("/filter-shpadr-rgst")
    public EaiWrapper<CreateShpadrRes> createFilterShippingAddress(
        @Valid
        @RequestBody
        EaiWrapper<CreateShpadrReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<CreateShpadrRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.createFilterShippingAddress(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "EAI_WSVI1058 W-SV-I-0038 필터 배송지 수정", notes = "고객센터에서 고객에게 배송할 필터 배송지 정보를 수정하는 인터페이스")
    @PostMapping("/filter-shpadr-mdfc")
    public EaiWrapper<EditShpadrRes> editFilterShippingAddress(
        @Valid
        @RequestBody
        EaiWrapper<EditShpadrReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<EditShpadrRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.editFilterShippingAddress(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "부가 정보 조회", notes = "고객센터에서 설치 장소 상세위치, 서비스 부가정보 등 부가 정보를 조회한다.")
    @PostMapping("/additionals")
    public EaiWrapper getAdditional(
        @Valid
        @RequestBody
        EaiWrapper<FindAdnInfReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<FindAdnInfRes> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getAdditional(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "정기배송 패키지 변경 이력 조회", notes = "고객센터에서 정기배송 패키지 변경 이력을 조회하는 인터페이스")
    @PostMapping("/package-change")
    public EaiWrapper getPackageChangeHistory(
        @RequestBody
        @Valid
        EaiWrapper<FindAdnInfReq> reqWrapper
    ) throws IOException, Exception {
        EaiWrapper<List<SearchPkgChRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getPackageChangeHistory(reqWrapper.getBody()));

        return resWrapper;
    }

    @ApiOperation(value = "필터 배송지 조회", notes = "고객센터에서 고객에게 배송할 필터 배송지 정보를 조회하는 인터페이스")
    @PostMapping("/filter-shpadr-inqr")
    public EaiWrapper getFilterShippingAddress(
        @RequestBody
        @Valid
        EaiWrapper<SearchFiltShpadrReq> reqWrapper
    ) {
        EaiWrapper<List<SearchFiltShpadrRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getFilterShippingAddress(reqWrapper.getBody()));

        return resWrapper;
    }
}
