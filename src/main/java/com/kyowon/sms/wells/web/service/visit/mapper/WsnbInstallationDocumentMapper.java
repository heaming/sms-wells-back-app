package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnbInstallationDocumentMapper {
    String selectConfirmationDocument(String cntrNo, String cntrSn);
}
