package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAgencyLocaraFilterGiveAOrderPsDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsnaAgencyLocaraFilterGiveAOrderPsConverter {

    PagingResult<SearchRes> mapWsnaAgencyLocaraFilterGiveAOrderPsDvoToSearchRes(
        List<WsnaAgencyLocaraFilterGiveAOrderPsDvo> dvo
    );

    List<SearchRes> mapWsnaAgencyLocaraFilterGiveAOrderPsExcelDvoToSearchRes(
        List<WsnaAgencyLocaraFilterGiveAOrderPsDvo> dvos
    );
}
