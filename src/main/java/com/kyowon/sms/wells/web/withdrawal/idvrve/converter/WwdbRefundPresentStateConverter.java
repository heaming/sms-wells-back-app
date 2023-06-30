package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundPresentStateDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundPresentStateDvo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WwdbRefundPresentStateConverter {

    PagingResult<WwdbRefundPresentStateDto.SearchRes> WwdbRefundPresentStateDvoToSearchRes(PagingResult<WwdbRefundPresentStateDvo> dvos);

    List<WwdbRefundPresentStateDto.SearchRes> WwdbRefundPresentStateDvoToListSearchRes(List<WwdbRefundPresentStateDvo> dvos);

}
