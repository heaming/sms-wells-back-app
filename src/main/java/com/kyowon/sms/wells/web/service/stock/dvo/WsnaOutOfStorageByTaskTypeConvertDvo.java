package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *  품목별 자재출고 현황 pivot 용 dvo
 * </pre>
 *
 * @author heymi.cho
 * @since 2024.02.23
 */
@Getter
@Setter
public class WsnaOutOfStorageByTaskTypeConvertDvo {

    // PIVOT field
    String wareByUseQty;
    String wareByPdctUprcSum;

    //request dto 매핑
    String stOstrDt;
    String edOstrDt;
    String cstDvCd;
    String stocTypeCd;
    String taskTypeCd;
    String dispTypeCd;
    String itmKndCd;
    String itmPdCd;
    String itmPdCdFrom;
    String itmPdCdTo;
    String pdGdCd;
    String useSel;
    String itmGrpCd;
    String svMatGrpCd;
}
