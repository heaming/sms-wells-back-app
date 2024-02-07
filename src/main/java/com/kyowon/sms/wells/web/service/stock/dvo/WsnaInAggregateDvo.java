package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */
@Getter
@Setter
public class WsnaInAggregateDvo {

    // PIVOT 창고번호 조건
    String wareNoInStr;
    // PIVOT 창고번호 필드
    String wareNoFields;
    String wareLogisticsFieldsSumStr;
    String wareServiceFieldsSumStr;
    String wareBusinessFieldsSumStr;

    //DTO 매핑
    String strTpCd;
    String pdCdFrom;
    String pdCdTo;
    String sapCdFrom;
    String sapCdTo;
    String baseDateFrom;
    String baseDateTo;
    String itmKndCd;
    String itmGrpCd;
    List<String> itmPdCds;
    String pdCd;
    String pdGdCd;
    String wareDvCd;
    String useYn;
}
