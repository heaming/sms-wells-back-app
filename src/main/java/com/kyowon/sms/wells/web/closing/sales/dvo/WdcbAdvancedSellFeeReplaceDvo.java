package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 선급판매수수료 비용 대체 전표 생성 DVO
 * </pre>
 *
 * @author Kicheol Choi
 * @since 2023-06-22
 */
@Getter
@Setter
@ToString
public class WdcbAdvancedSellFeeReplaceDvo {
    private String piaSellFeeSmry;
    private String kwGrpCoCd;
    private String orglFnlMdfcDtm;
}
