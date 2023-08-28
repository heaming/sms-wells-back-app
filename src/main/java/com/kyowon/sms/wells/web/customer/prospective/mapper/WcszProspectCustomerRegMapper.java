package com.kyowon.sms.wells.web.customer.prospective.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspecCustomerDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbTbSsopPspcCstDdlvHistDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbTbSsopPspcCstRlpplDtlDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbtbSsopPspcCstInrtIzDvo;

/* Wcsz-prospect-customer-reg.xml */
@Mapper
public interface WcszProspectCustomerRegMapper {

    // 기본정보 저장
    int insertProspectCustomer(@Param("dvo")
    WcsbProspecCustomerDvo dvo);

    // 기본정보 이력저장
    int insertProspectCustomerHistory(String pspcCstId, String startDtm);

    // 교사 저장
    int insertPspcCstDdlvHist(WcsbTbSsopPspcCstDdlvHistDvo dvo);

    // 자녀 저장
    int insertPspcCstRlpplDtl(WcsbTbSsopPspcCstRlpplDtlDvo dvo);

    // 자녀 이력저장
    int insertPspcCstRlpplDtlHistory(String pspcCstId, String startDtm);

    // 관심상품 저장
    int insertPspcCstInrtIz(WcsbtbSsopPspcCstInrtIzDvo dvo);

    // 관심상품 이력저장
    int insertPspcCstInrtIzHistory(String pspcCstId, String startDtm);

    String selectDupFreExpnBasByMpno(
        @Param("cralLocaraTno")
        String cralLocaraTno,
        @Param("locaraTno")
        String locaraTno,
        @Param("exnoEncr")
        String exnoEncr
    );

    // 센터장 찾기.

    String selectPspcCstIchrPrtnrNoForDdlv(WcsbProspecCustomerDvo dvo);

    int updatePspcCstCnslBasTm(String ogTpCd, String epNo, String pspcCstCnslId);

    int updatePspcCstCnslChHistForEnd(String pspcCstCnslId);

    int insertPspcCstCnslChHist(String pspcCstCnslId);

}
