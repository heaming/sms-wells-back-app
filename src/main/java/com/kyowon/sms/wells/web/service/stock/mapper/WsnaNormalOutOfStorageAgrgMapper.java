package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.FindWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageAgrgDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgWareDvo;

@Mapper
public interface WsnaNormalOutOfStorageAgrgMapper {

    List<FindWarehouseRes> selectLoginWarehouses(SearchReq dto);

    List<WsnaOutOfStorageAgrgWareDvo> selectWareHouses(SearchReq dto);

    List<HashMap<String, String>> selectNormalOutOfStorageAgrgs(WsnaNormalOutOfStorageAgrgDvo dto);

}
