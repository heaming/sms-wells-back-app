package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductReturningGoodsSaveDvo;

@Mapper(componentModel = "spring")
public interface WsnaMdProductReturningGoodsMgtConverter {

    List<WsnaMdProductReturningGoodsSaveDvo> mapSaveReqToMdProductReturningGoodsDvo(List<SaveReq> dto);

}
