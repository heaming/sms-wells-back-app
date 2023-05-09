package com.kyowon.sms.wells.web.withdrawal.interfaces.converter;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoEvidenceInfoInterfaceDvo;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferObjectItemizationInterfaceDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdaAutoTransferConverter {

    List<WwdaAutoTransferInterfaceDto.SearchObjectRes> mapWwdaAutoTransferDvoToSearchObjectRes(
        List<WwdaAutoTransferObjectItemizationInterfaceDvo> dvo
    );

    List<WwdaAutoTransferInterfaceDto.SearchRes> mapWwdaAutoTransferDvoToSearchRes(
        List<WwdaAutoTransferInfoInterfaceDvo> dvo
    );

    List<WwdaAutoTransferInterfaceDto.SearchEvidenceInfoRes> mapWwdaAutoTransferDvoToSearcEvidenceInfohRes(
        List<WwdaAutoTransferInfoEvidenceInfoInterfaceDvo> dvo
    );

    List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> mapWwdaAutoTransferDvoToSaveBundleRegistrationReleasesReq(
        List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseReq> dvo
    );

    List<WwdaAutoTransferInterfaceDto.SaveBundleRegistrationReleaseRes> mapSaveBundleRegistrationReleasesResToWwdaAutoTransferDvo(
        List<WwdaAutoTransferInfoBundleRegistrationReleasesInterfaceDvo> dvo
    );
}
