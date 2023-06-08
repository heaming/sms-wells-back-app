package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaEgerAllowanceConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaEgerAllowanceDto;
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

    private final BatchCallService batchCallService;

    /**
     * 엔지니어 실적 집계
     * @param dto : {}
     * @return 처리결과
     */
    @Transactional
    public String saveEgerPerformances(WfeaEgerAllowanceDto.SaveReq dto) throws Exception {
        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        Map<String, String> params = new HashMap<String, String>();
        params.put("baseYm", dto.perfYm());

        batchCallReqDvo.setJobKey("WSM_FE_OA0001");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        return StringUtils.isNotBlank(runId) ? "S" : "E";
    }

}
