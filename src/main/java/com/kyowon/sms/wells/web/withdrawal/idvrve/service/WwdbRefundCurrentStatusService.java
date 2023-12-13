package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchBalanceTransferRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchCardRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundCurrentStatusDto.SearchRefundHistoryTotalRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundCurrentStatusMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 환불현황 서비스
 * </pre>
 *
 * @author Sonkiseok
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WwdbRefundCurrentStatusService {

    private final WwdbRefundCurrentStatusMapper mapper;

    /**
     * 환불내역 목록
     *
     * @param req
     * @param pageInfo
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
     *
     * @param req
     * @return List<SearchRefundHistoryRes>
     */
    public List<SearchRefundHistoryRes> getRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return mapper.selectRefundHistory(req);
    }

    /**
     * 환불내역 목록 합계
     *
     * @param req
     * @return SearchRefundHistoryTotalRes
     */
    public SearchRefundHistoryTotalRes getRefundHistorySum(
        SearchRefundHistoryReq req
    ) {
        return mapper.selectRefundHistorySum(req);
    }

    /**
     * 카드사별 환불내역 목록
     *
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchCardRefundHistoryRes>
     */
    public PagingResult<SearchCardRefundHistoryRes> getCardRefundHistoryPages(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectCardRefundHistory(req, pageInfo);
    }

    /**
     * 카드사별 환불내역 엑셀다운로드
     *
     * @param req
     * @return List<SearchCardRefundHistoryRes>
     */
    public List<SearchCardRefundHistoryRes> getCardRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return mapper.selectCardRefundHistory(req);
    }

    /**
     * 전금내역 목록
     *
     * @param req
     * @param pageInfo
     * @return PagingResult<SearchBalanceTransferRefundHistoryRes>
     */
    public PagingResult<SearchBalanceTransferRefundHistoryRes> getBalanceTransferRefundHistoryPages(
        SearchRefundHistoryReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectBalanceTransferRefundHistory(req, pageInfo);
    }

    /**
     * 전금내역 목록 엑셀다운로드
     *
     * @param req
     * @return List<SearchBalanceTransferRefundHistoryRes>
     */
    public List<SearchBalanceTransferRefundHistoryRes> getBalanceTransferRefundHistoryExcels(
        SearchRefundHistoryReq req
    ) {
        return mapper.selectBalanceTransferRefundHistory(req);
    }
}
