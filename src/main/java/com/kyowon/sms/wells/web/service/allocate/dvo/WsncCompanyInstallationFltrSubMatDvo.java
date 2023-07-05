package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * 회사설치 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-07-04
 */

@Getter
@Setter
public class WsncCompanyInstallationFltrSubMatDvo {
    String itmKndCd;
    String sapMatCd;
    String itmPdCd;
    String itmPdNm;
    Long useQtySum;
    Long pdctUprcSum;
    Long pdctUprc;
    Long csmrUprcAmt;
    Long csmrUprcAmtSum;
}
