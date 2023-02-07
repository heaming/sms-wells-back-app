package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaPdctClsfDvRcpQtyInqrDto.SearchReq;

@Mapper
public interface WctaPdctClsfDvRcpQtyInqrMapper {
    int selectContractCount(SearchReq req);
}
