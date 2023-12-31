package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.*;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnCreateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnIndividualSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnRemoveDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnWareRenewalDvo;

@Mapper(componentModel = "spring", imports = {org.apache.commons.lang3.StringUtils.class})
public interface WsnaQomAsnConverter {

    @Mapping(target = "telNo", expression = "java(StringUtils.defaultString(dvo.getLocaraTno()) + StringUtils.defaultString(dvo.getExnoEncr()) + StringUtils.defaultString(dvo.getIdvTno()))")
    SearchRes mapWsnaQomAsnIndividualSearchDvoToSearchRes(WsnaQomAsnIndividualSearchDvo dvo);

    List<SearchRes> mapAllWsnaQomAsnIndividualSearchDvoToSearchRes(List<WsnaQomAsnIndividualSearchDvo> dvos);

    WsnaQomAsnWareRenewalDvo mapEditReqToWsnaQomAsnWareRenewalDvo(EditReq dto);

    WsnaQomAsnCreateDvo mapCreateReqToWsnaQomAsnCreateDvo(CreateReq dto);

    WsnaQomAsnRemoveDvo mapRemoveReqToWsnaQomAsnRemoveDvo(RemoveReq dto);

    WsnaQomAsnRemoveDvo mapCreateReqToWsnaQomAsnRemoveDvo(CreateReq dto);

}
