package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositRefundItemizationDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositRefundItemizationMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 월별입금/환불금액조회 서비스
 * </pre>
 *
 * @author
 * @since 2023-05-08
 */
@Service
@RequiredArgsConstructor
public class WwdbDepositRefundItemizationService {

    private final WwdbDepositRefundItemizationMapper mapper;

    /**
     * 월별입금/환불금액조회
     * @param req
     * @return List<SearchRes>
     */
    public List<WwdbDepositRefundItemizationDto.SearchRes> getDepositRefundItemizations(
        WwdbDepositRefundItemizationDto.SearchReq req
    ) {
        return mapper.selectDepositRefundItemizations(req);
    }
}
