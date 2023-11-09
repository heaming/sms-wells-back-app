package com.kyowon.sms.wells.web.deduction.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindRes;

@Mapper
public interface WdecRdsProductDisbursementHoldingInterfaceMapper {
    FindRes selectRdsProductDisbursementHoldings(FindReq dto);

    //등록
    int insertRdsProductDisbursementHoldings(@Param("item")
    List<WdecRdsProductDisbursementHoldingInterfaceDto.OrganizationTypes> dto, String rdsDsbDuedt, String wkPrtcDtmVal);

}
