package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WcteAlreadyReceivedMphDto.SearchRes;

@Mapper
public interface WcteAlreadyReceivedMphMapper {
    List<SearchRes> selectAlreadyReceivedMph(String cntrNo, String cntCstNo);
}
