package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradeDto.SaveReq;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradeDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaAsMaterialItemGradeConverter {

    WsnaAsMaterialItemGradeDvo mapCreateReqToWsnaAsMaterialItemGradeDvo(CreateReq dto);

    @Mapping(source = "mngtYm", target = "baseYm")
    WsnaAsMaterialItemGradeDvo mapSaveReqToWsnaAsMaterialItemGradeDvo(SaveReq dto);

    @Mapping(source = "mngtYm", target = "baseYm")
    List<WsnaAsMaterialItemGradeDvo> mapAllSaveReqToWsnaAsMaterialItemGradeDvo(List<SaveReq> dtos);

}
