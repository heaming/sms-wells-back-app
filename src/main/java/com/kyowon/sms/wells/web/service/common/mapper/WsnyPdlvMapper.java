package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPdlvDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnyPdlvMapper {
    List<WsnyPdlvDto.SearchRes> selectPlaceOfDeliverys();
}
