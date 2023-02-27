package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaEgerAllowanceConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaEgerAllowanceDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaEgerAllowanceMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 엔지니어 실적 집계
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfeaEgerAllowanceService {

    private final WfeaEgerAllowanceMapper mapper;

    private final WfeaEgerAllowanceConverter converter;

    /**
     * 엔지니어 실적 집계
     * @param dto : {}
     * @return 처리결과
     */
    @Transactional
    public int saveEgerPerformances(WfeaEgerAllowanceDto.SaveReq dto) {
        // TODO: 직책유형에 따라 쿼리 분기
        int processCnt = 0;

        WfeaEgerAllowanceDvo dvo = converter.mapSaveReqToWfeaEgerAllowanceDvo(dto);

        // 기 생성된 실적 삭제
        mapper.deleteEgerPerformances(dvo);

        // 실적 집계
        processCnt = mapper.insertEgerPerformances(dvo);
        BizAssert.isTrue(processCnt > 0, "MSG_ALT_AGRG_FAIL");

        return processCnt;
    }

}
