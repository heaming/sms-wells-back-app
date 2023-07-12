package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesInterfaceDto.SearchAllianceContractRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesInterfaceDto.SearchByContractReq;

@Mapper
public interface WdcbSalesInterfaceMapper {

    SearchAllianceContractRes selectAllianceContract(final SearchByContractReq req);

}
