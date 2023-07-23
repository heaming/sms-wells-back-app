package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbContractRefundDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbContractRefundMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbContractRefundService {

    private final WwdbContractRefundMapper mapper;

    /**
     * 환불내역 목록
     * @param pageInfo
     * @param SearchContractRefundReq
     * @return PagingResult<SearchContractRefundRes>
     */
    public PagingResult<SearchContractRefundRes> getContractRefundPages(
        SearchContractRefundReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectContractRefunds(req, pageInfo);
    }

    /**
     * 환불내역 엑셀다운로드
     * @param List
     * @param SearchContractRefundReq
     * @return List<SearchContractRefundRes>
     */
    public List<SearchContractRefundRes> getContractRefundExcels(
        SearchContractRefundReq req
    ) {
        return mapper.selectContractRefunds(req);
    }

    /**
     * 환불내역 집계
     * @param SearchContractRefundAgrgReq
     * @return SearchContractRefundAgrgRes
     */
    public SearchContractRefundAggregateRes getContractRefundAggregates(
        SearchContractRefundAggregateReq req
    ) {
        return mapper.selectContractRefundAggregates(req);
    }

    /**
     * 환불내역 집계
     * @param getContractRefundSummary
     * @return SearchContractRefundSummaryRes
     */
    public SearchContractRefundSummaryRes getContractRefundSummary(
        SearchContractRefundReq req
    ) {
        return mapper.selectContractRefundSummary(req);
    }

}
