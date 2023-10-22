package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbAdvanceRefundAccountMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 선환불계좌조회 Service
 * </pre>
 *
 * @author jaeha.yeon
 * @since 2023-10-20
 */
@Service
@RequiredArgsConstructor
public class WwdbAdvanceRefundAccountService {

    private final WwdbAdvanceRefundAccountMapper mapper;

    /**
     * 선환불계좌조회 목록
     * @param pageInfo
     * @param req
     * @return PagingResult<SearchAdvanceRefundAccountRes>
     */
    public PagingResult<SearchAdvanceRefundAccountRes> getAdvanceRefundAccountPages(
        SearchAdvanceRefundAccountReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectAdvanceRefundAccount(req, pageInfo);
    }

    /**
     * 선환불계좌조회 엑셀다운로드
     * @param req
     * @return List<SearchAdvanceRefundAccountRes>
     */
    public List<SearchAdvanceRefundAccountRes> getAdvanceRefundAccountExcels(
        SearchAdvanceRefundAccountReq req
    ) {
        return mapper.selectAdvanceRefundAccount(req);
    }
}
