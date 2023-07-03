package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.EditReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedReleaseScheduleSearchDvo;

@Mapper(componentModel = "spring")
public interface WsnaSeedReleaseScheduleConverter {

    @Mapping(target = "ichrCtnt", expression = "java(dvo.getIchrCralLocaraTno() + dvo.getIchrMexnoEncr() + dvo.getIchrCralIdvTno())")
    @Mapping(target = "cstCtnt", expression = "java(dvo.getCstCralLocaraTno() + dvo.getCstMexnoEncr() + dvo.getCstCralIdvTno())")
    SearchRes mapWsnaSeedReleaseScheduleSearchDvoToSearchRes(WsnaSeedReleaseScheduleSearchDvo dvo);

    List<SearchRes> mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(List<WsnaSeedReleaseScheduleSearchDvo> dvos);

    WsnaSeedReleaseScheduleDvo mapEditReqToWsnaSeedReleaseScheduleDvo(EditReq dtos);

}
