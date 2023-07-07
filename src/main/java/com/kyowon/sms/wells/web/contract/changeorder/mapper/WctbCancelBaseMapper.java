package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctbCancelBaseMapper {

    List<SearchRes> selectCancelBases(SearchReq dto);
}
