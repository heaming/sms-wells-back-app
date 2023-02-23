package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.List;

import com.kyowon.sms.wells.web.fee.calculation.converter.WfebOgFeeConverter;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOgFeeDto.*;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebOgFeeMapper;

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
public class WfebOgFeeService {

    private final WfebOgFeeMapper mapper;
    private final WfebOgFeeConverter converter;;

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

    public List<SearchMngerRes> getManagerFees(SearchMngerReq dto) {
        return this.mapper.selectManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 지점장 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchMngerBrmgrRes> getManagerBranchManagerFees(SearchMngerReq dto) {
        return this.mapper.selectManagerBranchManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 생성관리 전체 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */

    public List<SearchMngerTotalRes> getManagerTotalFees(SearchMngerReq dto) {
        return this.mapper.selectManagerTotalFees(dto);
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
