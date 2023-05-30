package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaSeedingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSeedingDto.SearchMachineRes;

@Service
@RequiredArgsConstructor
public class WctaSeedingService {
    private final WctaSeedingMapper mapper;

    public List<SearchMachineRes> getMachinery(SearchMachineReq dto) {
        return mapper.selectMachinery(dto);
    }
}
