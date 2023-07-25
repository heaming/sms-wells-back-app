package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  출고 집계 현황 dvo
 * </pre>
 *
 * @author junggheejin
 * @since 2023.07.25
 */
@Getter
@Setter
public class WsnaOutOfStorageAgrgDvo {

    // PIVOT 창고번호 조건
    private String wareNoInStr;
    // PIVOT 창고번호 필드
    private String wareNoFields;

    String startDt;
    String endDt;
    String ostrTpCd;
    String sapMatCdFrom;
    String sapMatCdTo;
    String itmCdFrom;
    String itmCdTo;
    String itmGdCd;
    String itmKndCd;
    String itmPdCd;
    String matUtlzDvCd;
    String useYn;
}
