package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchRes;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbContractChangeMngtMapper {
    PagingResult<SearchRes> selectContractChanges(SearchReq dto);

}
