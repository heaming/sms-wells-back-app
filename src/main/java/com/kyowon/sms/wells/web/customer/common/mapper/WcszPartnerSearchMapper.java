package com.kyowon.sms.wells.web.customer.common.mapper;

import com.kyowon.sms.wells.web.customer.common.dvo.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WcszPartnerSearchMapper {

    WcszPartnerDvo selectPartnerByPk(String ogTpCd, String prtnrNo);

}
