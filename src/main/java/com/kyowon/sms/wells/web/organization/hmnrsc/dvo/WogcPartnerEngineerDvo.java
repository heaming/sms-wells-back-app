package com.kyowon.sms.wells.web.organization.hmnrsc.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WogcPartnerEngineerDvo {

    String ogTpCd;
    String dgr1LevlOgNm;
    String dgr2LevlOgNm;
    String dgr3LevlOgNm;
    String dgr4LevlOgNm;
    String dgr5LevlOgNm;
    String apySeqn;
    String prtnrNo;
    String prtnrKnm;
    String wkGrpCd;
    String wkGrpCdNm;
    String egerRsbCd;
    String egerRsbCdNm;
    String rsbDvCd;
    String rsbDvCdNm;
    String wkcrCd;
    String wkcrCdNm;
    String cntrDt;
    String vlStrtdt;
    String vlEnddt;
    String cralLocaraTno;
    @DBEncField
    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String dtaDlYn;
    String telNumber;

    String rolDvCd;
    String rolDvCdNm;
    String prtnrGdCd;
    String apyStrtDt;
    String apyEnddt;
    String rmkCn;
    String cltnDt;

    String ogCd;
    String ogNm;
    String rolDvNm;
    String wkGrpNm;
    String bizAgntYn;
    String wrkDt;
    String wrkNm;
    String egerWrkStatCd;
    String dnlStrtdt;
    String dnlEnddt;
    String bizAgntPrtnrNo;
    String agntPrtnrKnm;
    String pcpPrtnrNo;
    String pcpPrtnrKnm;
    String procsDtm;
    String employeeIDNumber;

}
