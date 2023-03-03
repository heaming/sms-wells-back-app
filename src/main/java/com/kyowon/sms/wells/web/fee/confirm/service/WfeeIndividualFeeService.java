package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto.*;
import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeIndividualFeeMapper;

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
public class WfeeIndividualFeeService {
    private final WfeeIndividualFeeMapper mapper;

    /**
     * 수수료 개인별 실적 상세 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchRes> getIndividualPerformanceDetails(
        SearchReq dto
    ) {
        return this.mapper.selectIndividualPerformanceDetails(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 기본정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstRes getHmst(SearchHmstReq dto) {
        return mapper.selectHmst(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 기타내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstEtcRes> getHmstEtcs(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHmstEtcs(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstFeeRes> getHmstFees(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHmstFees(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindHmstDeductionRes getHmstDeductions(SearchHmstReq dto) {
        return mapper.selectHmstDeduction(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchHmstPnpyamRes> getHmstPnpyams(
        SearchHmstReq dto
    ) {
        return this.mapper.selectHmstPnpyams(dto);
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
    public FindPlarDeductionRes getPlarDeduction(SearchPlarReq dto) {
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

    /**
     * 수수료 M조직 개인별 실적 기본정보 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerRes getMnger(SearchMngerReq dto) {
        return mapper.selectMnger(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 기타내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerEtcRes> getMngerEtcs(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerEtcs(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 수수료 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerFeeRes> getMngerFees(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerFees(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 공제 내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public FindMngerDeductionRes getMngerDeduction(SearchMngerReq dto) {
        return mapper.selectMngerDeduction(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * perfYm : 실적년월,
     * no : 번호 }
     * @return 조회결과
     */
    public List<SearchMngerPnpyamRes> getMngerPnpyams(
        SearchMngerReq dto
    ) {
        return this.mapper.selectMngerPnpyams(dto);
    }

    /**
     * 추진단 수수료 조회
     * @param dto : {
     * perfYm : 실적년월,
     * ogTp : 조직유형,
     * rsbTp : 직책유형,
     * ogLevl1 : 조직레벨1,
     * ogLevl2 : 조직레벨2,
     * ogLevl3 : 조직레벨3,
     * no : 번호,
     * feeDsbYn : 수수료지급여부}
     * @return 조회결과
     */
    public List<SearchFeeRes> getFees(
        SearchFeeReq dto
    ) {
        return this.mapper.selectFees(dto);
    }

    /**
     * 홈마스터 수수료 조회
     * @param dto : {
     * perfYm : 실적년월,
     * ogTp : 조직유형,
     * rsbTp : 직책유형,
     * ogLevl1 : 조직레벨1,
     * ogLevl2 : 조직레벨2,
     * ogLevl3 : 조직레벨3,
     * no : 번호,
     * feeDsbYn : 수수료지급여부}
     * @return 조회결과
     */
    public List<SearchFeeHmstRes> getFeeHmsts(
        SearchFeeReq dto
    ) {
        return this.mapper.selectFeeHmsts(dto);
    }

}
