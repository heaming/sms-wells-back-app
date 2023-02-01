package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctbCancellationMtrSetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCancellationMtrSetService {
    private final WctbCancellationMtrSetMapper mapper;

    @Transactional
    public int cancellationMtrClSe(SearchReq req) {

        List<SearchRes> contractList = mapper.selectContractBase(req);

        int processCount = 0;
        int result = 0;
        for (Iterator<SearchRes> iterator = contractList.iterator(); iterator.hasNext(); processCount += result) {
            SearchRes res = iterator.next();
            mapper.updateContractDetail(res);
            result = mapper.insertContractDetailHist(res);
        }
        return processCount;
    }

}
