package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryBaseDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryBaseDvo;

@Mapper
public interface WsnaBsCsmbDeliveryBaseMapper {

    String selectDeliveryBaseDuplicateYn(CreateReq dto);

    int insertDeliveryBase(WsnaBsCsmbDeliveryBaseDvo dvo);
}
