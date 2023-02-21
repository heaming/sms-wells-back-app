package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaMembershipContractSnDto.SearchReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaMembershipContractSnDto.SearchRes;

@Mapper
public interface WctaMembershipContractSnMapper {
    List<SearchRes> selectMembershipContractSn(SearchReq dto);
}
