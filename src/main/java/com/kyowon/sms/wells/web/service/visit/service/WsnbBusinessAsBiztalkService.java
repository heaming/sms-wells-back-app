package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbBusinessAsBiztalkDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbBusinessAsBiztalkMapper;
import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.system.config.core.service.ConfigurationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0053 고객센터에서 접수한 아웃소싱 업체 여러건 AS건에 대해 알림톡 발송
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbBusinessAsBiztalkService {
    private final KakaoMessageService kakaoMessageService;
    private WsnbBusinessAsBiztalkMapper mapper;
    private final ConfigurationService configurationService;

    public int sendBusinessAsBiztalks() throws Exception {
        /* mexnoEncr null 체크용 암호화 값 */
        String mexnoEncr = DbEncUtil.enc("    ");

        List<WsnbBusinessAsBiztalkDvo> targets = mapper.selectBiztalkTargets(mexnoEncr);

        int processCount = 0;
        String callbackValue = configurationService.getConfigurationValue("CFG_SNB_WELLS_CST_CNR_TNO");

        for (WsnbBusinessAsBiztalkDvo target : targets) {

            /* 금일 발송 내역 유무 */
            if (mapper.selectCountBiztalkItemization() != 0) {
                break;
            }
            WsnbBusinessAsBiztalkDvo dvo = mapper.selectBiztalkTarget(target);
            Map<String, Object> paramMap = new HashMap<>();
            String hp = dvo.getCralLocaraTno() + dvo.getMexnoEncr() + dvo.getCralIdvTno();

            paramMap.put("cstKnm", dvo.getCstKnm());
            paramMap.put("pdNm", dvo.getPdNm());
            paramMap.put("vstDate", dvo.getVstDate());
            paramMap.put("vstTime", dvo.getVstTime());
            paramMap.put("vstTypeNm", dvo.getVstTypeNm());

            KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
                .templateCode("wells17918")
                .templateParamMap(paramMap)
                .destInfo(dvo.getCstKnm() + "^" + hp)
                .callback(callbackValue)
                .build();

            processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);
        }

        return processCount;
    }
}
