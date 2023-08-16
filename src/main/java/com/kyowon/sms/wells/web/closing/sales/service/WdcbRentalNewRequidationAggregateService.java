package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbRentalNewRequidationAggregateDto;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbRentalNewRequidationAggregateMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WdcbRentalNewRequidationAggregateService {
    private final WdcbRentalNewRequidationAggregateMapper mapper;

    public List<WdcbRentalNewRequidationAggregateDto.SearchRes> getAggregates(
        WdcbRentalNewRequidationAggregateDto.SearchReq dto
    ) {
        return mapper.selectAggregates(dto);
    }

    public List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> getRentalNewRequidations(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    ) {
        List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> res = new ArrayList<>();
        switch (dto.divCd()) {
            // 1. 무료제험
            case "10" -> res = this.mapper
                .selectFreeExperiences(dto);
            // 2. 렌탈신규
            case "20" -> res = this.mapper
                .selectRentalNews(dto);
            // 3. 제품교체
            case "30" -> res = this.mapper
                .selectProductChanges(dto);
            // 4. 렌탈철회
            case "40", "50" -> {
                if ("2".equals(dto.divDtlCd())) {
                    res = this.mapper.selectRentalWithdrawalFreeExperiences(dto);
                } else {
                    res = this.mapper.selectRentalWithdrawals(dto);
                }
            }
            // 6. R급취소
            case "60" -> res = this.mapper
                .selectRGradeCancels(dto);
            // 7. 직권해지
            case "70" -> res = this.mapper
                .selectAuthorityAuthorityResigns(dto);

            default -> throw new BizException("MSG_ALT_IT_NOT_EXIST", "MSG_TXT_DIV_CD");
        }
        return res;
    }
}
