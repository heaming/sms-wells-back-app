package com.kyowon.sms.wells.web.service.adrwork.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.service.adrwork.converter.WsnfMonthManagementCstConverter;
import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfMonthManagementCstDto;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfMonthManagementCstDvo;
import com.kyowon.sms.wells.web.service.adrwork.mapper.WsnfMonthManagementCstMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnfMonthManagementCstService {

    private final BatchCallService batchCallService;

    private final WsnfMonthManagementCstConverter converter;

    private final WsnfMonthManagementCstMapper mapper;

    @Transactional
    public int createMonthManagementCst(WsnfMonthManagementCstDto.CreateReq dto) throws Exception {
        //FrontEnd Validation을 넘길 경우 강력한 메세지로 대응
        if(DateUtil.getDays(DateUtil.getNowDayString().substring(0, 6) + "01", dto.mngtYm() + "01") < 0){
            throw new BizException("MSG_ALT_ABNORMAL_ACCESS"); //비정상적인 접근입니다. 관리자에 문의해주세요.
        }

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

    @Transactional
    public int removeMonthManagementCst(WsnfMonthManagementCstDto.RemoveReq dto) throws Exception {
        //FrontEnd Validation을 넘길 경우 강력한 메세지로 대응
        if(DateUtil.getDays(DateUtil.getNowDayString().substring(0, 6) + "01", dto.mngtYm() + "01") < 0){
            throw new BizException("MSG_ALT_ABNORMAL_ACCESS"); //비정상적인 접근입니다. 관리자에 문의해주세요.
        }

        return mapper.deleteMonthManagementCst(dto);
    }

}
