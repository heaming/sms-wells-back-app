package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbVirtualAccountDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbVirtualAccountMapper;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbAdvanceRefundAccountMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 가상계좌 조회 서비스
 * </pre>
 *
 * @author
 * @since 2023-05-08
 */
@Service
@RequiredArgsConstructor
public class WwdbVirtualAccountService {

    private final WwdbVirtualAccountMapper mapper;

    /**
     * 가상계좌조회 (모바일)
     * @param req
     * @return
     */
    public List<WwdbVirtualAccountDto.SearchRes> getVirtualAccounts(
        WwdbVirtualAccountDto.SearchReq req
    ) {
        return mapper.selectVirtualAccounts(req);
    }

    /**
     * 가상계좌입금내역조회 (모바일)
     * @param req
     * @return
     */
    public List<WwdbVirtualAccountDto.SearchDtlRes> getVirtualAccountDepositItemizations(
        WwdbVirtualAccountDto.SearchReq req
    ) {
        return mapper.selectVirtualAccountDepositItemizations(req);
    }

}
