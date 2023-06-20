package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationDocumentService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 고객 설치확인서 조회")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-documents")
public class WsnbInstallationDocumentController {
    private final WsnbInstallationDocumentService service;

    @GetMapping("/{cntrNo}-{cntrSn}")
    public String getConfirmationDocument(
        @PathVariable
        String cntrNo,
        @PathVariable
        String cntrSn
    ) {
        return service.getConfirmationDocument(cntrNo, cntrSn);
    }
}
