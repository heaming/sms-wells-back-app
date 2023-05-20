package com.kyowon.sms.wells.web.service.visit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0039 IoT 제품 설치 완료 후, 익일 오후 3시에 알림톡 발송
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.01.11
 */
@Setter
@Getter
public class WsnbIotProductIstBiztalkDvo {

    String wkExcnDt;
    String istllKnm;
    String cralLocaraTno;
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String pdNm;
    String subject;
    String tCode;
    String nowDate;
    String sendDate;
    String userId;
    String callback;

}
