package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncCustomerSearchMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 고객찾기
 * </pre>
 *
 * @author songmi.in
 * @since 2023-02-28
 */
@Service
@RequiredArgsConstructor
public class WbncCustomerSearchService {
    private final WbncCustomerSearchMapper mapper;

    /**
     * 고객찾기리스트 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getCustomers(SearchReq dto) {
        return mapper.selectCustomers(dto);
    }
}
