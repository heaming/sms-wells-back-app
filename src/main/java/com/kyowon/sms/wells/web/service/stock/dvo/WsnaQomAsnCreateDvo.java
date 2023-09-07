package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0190M01, W-SV-U-0191M01 개인/독립창고 물량배정 데이터 생성 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-12
 */

@Getter
@Setter
public class WsnaQomAsnCreateDvo {

    private String apyYm;
    private String asnOjYm;
    private BigDecimal cnt;
    private String ostrWareNo;
    private String wareDvCd;
    private String wareDtlDvCd;
    private int qomAsnNo;
}
