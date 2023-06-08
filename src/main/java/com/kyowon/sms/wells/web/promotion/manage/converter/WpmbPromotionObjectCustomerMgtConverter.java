package com.kyowon.sms.wells.web.promotion.manage.converter;

import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.ContractRes;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.promotion.manage.dvo.WpmbPromotionObjectCustomerDvo;

@Mapper(componentModel = "spring")
public interface WpmbPromotionObjectCustomerMgtConverter {

    WpmbPromotionObjectCustomerDvo mapSaveReqToWpmbPromotionObjectCustomerDvo(SaveReq dto);

    ContractRes mapWpmbPromotionObjectCustomerDvoToContractRes(WpmbPromotionObjectCustomerDvo selectObjectCustomerContractInfo);

    WpmbPromotionObjectCustomerDvo mapRemoveReqToWpmbPromotionObjectCustomerDvo(WpmbPromotionObjectCustomerMgtDto.RemoveReq dto);
}
