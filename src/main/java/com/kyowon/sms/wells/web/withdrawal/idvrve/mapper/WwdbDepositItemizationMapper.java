package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositItemizationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositItemizationResultDvo;

@Mapper
public interface WwdbDepositItemizationMapper {
    List<WwdbDepositItemizationResultDvo> selectDepositItemizations(WwdbDepositItemizationDvo dvo);

}
