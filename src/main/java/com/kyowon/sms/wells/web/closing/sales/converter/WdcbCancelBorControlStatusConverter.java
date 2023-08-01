package com.kyowon.sms.wells.web.closing.sales.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.common.web.bond.consultation.dvo.ZbncBondMessageRelayDvo;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchRes;

@Mapper(componentModel = "spring")
public interface WdcbCancelBorControlStatusConverter {
    List<SearchRes> ListBondMessageRelayDvoToSearchRes(List<ZbncBondMessageRelayDvo> dvos);

}
