package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbDepositRefundInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbDepositRefundInterfaceMapper {

    List<WwdbDepositRefundInterfaceDto.SearchRes> selectDepositRefunds(WwdbDepositRefundInterfaceDto.SearchReq dto);

}
