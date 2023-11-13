package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbContractRefundDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbContractRefundMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 계약금 환불 목록 서비스
 * </pre>
 *
 * @author Sonkiseok
 * @since 2023-04-07
 */
@Service
@RequiredArgsConstructor
public class WwdbContractRefundService {

    private final WwdbContractRefundMapper mapper;

    /**
     * 계약금 환불현황 목록 조회 / 페이징
     * @param req
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchContractRefundRes> getContractRefundPages(
        SearchContractRefundReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectContractRefunds(req, pageInfo);
    }

    /**
     * 웰스 환불 목록(카드사별) 조회 / 엑셀 다운로드
     * @param req
     * @return
     */
    public List<SearchContractRefundRes> getContractRefundExcels(
        SearchContractRefundReq req
    ) {
        return mapper.selectContractRefunds(req);
    }

    /**
     * 계약금 환불 내역 집계
     * @param req
     * @return
     */
    public SearchContractRefundAggregateRes getContractRefundAggregates(
        SearchContractRefundAggregateReq req
    ) {
        return mapper.selectContractRefundAggregates(req);
    }

    /**
     * 계약금 환불 목록의 값 합계
     * @param req
     * @return
     */
    public SearchContractRefundSummaryRes getContractRefundSummary(
        SearchContractRefundReq req
    ) {
        return mapper.selectContractRefundSummary(req);
    }

}
