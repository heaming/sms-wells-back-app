package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeHomeMasterDto.*;
import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeIndividualFeeHomeMasterMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 홈마스터 수수료 개인 상세 서비스
 * </pre>
 *
 * @author LEE SUNHO
 * @since 2023.10.17
 */
@Service
@RequiredArgsConstructor
public class WfeeIndividualFeeHomeMasterService {

    private final WfeeIndividualFeeHomeMasterMapper mapper;

    public List<SearchHmstRes> getIndividualPerformanceHmstDetails(SearchHmstReq dto) {
        return mapper.selectIndividualPerformanceHmstDetails(dto);
    }

    /**
     * 홈마스터 기본정보 조회
     * @param dto 조회조건
     * @return 홈마스터 기본정보
     */
    public FindHmstRes getHmst(SearchHmstReq dto) {
        return mapper.selectHmst(dto);
    }

    /**
     * 홈마스터 판매 및 기타내역 조회
     * @param dto 조회조건
     * @return 판매 및 기타내역 목록
     */
    public List<SearchHmstEtcRes> getHmstEtcs(SearchHmstReq dto) {
        return mapper.selectHmstEtcs(dto);
    }

    /**
     * 홈마스터 수수료 내역 조회
     * @param dto 조회조건
     * @return 수수료 내역 목록
     */
    public List<SearchHmstFeeRes> getHmstFees(SearchHmstReq dto) {
        return mapper.selectHmstFees(dto);
    }

    /**
     * 홈마스터 공제내역 조회
     * @param dto 조회조건
     * @return 공제내역 목록
     */
    public List<FindHmstDeductionRes> getHmstDeductions(SearchHmstReq dto) {
        return mapper.selectHmstDeductions(dto);
    }

    /**
     * 홈마스터 가지급금 세부내역 조회
     * @param dto 조회조건
     * @return 가지급금 세부내역 목록
     */
    public List<SearchHmstPnpyamRes> getHmstPnpyams(SearchHmstReq dto) {
        return mapper.selectHmstPnpyams(dto);
    }
}
