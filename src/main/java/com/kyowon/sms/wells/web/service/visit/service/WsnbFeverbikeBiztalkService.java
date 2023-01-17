package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbFeverbikeBiztalkDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbFeverbikeBiztalkMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class WsnbFeverbikeBiztalkService {

    private final WsnbFeverbikeBiztalkMapper mapper;
    private final KakaoMessageService kakaoMessageService;

    /**
     * 피버 바이크 플러스 온라인 강의 무료 구독 신청완료 후 고객에게 알림톡으로 발송한다.
     *
     * @return 변경 개수
     */
    @Transactional
    public int sendFeverbikeBiztalk() throws Exception {
        final AtomicInteger updateCount = new AtomicInteger();
        final List<WsnbFeverbikeBiztalkDvo> rows = mapper.selectFeverbikeBiztalk();
        final Map<String, Object> paramMap = new HashMap<>();
        for (WsnbFeverbikeBiztalkDvo x : rows) {
            paramMap.clear();
            paramMap.put("cntrCstNm", x.getCntrCstNm());
            paramMap.put("cntrNo", x.getCntrNo());
            String yn = x.getPifThpOfrAgYn();
            if (x.getPifThpOfrAgYn() == null)
                yn = "N";
            kakaoMessageService.sendMessage(
                KakaoSendReqDvo.withTemplateCode()
                    .templateCode(yn.equals("Y") ? "Wells18207" : "Wells18208")
                    .templateParamMap(paramMap)
                    .destInfo(x.getCntrCstNm().concat("^").concat(x.getMpno())).callback("15884113")
                    .build()
            );
            updateCount.addAndGet(mapper.updateFeverbikeBiztalk(x.getCntrNo()));
        }
        return updateCount.get();
    }

}
