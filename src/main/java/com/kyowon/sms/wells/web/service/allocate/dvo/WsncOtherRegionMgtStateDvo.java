package com.kyowon.sms.wells.web.service.allocate.dvo;

//import com.sds.sflex.system.config.annotation.DBDecField;
//import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * 타지역단 관리 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-06-01
 */

@Getter
@Setter
public class WsncOtherRegionMgtStateDvo {
    String rglnGrp;
    String cntrNo;
    String cstKnm;
    String newAdrZip;
    String cstAdr;
    String ltnAdr;
    String cralLocaraTno;
    //        @DBEncField
    //    @DBDecField
    String mexnoEncr;
    String cralIdvTno;
    String ogCd;
    String ogNm;
    String mngtPrtnrNo;
    String prtnrKnm;
    String bldNm;
    String fxnPrtnrYn;
    String mngerRglvDvCd;
}
