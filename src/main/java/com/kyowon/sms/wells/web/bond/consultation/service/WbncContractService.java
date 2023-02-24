package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncContractDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncContractDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncContractMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 채권상담 계약리스트 조회
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-02-10
 */
@Service
@RequiredArgsConstructor
public class WbncContractService {

    private final WbncContractMapper mapper;

    /**
     * 채권상담 계약리스트 조회
     * @param dto 검색 조건
     * @return 채권상담 계약리스트 조회 결과
     */
    public List<SearchRes> getContracts(SearchReq dto) throws Exception {
        return mapper.selectContracts(dto);
    }
}
