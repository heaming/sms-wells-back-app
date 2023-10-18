package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.SearchReq;
import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeePlannerDto.*;
import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeIndividualFeePlannerMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfeeIndividualFeePlannerService {
    private final WfeeIndividualFeePlannerMapper mapper;

    /**
     * 수수료 개인별 실적 상세 조회(P조직)
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarRes> getIndividualPerformancePlarDetails(
        SearchReq dto
    ) {
        return this.mapper.selectIndividualPerformancePlarDetails(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 기본정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindPlarRes getPlar(SearchPlarReq dto) {
        return mapper.selectPlar(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 기타내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarEtcRes> getPlarEtcs(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlarEtcs(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarFeeRes> getPlarFees(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlarFees(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<FindPlarDeductionRes> getPlarDeduction(SearchPlarReq dto) {
        return mapper.selectPlarDeduction(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchPlarPnpyamRes> getPlarPnpyams(
        SearchPlarReq dto
    ) {
        return this.mapper.selectPlarPnpyams(dto);
    }
}
