package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.FindItemRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAgrgWareDvo;

@Mapper
public interface WsnaOutOfStorageAgrgMapper {

    List<FindItemRes> selectItemProductCodes(SearchReq dto);

    List<WsnaOutOfStorageAgrgWareDvo> selectMcByWares(WsnaOutOfStorageAgrgWareDvo dvo);

    List<HashMap<String, String>> selectOutOfStorageAgrgs(WsnaOutOfStorageAgrgDvo dvo);
}
