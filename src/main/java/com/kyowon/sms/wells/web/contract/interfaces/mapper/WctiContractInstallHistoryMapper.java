package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto.SearchRes;

@Mapper
public interface WctiContractInstallHistoryMapper {
    List<SearchRes> selectIstlcChHist(SearchReq req);
}

