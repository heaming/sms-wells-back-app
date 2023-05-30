package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineRes;

@Mapper
public interface WctaSeedingMapper {
    List<SearchMachineRes> selectMachinery(SearchMachineReq dto);
}
