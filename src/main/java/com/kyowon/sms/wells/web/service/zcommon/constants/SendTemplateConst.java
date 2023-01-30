package com.kyowon.sms.wells.web.service.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

import java.util.Locale;

/**
* sms, kakao, push, mail 발송 시 사용하는
* 템플릿 코드, 발신서식ID
*
* */
public class SendTemplateConst {
    private SendTemplateConst() {}
    public static final String CALL_BACK_4113 = "1588-4113";
    public static final String WELLS18286 = "Wells18286";
    public static final String WELLS18287 = "Wells18287";
    public static final String TMP_SNB_WELLS18286 = "TMP_SNB_".concat(WELLS18286.toUpperCase(Locale.ROOT));
    public static final String TMP_SNB_WELLS18287 = "TMP_SNB_".concat(WELLS18287.toUpperCase(Locale.ROOT));
}
