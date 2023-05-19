package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRentalExpirationExcessiveAmountDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRentalExpirationExcessiveAmountMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbRentalExpirationExcessiveAmountService {

    private final WwdbRentalExpirationExcessiveAmountMapper mapper;

    /**
     * 렌탈만료 초과금 현황
     * @param SearchReq
     * @return SearchRes
     */
    public PagingResult<WwdbRentalExpirationExcessiveAmountDto.SearchRes> getRentalExpirationExcessiveAmountPage(
        WwdbRentalExpirationExcessiveAmountDto.SearchReq req, PageInfo pageInfo
    ) {
        return mapper.selectRentalExpirationExcessiveAmount(req, pageInfo);
    }

    /**
     * 렌탈만료 초과금 현황 엑셀다운로드
     * @param SearchReq
     * @return SearchRes
     */
    public List<WwdbRentalExpirationExcessiveAmountDto.SearchRes> getRentalExpirationExcessiveForExcelDownload(
        WwdbRentalExpirationExcessiveAmountDto.SearchReq req
    ) {
        return mapper.selectRentalExpirationExcessiveAmount(req);
    }

}
