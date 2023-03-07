package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaNchnFeeConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNchnFeeDto;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNchnFeeDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaNchnFeeMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 신채널 수수료 생성관리 서비스
 * </pre>
 *
 * @author haejin.lee
 * @since 2023-03-02
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfeaNchnFeeService {

    private final WfeaNchnFeeMapper mapper;

    private final WfeaNchnFeeConverter converter;

    /**
     * 엔지니어 실적 집계
     * @param dto : {}
     * @return 처리결과
     */
    @Transactional
    public int aggregateNchnPerformances(WfeaNchnFeeDto.SaveReq dto) {

        int processCnt = 0;

        WfeaNchnFeeDvo dvo = converter.mapSaveReqToWfeaNchnFeeDvo(dto);

        mapper.deleteNchnPerformances(dvo);

        processCnt = mapper.insertNchnPerformances(dvo);
        BizAssert.isTrue(processCnt > 0, "MSG_ALT_AGRG_FAIL");

        return processCnt;
    }

}
