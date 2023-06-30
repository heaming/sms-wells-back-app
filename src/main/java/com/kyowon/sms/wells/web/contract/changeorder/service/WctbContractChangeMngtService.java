package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbContractChangeMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctbContractChangeMngtService {

    private final WctbContractChangeMngtMapper mapper;

    public PagingResult<SearchContractChangeRes> getContractChangePages(
        SearchContractChangeReq dto, PageInfo pageInfo
    ) {
        return mapper.selectContractChanges(dto, pageInfo);

    }

    public WctbContractChangeMngtDto.FindPartnerRes getPartnerByCntrNo(String cntrNo, String cntrSn) {
        // 계약변경관리-파트너 변경(조회)
        return mapper.selectPartnerByCntrNo(cntrNo, cntrSn);
    }
}
