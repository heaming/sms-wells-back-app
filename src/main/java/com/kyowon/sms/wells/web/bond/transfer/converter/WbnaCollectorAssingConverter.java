package com.kyowon.sms.wells.web.bond.transfer.converter;

import com.kyowon.sms.common.web.bond.transfer.dvo.ZbnaBondTransferAssignDvo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaCollectorAssignDto.EditReq;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaCollectorAssignDvo;

@Mapper(componentModel = "spring")
public interface WbnaCollectorAssingConverter {

    @Mapping(target = "cntrNo", ignore = true)
    @Mapping(target = "cntrSn", ignore = true)
    @Mapping(target = "prtnrNo", ignore = true)
    @Mapping(target = "bndCntrId", ignore = true)
    WbnaCollectorAssignDvo mapSearchReqToWbnaCollectorAssignDvo(SearchReq dto);

    // TODO DVO 맵핑 정리 필요 현재는 기준이 정해지지 않아서 함께 사용(같이 사용해도 문제 없을거 같으면 해당 주석 삭제)
    @Mapping(source = "clctamPrtnrNo", target = "prtnrNo")
    @Mapping(target = "clctamDvCd", ignore = true)
    @Mapping(target = "bndNwDvCd", ignore = true)
    WbnaCollectorAssignDvo mapEditReqToWbnaCollectorAssignDvo(EditReq dto);

    @Mapping(target = "tfBizDvCd", ignore = true)
    @Mapping(target = "excnSn", ignore = true)
    @Mapping(target = "bndAsnMthCd", ignore = true)
    ZbnaBondTransferAssignDvo mapSearchReqToZbnaBondTransferAssignDvo(SearchReq dto);
}
