package com.kyowon.sms.wells.web.service.stock.dvo;

import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaMdProdcutOutOfStorageSearchDvo {

    String startDt;

    String endDt;

    String findGb;

    String firstSppGb;

    String selCnt;

    String wkStartDt;

    String wkEndDt;

    String prtnrBzsCd;

    String cntrDtlNo;

    String rcgvpKnm;

    String serialNo;

    String cralLocaraTno;

    @DBEncField
    String mexnoEncr;

    String cralIdvTno;
}
