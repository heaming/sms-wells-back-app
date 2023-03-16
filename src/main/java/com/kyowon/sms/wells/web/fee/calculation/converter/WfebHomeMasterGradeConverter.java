package com.kyowon.sms.wells.web.fee.calculation.converter;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebHomeMasterGradeDto.*;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebHomeMasterGradeDvo;

@Mapper(componentModel = "spring")
public interface WfebHomeMasterGradeConverter {

    WfebHomeMasterGradeDvo mapSaveReqToWfebHomeMasterGradeDvo(SaveReq dto);

    WfebHomeMasterGradeDvo mapSaveReqToWfebHomeMasterPointDvo(SavePointReq dto);

    WfebHomeMasterGradeDvo mapSaveReqToWfebHomeMasterGradeTransferDvo(SearchReq dto);

    WfebHomeMasterGradeDvo mapRemoveReqToWfebHomeMasterGradeTransferDvo(RemoveReq dto);

}
