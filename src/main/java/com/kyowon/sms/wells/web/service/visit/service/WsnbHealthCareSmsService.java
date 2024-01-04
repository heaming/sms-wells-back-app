package com.kyowon.sms.wells.web.service.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
//import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sflex.common.system.service.UrlShortenerService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbHealthCareSmsDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbHealthCareSmsMapper;
import com.kyowon.sms.wells.web.service.zcommon.constants.SendTemplateConst;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    //private final SmsMessageService smsMessageService;
    private final KakaoMessageService kakaoMessageService;
    private final UrlShortenerService urlService;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    /**
     * 안마의자, 웰스팜, 매트리스 등 설치 후 건강케어 고객에게 알림톡으로 발송한다.
     * 다음날 10시 일괄 발송
     *
     * @see 'SP_INSERT_HEALTHCARE_BIZTALK_SEND'
     * @return 변경 개수
     */
    public int sendHealthCareSms(Map<String, Object> jobParam) throws Exception {
        //final AtomicInteger updateCount = new AtomicInteger();
        List<WsnbHealthCareSmsDvo> rows = mapper.selectHealthCareSms();
        final Map<String, Object> paramMap = new HashMap<>();
        final String baseUrl = this.getBaseUrl();
        final String callback = SendTemplateConst.CALL_BACK_4113;
        String templateId = "";
        String templateCode = "";
        for (WsnbHealthCareSmsDvo row : rows) {
            String yn = "";
            String pifyn = StringUtil.nvl2(row.getPifThpOfrAgYn(), "N");
            String spmtyn = StringUtil.nvl2(row.getSpmtCstSvUAgYn(), "N");

            if(pifyn.equals("Y") && spmtyn.equals("Y")) yn = "Y";
            else yn = "N";

            // 오즈리포트 호출 url
            String url1 = "/api/v1/anonymous/sms/wells/service/healthcare-application-agreement/report/"
                + row.getCntrNo() + "-" + row.getCntrSn();

            String url2 = yn.equals("Y")
                ? "http://html.kyowon.co.kr/wells/wells_health/index.html" // WELLS18287
                : "https://m.kyowonwells.com:4440/voucher/index.html"; // WELLS18286

            paramMap.clear();
            paramMap.put("cstFnm", row.getAgpNm());
            //paramMap.put("cntrNo", row.getCntrNo());
            paramMap.put("callback", callback);
            paramMap.put("url1", baseUrl + urlService.getShortedUrl(url1));
            paramMap.put("url2", url2);

            templateId = yn.equals("Y") ? SendTemplateConst.TMP_SNB_WELLS18287 : SendTemplateConst.TMP_SNB_WELLS18286;
            templateCode = yn.equals("Y") ? SendTemplateConst.WELLS18287 : SendTemplateConst.WELLS18286;

            /* 고객에게 수락취소 문자발송 */
            //            smsMessageService.sendMessage(
            //                SmsSendReqDvo.withTemplateId()
            //                    .templateId(templateId)
            //                    .templateParamMap(paramMap)
            //                    .destInfo(
            //                        row.getAgpNm().concat("^")
            //                            .concat(row.getCralLocaraTno() + row.getMexnoEncr() + row.getCralIdvTno())
            //                    )
            //                    .build()
            //            );

            kakaoMessageService.sendMessage(
                KakaoSendReqDvo.withTemplateCode()
                    .templateCode(templateCode)
                    .templateParamMap(paramMap)
                    .destInfo(
                        row.getAgpNm().concat("^")
                            .concat(row.getCralLocaraTno() + row.getMexnoEncr() + row.getCralIdvTno())
                    )
                    .callback(callback)
                    .userId("admin")
                    .build()
            );

            // 10.1.60.29:5521/KWDEV 에는 없고 운영에만 존재하는 소스
            // ------------------------------------------------------
            //  /* 고객에게 수락취소 문자발송 */
            //  PR_BIZTALK_SEND('AS01'                                     /*--p_SMS_SYST_CD,       SMS 발송 시스템 코드 (SELECT * FROM TB_SMS_DPMS_SYST_M 참조)*/
            //                , 'S'                                        /*--p_MSG_QT_DIV_CD      소량, 대량 구분(S:소량, B:대량) */
            //                , 'N'                                        /*--p_ADVR_YN            광고 여부(Y:광고, N:일반) */
            //                , ''                                         /*--p_RFSL_GRP_CD        SMS수신 거부 그룹 코드(광고일경우) (SELECT * FROM TB_SMS_GRP_M 참조)*/
            //                , TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')        /*--p_SEND_DATE          발송 희망 시각: YYYYMMDDHHMMSS */
            //                , V_MSG
            //                , V_MSG
            //                , 'Y'                                        /*--p_USE_SMS_FAIL_BACK  실패시 문자 발송 여부 */
            //                , C1.HP_NO                                   /*--p_RECIPIENT_NUM      수신자 전화 번호      */
            //                , '10b58e0f60460756ec4818b73f7b7d430f2d2778' /*--p_SENDER_KEY         카카오톡 알림톡 발신 프로필 키 */
            //                , V_KAKAO_TMP_ID                             /*--p_TEMPLATE_CODE      카카오톡 알림톡 메시지 유형 템플릿 코드*/
            //                , '20974'                                    /*--p_USER_ID            회원 ID */
            //                , '[교원 웰스]'                              /*--p_SUBJECT            제목 (메시지 구분용. SMS의 경우는 실제 발송은 되지 않음) */
            //                , '15884113'                                 /*--p_CALLBACK           콜백번호(회신번호) */
            //                , 'wellssms'                                 /*--p_CDR_ID             과금 ID   */
            //                , ''                                         /*--p_RESERVED1          여분필드1 (임의로 사용가능) */
            //                , ''                                         /*--p_RESERVED2          여분필드2 (임의로 사용가능) */
            //                , ''                                         /*--p_RESERVED3          여분필드3 (임의로 사용가능) */
            //                , ''                                         /*--p_RESERVED4          여분필드4 (임의로 사용가능) */
            //                , ''                                         /*--p_RESERVED5          여분필드5 (임의로 사용가능) */
            //                , ''                                         /*--p_RESERVED6          여분필드6 (임의로 사용가능) */
            //                , p_RESULT);                                 /*--p_RESULT             결과 리턴*/
            //            updateCount.addAndGet(
            //
            //            );
        }
        //        return updateCount.get();

        //        kakaoMessageService.sendMessage(
        //            KakaoSendReqDvo.withTemplateCode()
        //                .templateCode(templateCode)
        //                .templateParamMap(paramMap)
        //                .destInfo(
        //                    row.getAgpNm().concat("^")
        //                        .concat(row.getCralLocaraTno() + row.getMexnoEncr() + row + row.getCralIdvTno())
        //                )
        //                .callback(callback)
        //                .userId("admin")
        //                .build()
        //        );
        return 1;
    }

    /**
     * base Url 생성
     */
    public String getBaseUrl() {
        String baseUrl = "";

        switch (activeProfile) {
            case SnServiceConst.SERVER_PROFILE_LOCAL, SnServiceConst.SERVER_PROFILE_DEV -> {
                baseUrl += SnServiceConst.BASE_URL_DEV;
            }
            case SnServiceConst.SERVER_PROFILE_QA -> {
                baseUrl += SnServiceConst.BASE_URL_QA;
            }
            case SnServiceConst.SERVER_PROFILE_PRD -> {
                baseUrl += SnServiceConst.BASE_URL_PRD;
            }
            default -> baseUrl += SnServiceConst.BASE_URL_PRD;
        }

        return baseUrl;
    }

}
