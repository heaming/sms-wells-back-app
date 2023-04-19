package com.kyowon.sms.wells.web.bond.transfer.converter;

import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondContractBasicHistReqDvo;
import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondTransferAssignDvo;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondPartTransferDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.CreateReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaBondPartTransferDto.EditReq;

@Mapper(componentModel = "spring")
public interface WbnaBondPartTransferConverter {

    @Mapping(target = "cntrNo", ignore = true)
    @Mapping(target = "cntrSn", ignore = true)
    @Mapping(target = "cstNo", ignore = true)
    WbnaBondPartTransferDvo mapCreateReqToWbnaBondPartTransferDvo(CreateReq dto);

    WbnaBondPartTransferDvo mapEditReqToWbnaBondPartTransferDvo(EditReq dto);

    @Mapping(target = "tfBizDvCd", ignore = true)
    @Mapping(target = "excnSn", ignore = true)
    @Mapping(target = "bndAsnMthCd", ignore = true)
    @Mapping(target = "clctamDvCd", ignore = true)
    ZbnaBondTransferAssignDvo mapCreateReqToZbnaBondTransferAssignDvo(CreateReq dto);

    @Mapping(target = "bndCntrId", ignore = true)
    @Mapping(target = "clctamDvCd", ignore = true)
    @Mapping(target = "bndNwDvCd", ignore = true)
    @Mapping(target = "cstNo", ignore = true)
    @Mapping(target = "cntrNo", ignore = true)
    @Mapping(target = "cntrSn", ignore = true)
    ZbnaBondContractBasicHistReqDvo mapCreateReqToZbnaBondContractBasicHistReqDvo(CreateReq dto);
}
