package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAutoMaterialReqDvo;

@Mapper
public interface WsnaAutoMaterialReqMapper {
    int insertOteamMatAutoAplcIz(WsnaAutoMaterialReqDvo dvo);

    int updateItmOstrAkIz(WsnaAutoMaterialReqDvo dvo);
}
