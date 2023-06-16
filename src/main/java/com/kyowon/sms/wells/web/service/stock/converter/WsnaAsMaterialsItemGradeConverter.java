package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialsItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialsItemGradeDvo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaAsMaterialsItemGradeConverter {

    WsnaAsMaterialsItemGradeDvo mapCreateReqToWsnaAsMaterialsItemGradeDvo(WsnaAsMaterialsItemGradeDto.CreateReq dto);

    @Mapping(source = "mngtYm", target = "baseYm")
    WsnaAsMaterialsItemGradeDvo mapSaveReqToWsnaAsMaterialsItemGradeDvo(
        WsnaAsMaterialsItemGradeDto.SaveReq dto
    );

    @Mapping(source = "mngtYm", target = "baseYm")
    List<WsnaAsMaterialsItemGradeDvo> mapAllSaveReqToWsnaAsMaterialsItemGradeDvo(
        List<WsnaAsMaterialsItemGradeDto.SaveReq> dtos
    );

}
