package com.kyowon.sms.wells.web.competence.business.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfBusinessCardMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfPartnerContactBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfPartnerCustomerContactBaseDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WpsfBusinessCarMgtConverter {

    WpsfPartnerCustomerContactBaseDvo mapSaveReq(WpsfBusinessCardMgtDto.EditReq dto);

    WpsfPartnerContactBaseDvo mapEditPartnerReq(WpsfBusinessCardMgtDto.EditPartnerReq dto);

    PagingResult<WpsfBusinessCardMgtDto.SearchRes> mapWpsfPartnerCustomerContactBaseDvoToSearchRes(
        PagingResult<WpsfPartnerCustomerContactBaseDvo> dvos
    );

    List<WpsfBusinessCardMgtDto.SearchRes> mapWpsfPartnerCustomerContactBaseDvoListToSearchRes(
        List<WpsfPartnerCustomerContactBaseDvo> dvos
    );

    WpsfBusinessCardMgtDto.SearchPartnerRes mapWWpsfPartnerContactBaseDvoToSearchPreviewRes(
        WpsfPartnerContactBaseDvo dvo
    );
}
