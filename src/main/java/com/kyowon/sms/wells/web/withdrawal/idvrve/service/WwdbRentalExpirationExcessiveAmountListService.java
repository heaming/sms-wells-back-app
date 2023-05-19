package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRentalExpirationExcessiveAmountListDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRentalExpirationExcessiveAmountListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbRentalExpirationExcessiveAmountListService {

    private final WwdbRentalExpirationExcessiveAmountListMapper mapper;

    /**
     * 렌탈만료 초과금 현황
     * @param SearchReq
     * @return SearchRes
     */
    public PagingResult<WwdbRentalExpirationExcessiveAmountListDto.SearchRes> getRentalExpirationExcessiveAmountPage(
        WwdbRentalExpirationExcessiveAmountListDto.SearchReq req, PageInfo pageInfo
    ) {
        return mapper.selectRentalExpirationExcessiveAmount(req, pageInfo);
    }

    /**
     * 렌탈만료 초과금 현황 엑셀다운로드
     * @param SearchReq
     * @return SearchRes
     */
    public List<WwdbRentalExpirationExcessiveAmountListDto.SearchRes> getRentalExpirationExcessiveForExcelDownload(
        WwdbRentalExpirationExcessiveAmountListDto.SearchReq req
    ) {
        return mapper.selectRentalExpirationExcessiveAmount(req);
    }

}
