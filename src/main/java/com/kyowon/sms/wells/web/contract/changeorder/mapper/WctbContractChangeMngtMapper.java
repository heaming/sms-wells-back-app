package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WctbContractChangeMngtMapper {
    PagingResult<SearchContractChangeRes> selectContractChanges(SearchContractChangeReq dto);

}
