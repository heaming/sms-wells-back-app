package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCenterLocalAreaTfDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCenterLocalAreaTfDvo;

@Mapper
public interface WsncCenterLocalAreaTfMapper {

    List<WsncCenterLocalAreaTfDto.SearchRes> selectCenterAreas(
        WsncCenterLocalAreaTfDto.SearchReq dto
    );

    int insertCenterArea(WsncCenterLocalAreaTfDvo dvo);
}
