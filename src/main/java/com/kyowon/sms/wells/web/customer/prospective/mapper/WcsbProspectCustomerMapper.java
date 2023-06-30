package com.kyowon.sms.wells.web.customer.prospective.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerBasDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerCnslBasDvo;

@Mapper
public interface WcsbProspectCustomerMapper {

    int insertPspcCstBas(@Param("dvo")
    WcsbProspectCustomerBasDvo dvo);

    int insertPspcCstChHist(String pspcCstId);

    int insertPspcCstCnslBas(@Param("dvo")
    WcsbProspectCustomerCnslBasDvo dvo);

    int insertPspcCstCnslChHist(String pspcCstCnslId, String pspcCstId);
}
