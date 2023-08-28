package com.kyowon.sms.wells.web.customer.prospective.dvo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class WcsbtbSsopPspcCstInrtIzDvo {
    // TB_SSOP_PSPC_CST_INRT_IZ

    private String pspcCstId; /* 가망고객ID */
    private String inrtArtcCd; /* 관심사항코드 */
    private String sellPrpChval; /* 판매속성문자값 */
    private BigDecimal sellPrpNuval; /* 판매속성숫자값 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
