package com.kyowon.sms.wells.web.promotion.manage.converter;

import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.ContractRes;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.promotion.manage.dvo.WpmbPromotionObjectCustomerMgtDvo;

@Mapper(componentModel = "spring")
public interface WpmbPromotionObjectCustomerMgtConverter {

    WpmbPromotionObjectCustomerMgtDvo mapSaveReqToWpmbPromotionObjectCustomerMgtDvo(SaveReq dto);

    ContractRes mapWpmbPromotionObjectCustomerMgtDvoToContractRes(WpmbPromotionObjectCustomerMgtDvo selectObjectCustomerContractInfo);

    WpmbPromotionObjectCustomerMgtDvo mapRemoveReqToWpmbPromotionObjectCustomerMgtDvo(WpmbPromotionObjectCustomerMgtDto.RemoveReq dto);
}
