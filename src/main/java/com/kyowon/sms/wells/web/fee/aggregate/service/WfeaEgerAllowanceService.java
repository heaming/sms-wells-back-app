package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaEgerAllowanceDto;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
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

    private final BatchCallService batchCallService;

    /**
     * 엔지니어 실적 집계
     * @param dto : {}
     * @return 처리결과
     */
    @Transactional
    public String saveEgerPerformances(WfeaEgerAllowanceDto.SaveReq dto) throws Exception {
        // 해당 월의 확정 자료 여부 체크
        /*int count = mapper.selectEngineerPerformanceCount(dto);
        BizAssert.isTrue(count == 0, "MSG_ALT_BF_CNFM_CONF_PERF"); //이미 확정되어 실적 생성이 불가합니다.*/

        // 배치 dvo 생성
        BatchCallReqDvo batchCallReqDvo = new BatchCallReqDvo();

        // 배치 parameter
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        Map<String, String> params = new HashMap<String, String>();
        params.put("baseYm", dto.perfYm());
        // 배치로그에 배치 실행한 사람의 정보를 넣음
        params.put("sessionDeptId", session.getDepartmentId());
        params.put("sessionUserId", session.getEmployeeIDNumber());

        batchCallReqDvo.setJobKey("WSM_FE_OA0001");
        batchCallReqDvo.setParams(params);

        String runId = batchCallService.runJob(batchCallReqDvo);
        BizAssert.isTrue(StringUtils.isNotEmpty(runId), "MSG_ALT_SVE_ERR");

        String jobStatus;
        while (true) {
            Thread.sleep(2000);
            jobStatus = batchCallService.getLastestJobStatus(runId);
            if (StringUtils.equals(jobStatus, "Ended OK") || StringUtils.equals(jobStatus, "Ended Not OK")) {
                break;
            }
        }

        return jobStatus;
    }

}
