package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.fee.common.service.ZfezFeeArticleService;
import com.kyowon.sms.wells.web.fee.calculation.converter.WfebOrganizationFeeConverter;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOrganizationFeeDto.*;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebOrganizationFeeMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * WELLS 홈마스터 수수료 생성관리 서비스
 * </pre>
 *
 * @author gs.piit150
 * @since 2023-02-03
 */

@Service
@RequiredArgsConstructor
public class WfebOrganizationFeeService {

    private final WfebOrganizationFeeMapper mapper;
    private final WfebOrganizationFeeConverter converter;

    private final ZfezFeeArticleService zfezFeeArticleService;

    /**
     * 수수료 항목 정보 조회
     * @param dto
     * @return 조회결과
     */
    public List<HashMap<String, Object>> getFeeArticles(String perfYm, String ogTpCd, String feeCalcUnitTpCd) {
        return zfezFeeArticleService.getFeeArticles(perfYm, ogTpCd, feeCalcUnitTpCd);
    }

    /**
     * WELLS 홈마스터 수수료 생성관리 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchHmstRes> getHomeMasterFees(SearchHmstReq dto) {
        return this.mapper.selectHomeMasterFees(dto);
    }

    /**
     * WELLS 홈마스터 지점장 수수료 생성관리 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchHmstBrmgrRes> getHomeMasterBranchManagerFees(SearchHmstReq dto) {
        return this.mapper.selectHomeMasterBranchManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<HashMap<String, Object>> getManagerFees(SearchMngerReq dto) {
        // 실적년월의 수수료 항목 정보 조회
        List<HashMap<String, Object>> feeArticles = getFeeArticles(dto.perfYm(), "W02", dto.feeCalcUnitTpCd());
        return this.mapper.selectManagerFees(dto, feeArticles);
    }

    /**
     * WELLS M조직 수수료 생성관리 전체 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<HashMap<String, Object>> getManagerTotalFees(SearchMngerReq dto) {
        return this.mapper.selectManagerTotalFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 전체 2023년4월 이전 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<HashMap<String, Object>> getManagerTotalBeforeFees(SearchMngerReq dto) {
        return this.mapper.selectManagerTotalBeforeFees(dto);
    }

    /**
     * WELLS P조직 수수료 생성관리 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchPlarRes> getPlannerFees(SearchPlarReq dto) {
        return this.mapper.selectPlannerFees(dto);
    }

    /**
     * WELLS P조직 수수료 생성관리 지점장 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchPlarBrmgrRes> getPlannerBranchManagerFees(SearchPlarReq dto) {
        return this.mapper.selectPlannerBranchManagerFees(dto);
    }

    /**
     * WELLS P조직 수수료 생성관리 전체 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchPlarTotalRes> getPlannerTotalFees(SearchPlarReq dto) {
        return this.mapper.selectPlannerTotalFees(dto);
    }

}
