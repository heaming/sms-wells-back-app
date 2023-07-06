package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.BranchsAndServiceCentersRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.ManagersAndEngineersRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsPeriodCustomerTfMgtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncBsPeriodCustomerTfMgtService {

    private final WsncBsPeriodCustomerTfMgtMapper mapper;

    public PagingResult<SearchRes> getBsPeriodCustomers(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBsPeriodCustomers(dto, pageInfo);
    }

    public List<SearchRes> getBsPeriodCustomersForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectBsPeriodCustomers(dto);
    }

    public List<BranchsAndServiceCentersRes> getBranchsAndServiceCenters() {
        return mapper.selectBranchsAndServiceCenters();
    }

    public List<ManagersAndEngineersRes> getManagersAndEngineers(String ogId) {
        return mapper.selectManagersAndEngineers(ogId);
    }
}
