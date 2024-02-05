package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchCustomerVisitIzRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchManagementCstInqrRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIndividualVisitPrdInqrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbIndividualVisitPrdInqrService {

    private final WsnbIndividualVisitPrdInqrMapper mapper;

    /**
     * 개인별 방문주기 조회 - 고객 방문 내역 조회
     * @param dto
     * @return List
     */
    public List<SearchCustomerVisitIzRes> getCustomerVisitIzs(SearchReq dto) {
        return mapper.selectCustomerVisitIzs(dto);
    }

    /**
     * 개인별 방문주기 조회 - 관리 고객 조회
     * @param dto
     * @return List
     */
    public List<SearchManagementCstInqrRes> getManagementCstInqrs(SearchReq dto) {
        return mapper.selectManagementCstInqrs(dto);
    }
}
