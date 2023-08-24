package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageAgrgDto.SearchReq;

@Mapper
public interface WsnaFilterOutOfStorageAgrgMapper {

    List<HashMap<String, String>> selectFilterOutOfStorageAgrgs(SearchReq dto);
}
