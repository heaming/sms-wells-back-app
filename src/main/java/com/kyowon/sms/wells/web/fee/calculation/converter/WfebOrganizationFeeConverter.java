package com.kyowon.sms.wells.web.fee.calculation.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOrganizationFeeDto;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebOrganizationFeeDvo;

@Mapper(componentModel = "spring")
public interface WfebOrganizationFeeConverter {

    WfebOrganizationFeeDvo mapSaveReqToWfebOgFeeDvo(WfebOrganizationFeeDto.SaveReq dto);

    WfebOrganizationFeeDvo mapSaveDsbCnstReqToWfebOgFeeDvo(WfebOrganizationFeeDto.SaveDsbCnstReq dto);

    WfebOrganizationFeeDvo mapSearchDsbCnstReqToWfebOgFeeDvo(WfebOrganizationFeeDto.SearchDsbCnstReq dto);

}
