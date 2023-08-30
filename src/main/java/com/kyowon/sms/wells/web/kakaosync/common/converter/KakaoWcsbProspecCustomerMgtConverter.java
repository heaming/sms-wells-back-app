package com.kyowon.sms.wells.web.kakaosync.common.converter;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspecCustomerDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbTbSsopPspcCstDdlvHistDvo;
import com.kyowon.sms.wells.web.kakaosync.common.dto.KakaoWcsbProspecCustomerMgtDto;
import com.kyowon.sms.wells.web.kakaosync.common.dvo.KakaoWcsbSyncDefaultDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KakaoWcsbProspecCustomerMgtConverter {

    //    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WcsbProspecCustomerDvo mapSaveReqToWcsbProspecCustomerDvo(WcsbProspecCustomerMgtDto.TbSsopPspcCstBas dto);

    KakaoWcsbSyncDefaultDvo mapSaveReqToKakaoWcsbSyncDefaultDvo(
        KakaoWcsbProspecCustomerMgtDto.SaveReq dto
    );

    // 교사정보
    WcsbTbSsopPspcCstDdlvHistDvo mapSaveReqToWcsbTbSsopPspcCstDdlvHistDvo(
        WcsbProspecCustomerMgtDto.TbSsopPspcCstDdlvHist dto
    );
}
