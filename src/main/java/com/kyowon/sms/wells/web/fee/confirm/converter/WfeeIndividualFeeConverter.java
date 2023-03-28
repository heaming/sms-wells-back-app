package com.kyowon.sms.wells.web.fee.confirm.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;
import com.kyowon.sms.wells.web.fee.confirm.dvo.WfeeIndividualFeeDvo;

@Mapper(componentModel = "spring")
public interface WfeeIndividualFeeConverter {
    WfeeIndividualFeeDvo mapSearchFeeReqToWfeeIndividualFeeDvo(SearchFeeReq dto);

    WfeeIndividualFeeDvo mapSearchFeeHmstReqToWfeeIndividualFeeDvo(SearchFeeHmstReq dto);

}
