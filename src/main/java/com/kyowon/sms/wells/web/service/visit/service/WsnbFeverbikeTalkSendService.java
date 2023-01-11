package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbFeverbikeTalkSendConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFeverbikeTalkSendDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbFeverbikeTalkSendMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * <pre>
 * W-SV-S-0037 피버바이크 알림톡 발송
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2023-01-11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbFeverbikeTalkSendService {

    private final WsnbFeverbikeTalkSendMapper mapper;
    private final WsnbFeverbikeTalkSendConverter converter;
    private final KakaoMessageService kakaoMessageService;

    /**
     * 피버 바이크 플러스 온라인 강의 무료 구독 신청완료 후 고객에게 알림톡으로 발송한다.
     *
     * @return 변경 개수
     */
    public int saveFeverbikeTalkSend() {
        final AtomicInteger updateCount = new AtomicInteger();
        final List<SearchRes> rows = converter.mapAllDvoToRes(mapper.selectFeverbikeTalkSendTarget());
        rows.forEach(x -> {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("cntrCstNm", x.cntrCstNm());
            paramMap.put("cntrNo", x.cntrNo());
            KakaoSendReqDvo dvo;
            if (x.pifThpOfrAgYn().equals("Y")) {
                dvo = KakaoSendReqDvo.withTemplateCode().templateCode("FEVERBIKE_APLC_Y").build();
            } else {
                dvo = KakaoSendReqDvo.withTemplateCode().templateCode("FEVERBIKE_APLC_N").build();
            }
            dvo.setTemplateParamMap(paramMap);
            dvo.setDestInfo(x.cntrCstNm() + "^" + x.mpno());
            dvo.setCallback("15884113");

            try {
                updateCount.addAndGet(kakaoMessageService.sendMessage(dvo));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return updateCount.get();
    }

}
