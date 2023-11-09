package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnItemDto.FindDetailRes;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnItemDto.FindRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaQomAsnItemMapper {

    FindRes selectQomAsnItemMasterInfo(String itmOstrNo);

    List<FindDetailRes> selectQomAsnItems(String itmOstrNo);

}
