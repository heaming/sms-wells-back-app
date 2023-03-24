package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbVirtualAccountInterfaceMapper {

    List<SearchRes> selectVirtualAccountIssues(SearchReq dto);
}
