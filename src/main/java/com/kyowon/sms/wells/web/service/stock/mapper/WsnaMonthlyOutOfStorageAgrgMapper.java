package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyOutOfStorageAgrgDto.FindWarehouseRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyOutOfStorageAgrgDvo;

@Mapper
public interface WsnaMonthlyOutOfStorageAgrgMapper {

    List<FindWarehouseRes> selectWareHouses(SearchReq dto);

    List<String> selectMonthlys(WsnaMonthlyOutOfStorageAgrgDvo dvo);

    List<HashMap<String, String>> selectMonthlyOutOfStorageAgrgs(WsnaMonthlyOutOfStorageAgrgDvo dvo);
}
