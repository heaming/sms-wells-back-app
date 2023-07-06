package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmCreateDvo;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmReceivingAndPayingDvo;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmSapMatDvo;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSlCnfmBasDvo;

/**
 * <pre>
 * 매출확정생성 서비스 맵퍼
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-04-13
 */
@Mapper
public interface WdcbSalesConfirmCreateMapper {

    int selectSalesConfirmSerialNumber(WdcbSalesConfirmCreateDvo dvo);

    String selectDgCstId(WdcbSalesConfirmCreateDvo dvo);

    String selectSapPdDvCd(WdcbSalesConfirmCreateDvo dvo);

    WdcbSalesConfirmSapMatDvo selectSapMat(WdcbSalesConfirmCreateDvo dvo);

    String selectCtrlOrdTpCd(String sapPdDvCd, String sellInflwChnlDtlCd, String ogTpCd);

    WdcbSalesConfirmReceivingAndPayingDvo selectReceivingAndPaying(WdcbSalesConfirmCreateDvo dvo);

    String selectSlpMapngCdv(String sellTpDtlCd, String clssVal, String slTpDvCd, String addCondition);

    int insertSalesConfirm(WdcbSlCnfmBasDvo inputDvo);
}
