package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaDisuseOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaDisuseOutOfStorageAgrgDto.SearchRes;

@Mapper
public interface WsnaDisuseOutOfStorageAgrgMapper {

    List<SearchRes> selectDisuseOutOfStorageAgrgs(SearchReq dto);
}
