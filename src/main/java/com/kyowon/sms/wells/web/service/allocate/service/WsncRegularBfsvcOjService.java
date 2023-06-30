package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.BatchCallReqDvo;
import com.kyowon.sflex.common.common.service.BatchCallService;
import com.kyowon.sms.wells.web.service.allocate.converter.WsncRegularBfsvcOjConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcOjDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcOjDvo;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsncRegularBfsvcOjService {

    private final WsncSpecCustAsnService wsncSpecCustAsnService;    //[W-SV-S-0027] 특정고객 배정 인서트

    private final WsncSpecCustPlanMatService wsncSpecCustPlanMatService;    //[W-SV-S-0029] 특정고객 예정자재 인서트

    private final WsncSpecCustMngrAsnService wsncSpecCustMngrAsnService;    //[W-SV-S-0031] 특정고객 담당자 지정 BS 오더 생성

    private final BatchCallService batchCallService;

    private final WsncRegularBfsvcOjConverter converter;

    public int createRegularBfsvcOj(WsncRegularBfsvcOjDto.CreateReq dto) throws Exception {
//        return createRegularBfsvcOj(converter.mapSaveProcessReqToDvo(dto));
        WsncRegularBfsvcOjDvo dvo = converter.mapSaveProcessReqToDvo(dto);

        BatchCallReqDvo batchDvo = new BatchCallReqDvo();
        Map<String, String> params = new HashMap<String, String>();

        batchDvo.setJobKey("WSM_SN_OA0001");

        params.put("asnOjYm", dvo.getAsnOjYm());
        params.put("createTarget", dvo.getCreateTarget());
        params.put("prtnrNo", "");  //현재는 필요없는 parameter

        batchDvo.setParams(params);
        String runId = batchCallService.runJob(batchDvo);

        log.info("[WsncRegularBfsvcOjService.createRegularBfsvcOj] Batch Run Id ::: " + runId);

        return 1;
    }

    @Transactional
    public int createRegularBfsvcOj(WsncRegularBfsvcOjDvo dvo) throws Exception {

        switch (dvo.getCreateTarget()) {
            case "A" -> {
                //Step 1. [W-SV-S-0027] ::: 특정고객 배정 인서트를 수행한다.
                wsncSpecCustAsnService.processSpecCustAsn(converter.mapDvoToSpecCustAsnDvo(dvo));
            }
            case "B" -> {
                //Step 2. [W-SV-S-0029] ::: 특정고객 예정자재 인서트 서비스를 수행한다.
                wsncSpecCustPlanMatService.processSpecCustPlanMat(converter.mapDvoToSpecCustPlanMatDvo(dvo));
            }
            case "H" -> {
                //Step 3. [W-SV-S-0031] ::: 특정고객 담당자 지정 BS 오더 생성 서비스를 수행한다.
                wsncSpecCustMngrAsnService.processSpecCustAsn(converter.mapDvoToSpecCustMngrAsnDvo(dvo));
            }
            default -> throw new BizException("MSG_TXT_ERROR_INFO");    //오류정보
        }
        return 1;
    }

    @Transactional
    public int createRegularBfsvcOj(Map<String, Object> param) throws Exception {
        WsncRegularBfsvcOjDvo dvo = new WsncRegularBfsvcOjDvo();
        dvo.setAsnOjYm(StringUtil.nvl(param.get("PARAM1")));
        dvo.setCreateTarget(StringUtil.nvl(param.get("PARAM2")));

        log.info("[WsncRegularBfsvcOjService.createRegularBfsvcOj] AsnOjYm ::: " + dvo.getAsnOjYm());
        log.info("[WsncRegularBfsvcOjService.createRegularBfsvcOj] CreateTarget ::: " + dvo.getCreateTarget());

        return createRegularBfsvcOj(dvo);
    }
}
