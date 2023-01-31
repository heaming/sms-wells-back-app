package com.kyowon.sms.wells.web.service.visit.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sflex.common.message.dvo.KakaoSendReqDvo;
import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.KakaoMessageService;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.service.visit.converter.WsnbInstallationHpcallFwConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallationHpcallFwDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallationHpcallDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbInstallationHpcallFwMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-S-0080 설치, A/S 해피콜 발송(리뉴얼)
 * </pre>
 *
 * @author yeonghwa.cheon 천영화
 * @since 2023.01.26
 */
@Service
@RequiredArgsConstructor
public class WsnbInstallationHpcallFwService {

    private final KakaoMessageService kakaoMessageService;
    private final SmsMessageService smsMessageService;
    private final WsnbInstallationHpcallFwMapper mapper;
    private final WsnbInstallationHpcallFwConverter converter;

    public int sendInstallationHpcallFws() throws Exception {
        List<WsnbInstallationHpcallDvo> dvos = mapper.selectCustomers();

        int processCount = 0;

        SimpleDateFormat toDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat dtFormatKr = new SimpleDateFormat("yyyy년 MM월 dd일");

        for (WsnbInstallationHpcallDvo dvo : dvos) {

            SearchReq searchReq = converter.mapWsnbInstallationHpcallDvoToSearchReq(dvo);

            String sendDateTime = "";

            /* TODO : pk중복관련 검증 로직 추가되야함 . (테이블 정리 덜 됨) */
            if (mapper.selectCountCustomerByPk(searchReq) != 0) {
                break;
            }
            /* 차기방문일자 등록했는지 체크 */
            String vstPromDt = mapper.selectVstPromDt(dvo);
            Date vstPromDate = toDate.parse(vstPromDt);

            /* 즉시 발송인지 예약발송인지 */
            int nowTime = Integer.parseInt(DateUtil.getNowTimeString());

            if (nowTime >= 210000 && nowTime <= 240000) {
                sendDateTime = DateUtil.addDays(DateUtil.getNowDayString(), 1) + "100000";
            } else if (nowTime >= 0 && nowTime <= 80000) {
                sendDateTime = DateUtil.getNowDayString() + "100000";
            }

            /* 파라미터 암호화 (GET 방식 파라미터 특수문자(&, +) 잘림 방지 */
            String paraGb;
            if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "1")) {
                paraGb = "1";
            } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "34")) {
                paraGb = "4";
            } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3")) {
                paraGb = "3";
            } else {
                paraGb = "3";

            }
            String paraVal = paraGb + "|" + dvo.getSellTpCd() + "|" + dvo.getWkExcnDt() + "|"
                + StringUtils.substring(dvo.getCntrNo(), 1, 4) + "|"
                + StringUtils.substring(dvo.getCntrNo(), 5) + "|"
                + StringUtils.substring(dvo.getCstSvAsnNo(), 0, 0) + "|"
                + StringUtils.substring(dvo.getCstSvAsnNo(), 1, 8) + "|"
                + StringUtils.substring(dvo.getCstSvAsnNo(), 13);
            paraVal = StringUtils.replace(paraVal, "+", "%2B");
            paraVal = StringUtils.replace(paraVal, "&", "%26");

            /* 템플릿 구분 처리 */
            String templateCode = "";
            if (StringUtils.startsWith(dvo.getSellTpCd(), "1")) {
                if (vstPromDt != null) {
                    /* 차기 방문 약속일이 있는 경우 */
                    if (Integer.parseInt(DateUtil.getNowDayString()) < 20220520) {
                        templateCode = "wells17903";
                    } else if (dvo.getQrGb() == "1") {
                        templateCode = "Wells18250";
                    } else if (dvo.getQrGb() == "2") {
                        templateCode = "Wells18237";
                    } else {
                        templateCode = "Wells18250";
                    }
                } else {
                    /* 차기 방문 약속일 없는 경우 */
                    if (Integer.parseInt(DateUtil.getNowDayString()) < 20220520) {
                        templateCode = "wells51877";
                    } else if (dvo.getQrGb() == "1") {
                        templateCode = "Wells18251";
                    } else if (dvo.getQrGb() == "2") {
                        templateCode = "Wells18236";
                    } else {
                        templateCode = "Wells18251";
                    }
                }
            } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "34")) {
                templateCode = "wells51879";
            } else if (StringUtils.startsWith(dvo.getSvBizDclsfCd(), "3")) {
                if (Integer.parseInt(DateUtil.getNowDayString()) < 20220520) {
                    templateCode = "wells51878";
                } else if (dvo.getQrGb() == "1") {
                    templateCode = "Wells18249";
                } else if (dvo.getQrGb() == "2") {
                    templateCode = "Wells18238";
                } else {
                    templateCode = "Wells18249";
                }
            }

            /* wells399 안내 알림톡 처리는 아직 미정. */

            if (templateCode != "") {
                String[] arr = {"Wells18236", "Wells18237", "Wells18238", "Wells18249", "Wells18250", "Wells18251"};
                Set<String> btnTcodes = new HashSet<String>(Arrays.asList(arr));
                int success = 0;
                /* 템플릿code따라 차기방문일자 포맷 세팅(변경할수있음) */

                if (templateCode == "Wells17903") {
                    vstPromDt = dtFormat.format(vstPromDate); // yyyy/mm/dd
                } else {
                    vstPromDt = dtFormatKr.format(vstPromDate); // yyyy년 mm월 dd일
                }

                Map<String, Object> paramMap = new HashMap<>();
                String hp = dvo.getCralLocaraTno() + dvo.getMexnoEncr() + dvo.getCralIdvTno();

                paramMap.put("cstNm", dvo.getRcgvpKnm());
                paramMap.put("pdNm", dvo.getPrdtNm());
                paramMap.put("vstDt", dvo.getWkExcnDt());
                paramMap.put("hpyUrl", "http://kiwi-m.kyowon.co.kr/KIWI-W/nosession_hpy.do?para=");
                paramMap.put("paraVal", paraVal);
                paramMap.put("hpyRejectUrl", "http://kiwi-m.kyowon.co.kr/KIWI-W/nosession_hpy_reject.do?para=");
                paramMap.put("bcNo", dvo.getBcNo());
                paramMap.put("nVstDt", vstPromDt);
                paramMap.put("csmrYr", StringUtils.substring(dvo.getCntrNo(), 1, 4));
                paramMap.put("csmrCd", StringUtils.substring(dvo.getCntrNo(), 5));
                if (btnTcodes.contains(templateCode)) {
                    /* 버튼형 세팅해주고 알림톡 호출. */
                    KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
                        .templateCode(templateCode)
                        .templateParamMap(paramMap)
                        .destInfo(dvo.getRcgvpKnm() + "^" + hp)
                        .callback("15884113")
                        .build();
                    success = kakaoMessageService.sendMessage(kakaoSendReqDvo);

                    /* 버튼형 알림톡 발송 실패시 sms 발송 */
                    if (success == 0) {
                        String templateId = "TMP_SNB_S" + StringUtils.upperCase(templateCode);
                        SmsSendReqDvo smsSendReqDvo;
                        if (sendDateTime != "") {
                            smsSendReqDvo = SmsSendReqDvo.withTemplateId()
                                .templateId(templateId)
                                .templateParamMap(paramMap)
                                .destInfo(dvo.getRcgvpKnm() + "^" + hp)
                                .callback("15884113")
                                .sendDatetime(sendDateTime)
                                .build();
                        } else {
                            smsSendReqDvo = SmsSendReqDvo.withTemplateId()
                                .templateId(templateId)
                                .templateParamMap(paramMap)
                                .destInfo(dvo.getRcgvpKnm() + "^" + hp)
                                .callback("15884113")
                                .build();
                        }
                        success = smsMessageService.sendMessage(smsSendReqDvo);
                    }
                    processCount += success;
                } else {
                    KakaoSendReqDvo kakaoSendReqDvo = KakaoSendReqDvo.withTemplateCode()
                        .templateCode(templateCode)
                        .templateParamMap(paramMap)
                        .destInfo(dvo.getRcgvpKnm() + "^" + hp)
                        .callback("15884113")
                        .build();
                    processCount += kakaoMessageService.sendMessage(kakaoSendReqDvo);
                }

            }
        }

        return processCount;
    }
}
