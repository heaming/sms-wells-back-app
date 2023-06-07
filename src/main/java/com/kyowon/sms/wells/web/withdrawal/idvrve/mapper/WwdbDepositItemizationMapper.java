package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositComparisonComfirmationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbIntegrationDepositInfoDvo;

@Mapper
public interface WwdbDepositItemizationMapper {
    List<WwdbDepositItemizationDto.SearchRes> selectDepositItemizations(WwdbDepositItemizationDto.SearchReq dto);

}
