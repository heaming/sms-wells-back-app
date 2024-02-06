package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * K-W-SV-U-0258M01 일자별 자재 입출고 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.19
 */
@Setter
@Getter
public class WsnaByDayMaterialInOutSearchDvo {

    String wareDvCd;
    String hgrWareNo;
    String itmKndCd;
    String itmGrpCd;
    List<String> itmPdCds;
    String ostrAkTpCd;
    String baseDateFrom;
    String baseDateTo;
    String ostrDtrmYn;

    String strHopDt;
    String ostrAkTpNm;
    String ostrAkNo;
    String ostrAkSn;
    String ostrWareNm;
    String strWareNm;
    String sapMatCd;
    String pdCd;
    String pdNm;
    String ostrStckQty;
    String strStckQty;
    String rgstDtOstrStckQty;
    String rgstDtstrStckQty;
    String ostrAkQty;
    String ostrDt1;
    String ostrQty1;
    String outYn;
    String outPeriod;
    String sysDt;
    String sysDtAgo;
    String rgstDt;
    String rgstDtAgo;
    String strOjWareNo;
    String ostrOjWareNo;
}
