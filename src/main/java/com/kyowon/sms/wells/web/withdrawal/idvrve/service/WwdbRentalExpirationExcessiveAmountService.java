package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRentalExpirationExcessiveAmountDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRentalExpirationExcessiveAmountMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 렌탈만료초과금현황 서비스
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-05-19
 */
@Service
@RequiredArgsConstructor
public class WwdbRentalExpirationExcessiveAmountService {

    private final WwdbRentalExpirationExcessiveAmountMapper mapper;

    /**
     * 렌탈만료초과금현황 조회 / 페이징
     * @param req
     * @param pageInfo
     * @return
     */
    public PagingResult<WwdbRentalExpirationExcessiveAmountDto.SearchRes> getRentalExpirationExcessiveAmountPage(
        WwdbRentalExpirationExcessiveAmountDto.SearchReq req, PageInfo pageInfo
    ) {
        return mapper.selectRentalExpirationExcessiveAmount(req, pageInfo);
    }

    /**
     * 렌탈만료초과금현황 조회 / 엑셀 다운로드
     * @param req
     * @return
     */
    public List<WwdbRentalExpirationExcessiveAmountDto.SearchRes> getRentalExpirationExcessiveForExcelDownload(
        WwdbRentalExpirationExcessiveAmountDto.SearchReq req
    ) {
        return mapper.selectRentalExpirationExcessiveAmount(req);
    }

}
