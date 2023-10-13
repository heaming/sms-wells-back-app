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

    // 기준년월
    private String apyYm;
    // 배정년월
    private String asnOjYm;
    // 회차
    private BigDecimal cnt;
    // 출고창고번호
    private String ostrWareNo;
    // 창고구분
    private String wareDvCd;
    // 창고상세구분
    private String wareDtlDvCd;
    // MAX 품목물량배정번호, 품목물량배정번호 생성 시 사용
    private int qomAsnNo;
}
