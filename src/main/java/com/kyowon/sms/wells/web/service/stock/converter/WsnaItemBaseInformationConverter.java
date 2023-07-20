package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationSearchDvo;
import org.mapstruct.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

@Mapper(componentModel = "spring")
public interface WsnaItemBaseInformationConverter {

    WsnaItemBaseInformationSearchDvo mapSearchReqToWsnaItemBaseInformationSearchDvo(SearchReq dto);

}
