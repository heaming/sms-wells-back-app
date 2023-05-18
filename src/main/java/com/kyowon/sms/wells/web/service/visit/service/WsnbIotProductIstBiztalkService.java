package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sds.sflex.common.utils.StringUtil;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbIotProductIstBiztalkDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIotProductIstBiztalkMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0039 IoT 제품 설치 완료 후, 익일 오후 3시에 알림톡 발송
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.01.11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbIotProductIstBiztalkService {

    private final KakaoMessageService kakaoMessageService;

    private final WsnbIotProductIstBiztalkMapper mapper;

    public int sendIotBiztalks() throws Exception {
        List<WsnbIotProductIstBiztalkDvo> dvos = this.mapper.selectBiztalkTargets();

        int processCount = 0;

        for (WsnbIotProductIstBiztalkDvo dvo : dvos) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("istllKnm", dvo.getIstllKnm());
            paramMap.put("pdNm", dvo.getPdNm());

            KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
                .templateCode(dvo.getTCode())
                .templateParamMap(paramMap)
                .destInfo(
                    dvo.getIstllKnm() + "^"
                        + this.getDestPhoneNo(dvo.getCralLocaraTno(), dvo.getMexnoEncr(), dvo.getCralIdvTno())
                )
                .callback(dvo.getCallback())
                .build();

            processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);
        }

        return processCount;
    }

    private String getDestPhoneNo(String cralLocaraTno, String mexnoEncr, String cralIdvTno) {
        if (StringUtil.isBlank(cralLocaraTno) || StringUtil.isBlank(mexnoEncr) || StringUtil.isBlank(cralIdvTno)) {
            return "";
        }
        return cralLocaraTno + mexnoEncr + cralLocaraTno;
    }

}
