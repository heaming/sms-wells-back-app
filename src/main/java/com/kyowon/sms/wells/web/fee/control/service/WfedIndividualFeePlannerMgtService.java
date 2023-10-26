package com.kyowon.sms.wells.web.fee.control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeePlannerMgtDto.*;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedIndividualFeePlannerMgtMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * P조직 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit272
 * @since 2023.10.17
 */
@Service
@RequiredArgsConstructor
public class WfedIndividualFeePlannerMgtService {
    private final WfedIndividualFeePlannerMgtMapper mapper;

    /**
     * 개인별 수수료 관리 P조직 사업자 정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindPlarEntrpRes getPlarEntrp(SearchPlarReq dto) {
        return mapper.selectPlarEntrp(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<FindPlarDeductionRes> getPlarDeduction(SearchPlarReq dto) {
        return mapper.selectPlarDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 기본 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public SearchPlarEtcRes getPlarEtcs(SearchPlarReq dto) {
        return mapper.selectPlarEtcs(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarFeeRes> getPlarFee(SearchPlarReq dto) {
        return mapper.selectPlarFee(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 조정내역 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarControlRes> getPlarControls(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlarControls(dto);
    }
}
