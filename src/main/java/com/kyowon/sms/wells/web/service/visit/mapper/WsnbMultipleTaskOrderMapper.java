package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMultipleTaskOrderDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMultipleTaskOrderDvo;

@Mapper
public interface WsnbMultipleTaskOrderMapper {

    int selectCountItemizationByItemGroup(String cntrNo);

    int selectCountItemizationByCntrNoB(String cntrNo);

    WsnbMultipleTaskOrderDvo selectWorkRequidationItemization(String asIstOjNo);

    String selectRcgvpKnm(String cntrNo);

    int selectCountInstallationDate(String cntrNo);

    int selectCountRequidationDate(String cntrNo);

    int selectCountAsIstAsnIz(String SvBizDclsfCd, String cntrNo);

    int selectCountStopDate(String cntrNo);

    String selectWorkContent(String svBizDclsfCd);

    int selectUseMonth(String vstRqdt, String cntrNo);

    int selectCountChangeTotal(String cntrNo);

    int selectCountRangeChange(String cntrNO);

    int selectCountRangeChangeBs(String CntrNo);

    int selectCountItem(String cntrNo);

    int selectCountAsIstOjIz(SaveReq dto);

    WsnbMultipleTaskOrderDvo selectAsIstOjIzKey(SaveReq dto);

    String selectBasePdCdBySaleCd(String saleCd);

    String selectBasePdCdByCntrNo(String CntrNo);

    int selectCountExecutionItemization(String CntrNo);

    int updateExecutionItemization(WsnbMultipleTaskOrderDvo dvo);

    int insertExecutionItemization(WsnbMultipleTaskOrderDvo dvo);

    int insertErrorItemization(WsnbMultipleTaskOrderDvo dvo);

    int insertIstObjectItemization(WsnbMultipleTaskOrderDvo dvo);

    int updateIstObjectItemization(WsnbMultipleTaskOrderDvo dvo);

    int updateIstObjectItemizationByPk(WsnbMultipleTaskOrderDvo dvo);

    int deleteAsPutItemIz(WsnbMultipleTaskOrderDvo dvo);

    int insertAsPutItemIz(WsnbMultipleTaskOrderDvo dvo);

    String selectCustomerServiceAssignNo(WsnbMultipleTaskOrderDvo dvo);

    int insertAsInstallationAssignHist(WsnbMultipleTaskOrderDvo dvo);

    int deleteAsInstallationAssignHist(String cstSvAsnNo);

    int insertAsInstallationAssign(WsnbMultipleTaskOrderDvo dvo);

    int updateAsInstallationAssign(WsnbMultipleTaskOrderDvo dvo);

}
