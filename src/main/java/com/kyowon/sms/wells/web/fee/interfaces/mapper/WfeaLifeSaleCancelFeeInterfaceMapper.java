package com.kyowon.sms.wells.web.fee.interfaces.mapper;

import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.deduction.interfaces.dto.WdecRdsProductDisbursementHoldingInterfaceDto.FindRes;
import com.kyowon.sms.wells.web.deduction.interfaces.dvo.WdecRdsProductDisbursementHoldingInterfaceDvo;
import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto;
import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto.SaveReq;
import com.kyowon.sms.wells.web.fee.interfaces.dvo.WfeaLifeSaleCancelFeenterfaceDvo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WfeaLifeSaleCancelFeeInterfaceMapper {
    int selectLifeFeeValidKeyCount(WfeaLifeSaleCancelFeenterfaceDvo dvo);
    int deleteLifeFeeSync(WfeaLifeSaleCancelFeenterfaceDvo dvo);
    int insertLifeFeeSync(WfeaLifeSaleCancelFeenterfaceDvo dvo);

    // 2024.01.25 미사용
    String selectLifeFeeValidKey(WfeaLifeSaleCancelFeenterfaceDvo dvo);
    int updateLifeFeeSync(WfeaLifeSaleCancelFeenterfaceDvo dvo);



}
