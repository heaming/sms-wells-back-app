package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaOrganizationNetOrderConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOrganizationNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOrganizationNetOrderDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaOrganizationNetOrderMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfeaOrganizationNetOrderService {

    private final WfeaOrganizationNetOrderMapper mapper;

    private final WfeaOrganizationNetOrderConverter converter;

    /**
     * 조직별 실적 집계
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int saveOrganizationAggregates(WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto) {
        int processCnt = 1;
        return processCnt;
    }

    /**
     * BS 실적 집계
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int saveBsPerformances(WfeaOrganizationNetOrderDto.SaveBsReq dto) {
        int processCnt = 0;

        WfeaOrganizationNetOrderDvo dvo = converter.mapSaveBsReqToWfeaOrganizationNetOrderDvo(dto);

        mapper.deleteBsPerformances(dvo);
        processCnt = mapper.insertBsPerformances(dvo);

        BizAssert.isTrue(processCnt > 0, "MSG_ALT_AGRG_FAIL");

        return processCnt;
    }

    /**
     * 조직별 실적 확정
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int editOrganizationAggregates(WfeaOrganizationNetOrderDto.SaveOgNetOrderReq dto) {
        int processCnt = 1;
        return processCnt;
    }

    /**
     * WELLS 홈마스터 수수료 순주문 관리 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchHmstRes> getHomeMasters(
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasters(dto);
    }

    /**
     * WELLS 홈마스터 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchHmstReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchHmstFeeRes> getHomeMasterFees(
        WfeaOrganizationNetOrderDto.SearchHmstReq dto
    ) {
        return this.mapper.selectHomeMasterFees(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchMngerRes> getManagers(
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.mapper.selectManagers(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchMngerSellFeeRes> getManagerFees(
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.mapper.selectManagerFees(dto);
    }

    /**
     * WELLS M조직 수수료 순주문 관리 집계정보 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchMngerAgrgRes> getManagerAggregation(
        WfeaOrganizationNetOrderDto.SearchMngerReq dto
    ) {
        return this.mapper.selectManagerAggregation(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchPlarRes> getPlanners(WfeaOrganizationNetOrderDto.SearchPlarReq dto) {
        return this.mapper.selectPlanners(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리(판매수수료) 목록 조회
     * @param 'SearchPlarReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchPlarSellFeeRes> getPlannerSellFees(
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlannerFees(dto);
    }

    /**
     * WELLS P조직 수수료 순주문 관리 집계정보 조회
     * @param 'SearchMngerReq' 검색조건 정보
     * @return 조회된 데이터
     */
    public List<WfeaOrganizationNetOrderDto.SearchPlarAgrgRes> getPlannerAggregation(
        WfeaOrganizationNetOrderDto.SearchPlarReq dto
    ) {
        return this.mapper.selectPlannerAggregation(dto);
    }

}
