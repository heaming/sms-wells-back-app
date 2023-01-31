package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationHpcallFwDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallationHpcallDvo;

/**
 * <pre>
 * W-SV-S-0080 설치, A/S 해피콜 발송(리뉴얼)
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.01.26
 */
@Mapper(componentModel = "spring")
public interface WsnbInstallationHpcallFwConverter {

    SearchReq mapWsnbInstallationHpcallDvoToSearchReq(WsnbInstallationHpcallDvo dvo);

}
