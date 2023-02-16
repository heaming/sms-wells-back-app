package com.kyowon.sms.wells.web.withdrawal.zcommon.constants;

import com.sds.sflex.system.config.constant.CommConst;

public class WdWithdrawalConst {

    public static final String REST_URL_V1 = CommConst.REST_URL_V1 + "/sms/wells/withdrawal";

    public static final String REST_URL_STANDARD = REST_URL_V1 + "/standard"; //수납입출금-기준정보

    public static final String REST_URL_IDVRVE = REST_URL_V1 + "/idvrve"; //수납입출금-개별수납
}
