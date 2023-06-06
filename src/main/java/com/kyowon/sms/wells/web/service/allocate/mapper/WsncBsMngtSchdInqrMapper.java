package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsMngtSchdInqrDto;

@Mapper
public interface WsncBsMngtSchdInqrMapper {
    List<WsncBsMngtSchdInqrDto.SearchRes> selectBsMngtSchdInqrAgrg(
        WsncBsMngtSchdInqrDto.SearchReq dto
    );

    List<WsncBsMngtSchdInqrDto.SearchRes> selectBsMngtSchdInqrDtl(
        WsncBsMngtSchdInqrDto.SearchReq dto
    );

}
