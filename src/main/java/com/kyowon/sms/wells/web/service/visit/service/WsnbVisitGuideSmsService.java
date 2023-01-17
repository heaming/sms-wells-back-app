package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitGuideSmsDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbVisitGuideSmsMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0032 방문일 D-1 전에 방문 안내 문자 발송
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.01.06
 */
@Service
@RequiredArgsConstructor
public class WsnbVisitGuideSmsService {

    private final SmsMessageService smsMessageService;

    private final WsnbVisitGuideSmsMapper mapper;

    public int sendVisitGuideSms() throws Exception {
        List<WsnbVisitGuideSmsDvo> dvos = this.mapper.selectCustomers();

        int processCount = 0;

        for (WsnbVisitGuideSmsDvo dvo : dvos) {
            SmsSendReqDvo smsDvo = SmsSendReqDvo.withTemplateId()
                .templateId(dvo.getTemplateId())
                .templateParamMap(
                    Map.of(
                        "vstCnfmdt", dvo.getVstCnfmdt(),
                        "vstCnfmHh", dvo.getVstCnfmHh(),
                        "istllKnm", dvo.getIstllKnm(),
                        "pdNm", dvo.getPdNm()
                    )
                )
                .destInfo(dvo.getUserId() + "^" + dvo.getDestInfo())
                .callback(dvo.getCallback())
                .build();

            processCount += smsMessageService.sendMessage(smsDvo);
        }

        return processCount;
    }

}
