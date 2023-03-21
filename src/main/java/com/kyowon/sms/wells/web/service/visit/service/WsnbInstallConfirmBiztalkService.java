package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallConfirmDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallConfirmBiztalkMapper;
import com.sds.sflex.system.config.core.service.ConfigurationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0054 설치 확인서 알림톡 발송
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.01.12
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbInstallConfirmBiztalkService {
    private final KakaoMessageService kakaoMessageService;
    private WsnbInstallConfirmBiztalkMapper mapper;
    private final ConfigurationService configurationService;

    public int sendInstallConfirmBiztalks() throws Exception {
        List<WsnbInstallConfirmDvo> dvos = mapper.selectInstallConfirms();

        int processCount = 0;
        String callbackValue = configurationService.getConfigurationValue("CFG_SNB_WELLS_CST_CNR_TNO");

        for (WsnbInstallConfirmDvo dvo : dvos) {
            Map<String, Object> paramMap = new HashMap<>();
            String hp = dvo.getCralLocaraTno() + dvo.getMexnoEncr() + dvo.getCralIdvTno();

            paramMap.put("cstKnm", dvo.getCstKnm());
            paramMap.put("pdNm", dvo.getPdNm());
            paramMap.put("vstFshDt", dvo.getVstFshDt());
            paramMap.put(
                "url",
                "http://kiwi-m.kyowon.co.kr/KIWI-M/upload_file/upload_file/instcfrm/" + dvo.getCntrNo() + ".jpg"
            );

            KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
                .templateCode("Wells18133")
                .templateParamMap(paramMap)
                .destInfo(dvo.getCstKnm() + "^" + hp)
                .callback(callbackValue)
                .build();

            processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);
        }

        return processCount;
    }
}
