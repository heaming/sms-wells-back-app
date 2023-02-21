package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaOgNetOrderConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOgNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaOgNetOrderDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaOgNetOrderMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 조직별 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfeaOgNetOrderService {

    private final WfeaOgNetOrderMapper mapper;

    private final WfeaOgNetOrderConverter converter;

    /**
     * 조직별 실적 집계
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int saveOrganizationAggregates(WfeaOgNetOrderDto.SaveOgNetOrderReq dto) {
        int processCnt = 1;
        return processCnt;
    }

    /**
     * BS 실적 집계
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int saveBsPerformances(WfeaOgNetOrderDto.SaveBsReq dto) {
        int processCnt = 1;

        WfeaOgNetOrderDvo dvo = converter.mapSaveBsReqToWfeaOgNetOrderDvo(dto);

        mapper.deleteBsPerformances(dvo);
        processCnt = mapper.insertBsPerformances(dvo);

        BizAssert.isTrue(processCnt < 0, "MSG_ALT_AGRG_FAIL");

        return processCnt;
    }

    /**
     * 조직별 실적 확정
     * @param dto
     * @return 처리결과
     */
    @Transactional
    public int editOrganizationAggregates(WfeaOgNetOrderDto.SaveOgNetOrderReq dto) {
        int processCnt = 1;
        return processCnt;
    }
}
