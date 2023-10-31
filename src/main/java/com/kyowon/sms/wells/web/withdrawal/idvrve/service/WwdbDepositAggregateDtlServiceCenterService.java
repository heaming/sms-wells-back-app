package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchCodeRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateDtlServiceCenterDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositAggregateDtlServiceCenterMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * 입금집계 상세현황 서비스
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-10-31
 */
@Service
@RequiredArgsConstructor
public class WwdbDepositAggregateDtlServiceCenterService {

    private final WwdbDepositAggregateDtlServiceCenterMapper mapper;

    /**
     * 입금집계 상세현황 서비스 조회 / 페이징
     * @param req 입금집계 상세현황 서비스 조회 DTO
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getDepositAggregateServiceCenterPages(
        SearchReq req, PageInfo pageInfo
    ) {
        return mapper.selectDepositAggregateServiceCenters(req, pageInfo);
    }

    /**
     * 입금집계 상세현황 서비스 조회 / 엑셀 다운로드
     * @param req 입금집계 상세현황 서비스 조회 DTO
     * @return List<SearchRes>
     */
    public List<SearchRes> getDepositAggregateServiceCenterExcels(
        SearchReq req
    ) {
        return mapper.selectDepositAggregateServiceCenters(req);
    }

    /**
     * 입금집계 상세현황 서비스 조회 - 조직 내역
     * @param req 조직 내역 조회 DTO
     * @return List<SearchCodeRes>
     */
    public List<SearchCodeRes> getCenterCodes(
        SearchReq req
    ) {
        return mapper.selectCenterCodes(req);
    }

    /**
     * 입금집계 상세현황 서비스 합계 조회
     * @param req 입금집계 상세현황 서비스 합계 조회 DTO
     * @return SearchSumRes
     */
    public SearchSumRes getDepositAggregateServiceCentersTotal(
        SearchReq req
    ) {
        return mapper.selectDepositAggregateServiceCentersTotal(req);
    }

}
