package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactListsDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactListsDto.SearchRes;

@Mapper
public interface WctiContractContactListsMapper {
    List<SearchRes> selectContractContactPs(SearchReq dto);
}
