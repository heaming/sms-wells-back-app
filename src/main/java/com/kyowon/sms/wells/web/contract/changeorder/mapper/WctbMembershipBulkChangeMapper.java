package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto.SearchRes;

@Mapper
public interface WctbMembershipBulkChangeMapper {
    List<SearchRes> selectMembershipBulkChanges(String cntrNo, String cntrSn, String rfdt);
}
