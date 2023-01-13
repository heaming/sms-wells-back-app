package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbHealthCareSmsDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbHealthCareSmsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * <pre>
 * W-SV-S-0038 건강케어 알림톡 발송 , 설치 후 다음날 발송 안마의자, 웰스팜, 매트리스
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2023-01-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbHealthCareSmsService {

    private final WsnbHealthCareSmsMapper mapper;
    private final SmsMessageService smsMessageService;

    /**
     * 안마의자, 웰스팜, 매트리스 등 설치 후 건강케어 고객에게 알림톡으로 발송한다.
     *
     * @return 변경 개수
     */
    public int sendHealthCareSms() throws Exception{
        final AtomicInteger updateCount = new AtomicInteger();
        List<WsnbHealthCareSmsDvo> rows = mapper.selectHealthCareSms();
        for (WsnbHealthCareSmsDvo row : rows) {
            smsMessageService.sendMessage(
                SmsSendReqDvo.builder()
                    .subject("SUBJECT")
                    .msgContent("content")
                    .destInfo(row.getCstNm().concat("^").concat(row.getCphonIdvTno()))
                    .documentId("ID")
                    .build()
            );
        }
        return updateCount.get();
    }

}
