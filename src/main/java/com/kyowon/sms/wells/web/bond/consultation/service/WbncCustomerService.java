package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncCustomerMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 채권상담 고객리스트
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-02-10
 */
@Service
@RequiredArgsConstructor
public class WbncCustomerService {

    private final WbncCustomerMapper mapper;

    /**
     * 채권상담 고객리스트 조회
     * @param dto 검색 조건
     * @return 채권상담 고객리스트 조회 결과
     */
    public List<SearchRes> getCustomers(SearchReq dto) throws Exception {
        return mapper.selectCustomers(dto);
    }

    /**
     * 채권상담 고객상세 조회
     * @param dto 검색 조건
     * @return 채권상담 고객상세 정보조회 결과
     */
    public Optional<FindRes> getCustomerDetail(FindReq dto) {
        return mapper.selectCustomerDetail(dto);
    }

    /**
     * 채권상담 고객상세 조회
     * @param dto 검색 조건
     * @return 채권상담 고객상세 목록조회 결과
     */
    public List<FindCustomerDetailRes> getCustomerDetails(FindCustomerDetailReq dto) throws Exception {
        return mapper.selectCustomerDetails(dto);
    }

    /**
     * 채권상담 고객상세 상담이력 조회
     * @param dto 검색 조건
     * @return 채권상담 고객상세 상담이력 조회 결과
     */
    public List<FindCounselHistoryRes> getCounselHistorys(FindCounselHistoryReq dto) throws Exception {
        return mapper.selectCounselHistorys(dto);
    }
}
