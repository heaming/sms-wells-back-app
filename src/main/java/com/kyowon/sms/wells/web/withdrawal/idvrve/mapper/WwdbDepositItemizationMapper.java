package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositItemizationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositItemizationResultDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositItemizationDto.SearchDepositItemizationsByEngineersRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositItemizationDto.SearchReq;

@Mapper
public interface WwdbDepositItemizationMapper {
    List<WwdbDepositItemizationResultDvo> selectDepositItemizations(WwdbDepositItemizationDvo dvo);

    List<SearchDepositItemizationsByEngineersRes> selectDepositItemizationsByEngineers(WwdbDepositItemizationDvo dvo);
}
