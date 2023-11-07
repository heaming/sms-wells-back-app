package com.kyowon.sms.wells.web.fee.simulation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchCanNincRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchDetailRes;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchRes;
import com.kyowon.sms.wells.web.fee.simulation.mapper.WfefSellFeeEtPerfMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 예상 실적 조회 (판매)
 * </pre>
 *
 * @author LEE SUNHO
 * @since 2023.10.10
 */
@Service
@RequiredArgsConstructor
public class WfefSellFeeEtPerfService {

    private final WfefSellFeeEtPerfMapper mapper;

    public List<SearchRes> getSellFeeEtPerfs(SearchReq dto) {
        /* 건수&실적 , 계정순증 */
        return mapper.selectSellFeeEtPerfs(dto);
    }

    public List<SearchDetailRes> getSellFeeEtPerfDetails(SearchReq dto) {
        /* 판매상세 */
        return mapper.selectSellFeeEtPerfDetails(dto);
    }

    public List<SearchCanNincRes> getSellFeeEtPerfCanNincs(SearchReq dto) {
        if ("01".equals(dto.nincDv())) {
            /* 순증상세 - 전월취소 */
            return mapper.selectSellFeeEtPerfCans(dto);
        } else {
            /* 순증상세 - 신규판매 */
            return mapper.selectSellFeeEtPerfNincs(dto);
        }
    }
}
