package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0032 방문일 D-1 전에 방문 안내 문자 발송
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.01.06
 */
@Setter
@Getter
public class WsnbVisitGuideSmsDvo {

    String vstCnfmdt;
    String vstCnfmHh;
    String istllKnm;
    String pdNm;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String nowDate;
    String sendDatetime;
    String userId;
    String callback;
    String templateId;
    String subject;

}
