package com.kyowon.sms.wells.web.fee.control.service;

import static com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeHomeMasterMgtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.mapper.WfedIndividualFeeHomeMasterMgtMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WfedIndividualFeeHomeMasterMgtService {
    private final WfedIndividualFeeHomeMasterMgtMapper mapper;

    public FindHmstEntrpRes getHmstEntrp(
        SearchHmstReq dto
    ) {
        return mapper.selectHmstEntrp(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstDeductionRes getHmstDeduction(SearchHmstReq dto) {
        return mapper.selectHmstDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstFeeRes> getHmstFees(SearchHmstReq dto) {
        return mapper.selectHmstFees(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 조정내역 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstControlRes> getHmstControls(
        SearchHmstReq dto
    ) {
        return mapper.selectHmstControls(dto);
    }
}
