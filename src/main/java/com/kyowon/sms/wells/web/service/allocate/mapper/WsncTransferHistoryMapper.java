package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTransferHistoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsncTransferHistoryMapper {
    List<WsncTransferHistoryDto.SearchRes> selectTransferHistory(WsncTransferHistoryDto.SearchReq dto);
}
