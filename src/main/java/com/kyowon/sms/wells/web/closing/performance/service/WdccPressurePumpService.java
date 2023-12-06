package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.performance.converter.WdccPressurePumpConverter;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.RemoveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchConfirmManagementReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchConfirmManagementRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchSalesBaseReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SearchSalesBaseRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccPressurePumpDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccPressurePumpMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdccPressurePumpService {

    private final WdccPressurePumpMapper mapper;
    private final WdccPressurePumpConverter converter;

    public List<SearchConfirmManagementRes> getConfirmManagement(SearchConfirmManagementReq dto) {
        return this.mapper.selectConfirmManagement(dto);
    }

    public List<SearchSalesBaseRes> getSalesBase(SearchSalesBaseReq dto) {
        return this.mapper.selectSalesBase(dto);
    }

    /**
     * 가압펌프 설치/철거 관리 - 가압펌프 확정관리 대상 저장
     * @param dto
     * @return processCount
     */
    @Transactional
    public int saveConfirmManagement(List<SaveReq> dtos) {
        int processCount = 0;
        if (CollectionUtils.isNotEmpty(dtos)) {
            for (SaveReq dto : dtos) {
                WdccPressurePumpDvo dvo = this.converter.mapSaveReqToWdccPressurePumpDvo(dto);
                processCount += this.mapper.insertConfirmManagement(dvo);
            }
        }

        return processCount;
    }

    /**
     * 가압펌프 설치/철거 관리 - 가압펌프 확정관리 대상 삭제
     * @param dto
     * @return processCount
     */
    @Transactional
    public int removeConfirmManagement(List<RemoveReq> dtos) {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WdccPressurePumpDvo dvo = converter.mapRemoveReqToWdccPressurePumpDvo(dto);
            processCount += mapper.deleteConfirmManagement(dvo);
        }

        return processCount;
    }
}
