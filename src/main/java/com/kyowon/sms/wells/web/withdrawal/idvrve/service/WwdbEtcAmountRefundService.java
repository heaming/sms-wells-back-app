package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundAggregateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundAggregateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAmountRefundDto.SearchEtcAmountRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbEtcAmountRefundMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbEtcAmountRefundService {

    private final WwdbEtcAmountRefundMapper mapper;

    /**
     * 기타 선수금 환불 목록
     * @param pageInfo 
     * @param SearchEtcAmountRefundReq
     * @return PagingResult<SearchEtcAmountRefundRes>
     */
    public PagingResult<SearchEtcAmountRefundRes> getEtcAmountRefundPages(
        SearchEtcAmountRefundReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectEtcAmountRefunds(req, pageInfo);
    }

    /**
     * 기타 선수금 환불 목록 엑셀다운로드
     * @param List 
     * @param SearchEtcAmountRefundReq
     * @return List<SearchEtcAmountRefundRes>
     */
    public List<SearchEtcAmountRefundRes> getEtcAmountRefundExcels(
        SearchEtcAmountRefundReq req
    ) {
        return mapper.selectEtcAmountRefunds(req);
    }

    /**
     * 기타 선수금 환불 목록 집계
     * @param SearchCntramRfndListAgrgReq
     * @return SearchCntramRfndListAgrgRes
     */
    public SearchEtcAmountRefundAggregateRes getEtcAmountRefundAggregates(
        SearchEtcAmountRefundAggregateReq req
    ) {
        return mapper.selectEtcAmountRefundAggregates(req);
    }
}
