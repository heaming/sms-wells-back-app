package com.kyowon.sms.wells.web.service.stock.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMatAutoAplcCrdovrDvo;

@Mapper
public interface WsnaMatAutoAplcCrdovrMapper {
    int insertMatAutoAplcCrdovr(WsnaMatAutoAplcCrdovrDvo dvo);

    int updateMatAutoAplcCrdovr(WsnaMatAutoAplcCrdovrDvo dvo);
}
