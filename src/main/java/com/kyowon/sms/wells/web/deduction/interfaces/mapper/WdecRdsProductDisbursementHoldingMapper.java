package com.kyowon.sms.wells.web.deduction.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementHoldingDvo;

@Mapper
public interface WdecRdsProductDisbursementHoldingMapper {
    FindRes selectRdsProductDisbursementHoldings(FindReq dto);

    int insertRdsProductDisbursementHoldings(@Param("item")
    WdecRdsProductDisbursementHoldingDvo saveDvo);

}
