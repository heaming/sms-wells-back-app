package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailSummaryDto.FindRes;

@Mapper
public interface WctiContractDetailSummaryMapper {

    List<FindRes> selectDetailSummary(FindReq dto);
}
