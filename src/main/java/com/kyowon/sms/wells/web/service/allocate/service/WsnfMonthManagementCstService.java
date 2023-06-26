package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.service.allocate.converter.WsnfMonthManagementCstConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsnfMonthManagementCstDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsnfMonthManagementCstDvo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnfMonthManagementCstService {

    private final BatchCallService batchCallService;

    private final WsnfMonthManagementCstConverter converter;

    @Transactional
    public int createMonthManagementCst(WsnfMonthManagementCstDto.CreateReq dto) throws Exception {
        WsnfMonthManagementCstDvo dvo = converter.mapSaveProcessReqToDvo(dto);

        BatchCallReqDvo batchDvo = new BatchCallReqDvo();
        Map<String, String> params = new HashMap<String, String>();

        batchDvo.setJobKey("WSM_SN_OA0002");

        params.put("mngtYm", dvo.getMngtYm());
        params.put("createTarget", dvo.getCreateTarget());

        batchDvo.setParams(params);
        String runId = batchCallService.runJob(batchDvo);

        log.info("[WsnfMonthManagementCstService.createMonthManagementCst] Batch Run Id ::: " + runId);

        return 1;
    }

}
