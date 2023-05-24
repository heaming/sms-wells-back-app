package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundCurrentStatusMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbRefundCurrentStatusService {

    private final WwdbRefundCurrentStatusMapper mapper;

    /**
     * 환불내역 목록
     * @param pageInfo 
     * @param SearchRefundHistoryReq
     * @return PagingResult<SearchRefundHistoryRes>
     */
    public PagingResult<SearchRefundHistoryRes> getRefundHistoryPages(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectRefundHistory(req, pageInfo);
    }

    /**
     * 환불내역 엑셀다운로드
     * @param SearchRefundHistoryReq
     * @return List<SearchRefundHistoryRes>
     */
    public List<SearchRefundHistoryRes> getRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return mapper.selectRefundHistory(req);
    }

    /**
     * 카드사별 환불내역 목록
     * @param pageInfo 
     * @param SearchCardRefundHistoryReq
     * @return PagingResult<SearchCardRefundHistoryRes> 
     */
    public PagingResult<SearchCardRefundHistoryRes> getCardRefundHistoryPages(
        SearchCardRefundHistoryReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectCardRefundHistory(req, pageInfo);
    }

    /**
     * 카드사별 환불내역 엑셀다운로드
     * @param SearchCardRefundHistoryReq
     * @return List<SearchCardRefundHistoryRes>
     */
    public List<SearchCardRefundHistoryRes> getCardRefundHistoryExcels(
        SearchCardRefundHistoryReq req
    ) {
        return mapper.selectCardRefundHistory(req);
    }

    /**
     * 전금내역 목록
     * @param pageInfo 
     * @param SearchBalanceTransferRefundHistoryReq
     * @return PagingResult<SearchBalanceTransferRefundHistoryRes>
     */
    public PagingResult<SearchBalanceTransferRefundHistoryRes> getBalanceTransferRefundHistoryPages(
        SearchBalanceTransferRefundHistoryReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectBalanceTransferRefundHistory(req, pageInfo);
    }

    /**
     * 전금내역 목록 엑셀다운로드
     * @param SearchBalanceTransferRefundHistoryReq
     * @return List<SearchBalanceTransferRefundHistoryRes>
     */
    public List<SearchBalanceTransferRefundHistoryRes> getBalanceTransferRefundHistoryExcels(
        SearchBalanceTransferRefundHistoryReq req
    ) {
        return mapper.selectBalanceTransferRefundHistory(req);
    }
}
