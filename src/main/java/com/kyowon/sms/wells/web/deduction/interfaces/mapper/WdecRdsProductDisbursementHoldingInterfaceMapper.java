package com.kyowon.sms.wells.web.deduction.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementHoldingInterfaceDvo;

@Mapper
public interface WdecRdsProductDisbursementHoldingInterfaceMapper {
    FindRes selectRdsProductDisbursementHoldings(FindReq dto);

    //등록
    int insertRdsProductDisbursementHoldings(@Param("item")
    WdecRdsProductDisbursementHoldingInterfaceDvo saveDvo);

}
