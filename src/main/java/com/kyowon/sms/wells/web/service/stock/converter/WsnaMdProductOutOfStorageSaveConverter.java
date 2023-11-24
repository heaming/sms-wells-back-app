package com.kyowon.sms.wells.web.service.stock.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSaveDvo;

@Mapper(componentModel = "spring")
public interface WsnaMdProductOutOfStorageSaveConverter {

    List<WsnaMdProdcutOutOfStorageSaveDvo> mapSaveReqToMdProductOutOfStorageSaveDvo(List<SaveReq> dtos);

    List<WsnaMdProdcutOutOfStorageSaveDvo> mapRemoveReqToMdProductOutOfStorageSaveDvo(List<RemoveReq> dtos);
}
