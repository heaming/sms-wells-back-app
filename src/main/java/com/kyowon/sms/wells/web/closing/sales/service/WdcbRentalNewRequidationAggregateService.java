package com.kyowon.sms.wells.web.closing.sales.service;

import static com.kyowon.sms.wells.web.closing.sales.dto.WdcbRentalNewRequidationAggregateDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbRentalNewRequidationAggregateMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 렌탈 신규/철거 집계현황
 * </pre>
 *
 * @author songmi.in
 * @since 2023-08-16
 */
@Service
@RequiredArgsConstructor
public class WdcbRentalNewRequidationAggregateService {
    private final WdcbRentalNewRequidationAggregateMapper mapper;

    /**
      * 렌탈 신규/철거 집계현황 - 집계현황 조회
      * @param dto
      * @return 조회결과
      */
    public List<SearchRes> getAggregates(
        SearchReq dto
    ) {
        return mapper.selectAggregates(dto);
    }

    /**
      * 렌탈 신규/철거 집계현황 - 상세현황 조회
      * @param dto
      * @return 조회결과
      */
    public List<SearchDetailRes> getRentalNewRequidations(
        SearchDetailReq dto
    ) {
        switch (dto.divCd()) {
            // 1. 무료제험
            case "10" -> {
                return mapper.selectFreeExperiences(dto);
            }
            // 2. 렌탈신규
            case "20" -> {
                return mapper.selectRentalNews(dto);
            }
            // 3. 제품교체
            case "30" -> {
                return mapper.selectProductChanges(dto);
            }
            // 4. 렌탈철회
            case "40", "50" -> {
                if ("2".equals(dto.divDtlCd())) {
                    return mapper.selectRentalWithdrawalFreeExperiences(dto);
                } else {
                    return mapper.selectRentalWithdrawals(dto);
                }
            }
            // 6. R급취소
            case "60" -> {
                return mapper.selectRGradeCancels(dto);
            }
            // 7. 직권해지
            case "70" -> {
                return mapper.selectAuthorityAuthorityResigns(dto);
            }

            default -> throw new BizException("MSG_ALT_IT_NOT_EXIST", "MSG_TXT_DIV_CD");
        }
    }
}
