package com.kyowon.sms.wells.web.fee.control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.dto.WfedIndividualFeeMgtDto.*;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedIndividualFeeMgtMapper;

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
public class WfedIndividualFeeMgtService {
    private final WfedIndividualFeeMgtMapper mapper;

    /**
     * 개인별 수수료 관리 홈마스터 사업자 정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstEntrpRes getHmstEntrp(SearchHmstReq dto) {
        return mapper.selectHmstEntrp(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 기본정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstBasicRes getHmstBasic(SearchHmstReq dto) {
        return mapper.selectHmstBasic(dto);
    }

    /**
     * 개인별 수수료 관리 홈마스터 총계정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstTotalSumRes getHmstTotalSum(SearchHmstReq dto) {
        return mapper.selectHmstTotalSum(dto);
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
    public FindHmstFeeRes getHmstFee(SearchHmstReq dto) {
        return mapper.selectHmstFee(dto);
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
        return this.mapper.selectHmstControls(dto);
    }

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
    public FindPlarDeductionRes getPlarDeduction(SearchPlarReq dto) {
        return mapper.selectPlarDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 P조직 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindPlarFeeRes getPlarFee(SearchPlarReq dto) {
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

    /**
     * 개인별 수수료 관리 M조직 사업자 정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerEntrpRes getMngerEntrp(SearchMngerReq dto) {
        return mapper.selectMngerEntrp(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 BS 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerBeforeServiceRes> getMngerBeforeServices(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerBeforeServices(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerDeductionRes getMngerDeduction(SearchMngerReq dto) {
        return mapper.selectMngerDeduction(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerFeeRes getMngerFee(SearchMngerReq dto) {
        return mapper.selectMngerFee(dto);
    }

    /**
     * 개인별 수수료 관리 M조직 조정내역 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerControlRes> getMngerControls(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerControls(dto);
    }

}
