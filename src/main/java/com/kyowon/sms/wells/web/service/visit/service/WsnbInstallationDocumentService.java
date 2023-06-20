package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationDocumentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbInstallationDocumentService {
    private final WsnbInstallationDocumentMapper mapper;

    public String getConfirmationDocument(String cntrNo, String cntrSn) {
        return mapper.selectConfirmationDocument(cntrNo, cntrSn);
    }
}
