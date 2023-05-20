package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositRefundItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbVirtualAccountDto;

@Mapper
public interface WwdbDepositRefundItemizationMapper {

    /**
     * 가상계좌조회 (모바일)
     * @param req
     * @return
     */
    List<WwdbDepositRefundItemizationDto.SearchRes> selectDepositRefundItemizations(
        WwdbDepositRefundItemizationDto.SearchReq req
    );

}
