package com.kyowon.sms.wells.web.withdrawal.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.service.WwdaAutoTransferInterfaceService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@InterfaceController
@Api(tags = "[Wwda] WELLS 자동이체 I/F API")
@RequestMapping(value = WdWithdrawalConst.INTERFACE_URL_V1 + "/automatic-transfer")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WwdaAutoTransferInterfaceController {

    private final WwdaAutoTransferInterfaceService service;

    @ApiOperation(value = "[EAI_WWDI1007] WELLS 자동이체 출금내역 조회")
    @PostMapping("/payment-withdrawal-itemizations")
    public EaiWrapper getPaymentAndWithdrawalItemizations(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchPaymentAndWithdrawalRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchPaymentAndWithdrawalRes> res = service
            .getPaymentAndWithdrawalItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1008] WELLS 자동이체 변경내역 조회")
    @PostMapping("/change-itemizations")
    public EaiWrapper getChangeItemizations(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchChangeRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchChangeRes> res = service
            .getChangeItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1009] WELLS 자동이체 대상목록 조회")
    @PostMapping("/object-itemizations")
    public EaiWrapper getObjectItemizations(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchObjectReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchObjectRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchObjectRes> res = service
            .getObjectItemizations(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1010] WELLS 자동이체 정보 조회")
    @PostMapping
    public EaiWrapper getInfos(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchRes>> resWrapper = reqWrapper.newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchRes> res = service
            .getInfos(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1014] WELLS 자동이체 구분(개인/법인) 조회")
    @PostMapping("/corporate-personal-divisions")
    public EaiWrapper getCorporatePersonalityDivisions(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchCorporatePersonalityDivisionRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchCorporatePersonalityDivisionRes> res = service
            .getCorporatePersonalityDivisions(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1015] WELLS 자동이체 은행 목록 조회")
    @PostMapping("/financial-institutions")
    public EaiWrapper getFinancialInstitutionCodes(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes> res = service
            .getFinancialInstitutionCodes(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1018] wells 자동이체 묶음 등록 정보 조회")
    @PostMapping("/bundle-infos")
    public EaiWrapper getBundleInfos(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchBundleInfoRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchBundleInfoRes> res = service
            .getBundleInfos(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1020] wells 자동이체 증빙 정보 목록 조회")
    @PostMapping("/evidence-infos")
    public EaiWrapper getEvidenceInfos(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchEvidenceInfoRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchEvidenceInfoRes> res = service
            .getEvidenceInfos(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1019] wells 자동이체 일괄 묶음 등록/해제")
    @PostMapping("/bundles")
    public EaiWrapper saveBundleRegistrationReleases(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SaveReq> reqWrapper
    ) {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes> res = service
            .saveBundleRegistrationReleases(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1019] wells 자동이체 일괄 등록/해제")
    @PostMapping("/bulk")
    public EaiWrapper saveBulkRegistrationReleases(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SaveReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes> res = service
            .saveBulkRegistrationReleases(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1017] wells 자동이체 계좌 실명인증")
    @PostMapping("/real-name-certification")
    public EaiWrapper getRealNameCertification(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchRealNameCertificationReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchRealNameCertificationRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchRealNameCertificationRes> res = service
            .getRealNameCertification(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }

    @ApiOperation(value = "[EAI_WWDI1016] wells 자동이체 카드 유효성 체크")
    @PostMapping("/card-effectiveness-check")
    public EaiWrapper getCardEffectivenessCheck(
        @Valid
        @RequestBody
        EaiWrapper<WwdaAutoTransferInterfaceDto.SearchCardEffectivenessCheckReq> reqWrapper
    ) throws Exception {
        // Response용 EaiWrapper 생성
        EaiWrapper<List<WwdaAutoTransferInterfaceDto.SearchCardEffectivenessCheckRes>> resWrapper = reqWrapper
            .newResInstance();
        // 서비스 메소드 호출
        List<WwdaAutoTransferInterfaceDto.SearchCardEffectivenessCheckRes> res = service
            .getCardEffectivenessCheck(reqWrapper.getBody());

        // Response Body 세팅
        resWrapper.setBody(res);

        return resWrapper;
    }
}
