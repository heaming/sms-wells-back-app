package com.kyowon.sms.wells.web.closing.performance.converter;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccContractDvo;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesPerformDvo;
import org.mapstruct.Mapper;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.SearchContractReq;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WdccSalesPerformConverter {
    List<SearchRes> mapAllWdccSalesPerformDvoToSearchRes(
        List<WdccSalesPerformDvo> dvos
    );

    SearchContractReq mapWdccContractDvoToSearchContractReq(WdccContractDvo dvo);
}
