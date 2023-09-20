package com.kyowon.sms.wells.web.kakaosync.common.converter;

import com.kyowon.sms.wells.web.kakaosync.common.dto.KakaoWcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.kakaosync.common.dvo.KakaoWcsbSyncDefaultDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KakaoWcsbProspecCustomerMgtConverter {

    KakaoWcsbSyncDefaultDvo mapSaveReqToKakaoWcsbSyncDefaultDvo(
        KakaoWcsbProspecCustomerMgtDto.SaveReq dto
    );

}
