package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto;
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
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchRes> getIndividualPerformanceDetails(
        WfeeIndividualFeeDto.SearchReq dto
    ) {
        return this.mapper.selectIndividualPerformanceDetails(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 기본정보 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public WfeeIndividualFeeDto.FindHmstInformationRes getHmstInformation(WfeeIndividualFeeDto.SearchHmstReq dto) {
        return mapper.selectHmstInformation(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 기타내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchHmstEtcRes> getHmstEtcInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHmstEtcInformations(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 수수료 내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchHmstFeeRes> getHmstFeeInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHmstFeeInformations(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 공제 내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public WfeeIndividualFeeDto.FindHmstDeductionRes getHmstDeductions(WfeeIndividualFeeDto.SearchHmstReq dto) {
        return mapper.selectHmstDeduction(dto);
    }

    /**
     * 수수료 홈마스터 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchHmstPnpyamRes> getHmstPnpyamInformations(
        WfeeIndividualFeeDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHmstPnpyamInformations(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 기본정보 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public WfeeIndividualFeeDto.FindPlarInformationRes getPlarInformation(WfeeIndividualFeeDto.SearchPlarReq dto) {
        return mapper.selectPlarInformation(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 기타내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchPlarEtcRes> getPlarEtcInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlarEtcInformations(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 수수료 내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchPlarFeeRes> getPlarFeeInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlarFeeInformations(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 공제 내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public WfeeIndividualFeeDto.FindPlarDeductionRes getPlarDeduction(WfeeIndividualFeeDto.SearchPlarReq dto) {
        return mapper.selectPlarDeduction(dto);
    }

    /**
     * 수수료 P조직 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchPlarPnpyamRes> getPlarPnpyamInformations(
        WfeeIndividualFeeDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlarPnpyamInformations(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 기본정보 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public WfeeIndividualFeeDto.FindMngerInformationRes getMngerInformation(WfeeIndividualFeeDto.SearchMngerReq dto) {
        return mapper.selectMngerInformation(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 기타내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchMngerEtcRes> getMngerEtcInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return this.mapper.selectMngerEtcInformations(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 수수료 내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchMngerFeeRes> getMngerFeeInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return this.mapper.selectMngerFeeInformations(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 공제 내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public WfeeIndividualFeeDto.FindMngerDeductionRes getMngerDeduction(WfeeIndividualFeeDto.SearchMngerReq dto) {
        return mapper.selectMngerDeduction(dto);
    }

    /**
     * 수수료 M조직 개인별 실적 가지급금 세부내역 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchMngerPnpyamRes> getMngerPnpyamInformations(
        WfeeIndividualFeeDto.SearchMngerReq dto
    ) {
        return this.mapper.selectMngerPnpyamInformations(dto);
    }

}
