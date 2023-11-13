package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbEtcAmountRefundMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 기타 선수금 환불 목록 서비스
 * </pre>
 *
 * @author Sonkiseok
 * @since 2023-04-07
 */
@Service
@RequiredArgsConstructor
public class WwdbEtcAmountRefundService {

    private final WwdbEtcAmountRefundMapper mapper;

    /**
     * 기타 선수금 환불 목록 조회 / 페이징
     * @param req
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchEtcAmountRefundRes> getEtcAmountRefundPages(
        SearchEtcAmountRefundReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectEtcAmountRefunds(req, pageInfo);
    }

    /**
     * 기타 선수금 환불 목록 조회 / 엑셀 다운로드
     * @param req
     * @return
     */
    public List<SearchEtcAmountRefundRes> getEtcAmountRefundExcels(
        SearchEtcAmountRefundReq req
    ) {
        return mapper.selectEtcAmountRefunds(req);
    }

    /**
     * 기타 선수금 환불 목록 집계
     * @param req
     * @return
     */
    public SearchEtcAmountRefundAggregateRes getEtcAmountRefundAggregates(
        SearchEtcAmountRefundAggregateReq req
    ) {
        return mapper.selectEtcAmountRefundAggregates(req);
    }

    /**
     * 기타 선수금 환불 목록 총합계(Summary)
     * @param req
     * @return
     */
    public SearchEtcAmountRefundSummaryRes getEtcAmountRefundSummaryPages(
        SearchEtcAmountRefundReq req
    ) {
        return mapper.selectEtcAmountRefundsSummary(req);
    }

}
