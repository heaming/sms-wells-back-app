package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0140M01 품목별 재고 집계 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-08
 */

@Getter
@Setter
public class WsnaItemByStockAggregationDvo {

    private String baseDt;
    private String mgtTypCd;
    private String itmKndCd;
    private List<String> itmPdCds;
    private String itmGdCd;
    private String useYn;
    private String matUtlzDvCd;
    private String wareTpCd;
    private String itmPdCd;
    private String strtSapCd;
    private String endSapCd;

    // PIVOT 창고번호 조건
    private String wareNoInStr;
    // PIVOT 창고번호 필드
    private String wareNoFields;

    // OFFSET
    private Integer offSet;
    // FETCH SIZE
    private Integer size;
}
