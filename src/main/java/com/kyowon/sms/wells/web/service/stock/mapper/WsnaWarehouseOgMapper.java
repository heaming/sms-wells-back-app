package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.CountReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOgDvo;
import org.apache.ibatis.annotations.Mapper;
import org.w3c.dom.css.Counter;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.*;

@Mapper
public interface WsnaWarehouseOgMapper {
    List<SearchRes> selectWarehouseOgs(SearchReq dto);

    int selectWareCarriedCounter(CountReq dto);

    int insertWareCarried(CreateReq dto);
}
