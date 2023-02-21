package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.RemoveConfirmApprovalBaseReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.RemoveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.SaveConfirmApprovalBaseReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprBaseBasDvo;

@Mapper(componentModel = "spring")
public interface WctaContractConverter {
    WctaCntrAprAkDvCdDvo mapRemoveReqToWctaCntrAprAkDvCdDvo(RemoveReq dto);

    WctaCntrAprBaseBasDvo mapSaveReqpToWctaCntrAprBaseBasDvo(SaveConfirmApprovalBaseReq dto);

    WctaCntrAprBaseBasDvo mapRemoveReqpToWctaCntrAprBaseBasDvo(RemoveConfirmApprovalBaseReq dto);
}
