package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  월별 출고 집계 현황 dvo
 * </pre>
 *
 * @author junggheejin
 * @since 2023.08.25
 */
@Getter
@Setter
public class WsnaMonthlyOutOfStorageAgrgDvo {

    // PIVOT (FNL_VST_FSH_YM) 조건
    String fnlVstFshYmStr;
    // PIVOT (FNL_VST_FSH_YM) 필드
    String fnlVstFshYmStrFields;

    //DTO 매핑
    String startDt;
    String endDt;
    String wareDvCd;
    String wareDtlDvCd;
    String wareNoM;
    String wareNoD;
    String wareUseYn;
    String itmGdCd;
    String itmKndCd;
    String itmPdCd;
    String useYn;
    List<String> itmPdCds;
    String itmGrpCd;
    String strtSapCd;
    String endSapCd;
}
