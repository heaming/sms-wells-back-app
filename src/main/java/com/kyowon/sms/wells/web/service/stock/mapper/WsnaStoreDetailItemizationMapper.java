package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreDetailItemizationDto.*;

@Mapper
public interface WsnaStoreDetailItemizationMapper {

    List<SearchRes> selectStoreDetailItemizations(SearchReq dto);
}
