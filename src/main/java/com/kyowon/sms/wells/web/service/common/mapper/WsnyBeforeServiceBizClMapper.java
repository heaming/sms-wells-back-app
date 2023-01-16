package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyBeforeServiceBizClDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyBeforeServiceBizClDvo;

@Mapper
public interface WsnyBeforeServiceBizClMapper {
    List<WsnyBeforeServiceBizClDto.SearchRes> selectBusinessClosesForBeforeService(
        WsnyBeforeServiceBizClDto.SearchReq dto
    );

    List<WsnyBeforeServiceBizClDto.SearchRes> selectBusinessClosesForEtc(
        WsnyBeforeServiceBizClDto.SearchReq dto
    );

    int saveBusinessClosesForBeforeService(WsnyBeforeServiceBizClDvo dvo);

    int saveBusinessClosesForEtc(WsnyBeforeServiceBizClDvo dvo);
}
