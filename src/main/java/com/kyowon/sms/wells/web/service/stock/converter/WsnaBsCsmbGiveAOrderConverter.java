package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.CreatReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbGiveAOrderDvo;

@Mapper(componentModel = "spring")
public interface WsnaBsCsmbGiveAOrderConverter {
    List<SearchRes> listDvoToBsCsmbGiveAOrderDto(List<WsnaBsCsmbGiveAOrderDvo> dvos);

    List<WsnaBsCsmbGiveAOrderDvo> listCreatReqToBsCsmbGiveAOrderDvos(List<CreatReq> dtos);

}
