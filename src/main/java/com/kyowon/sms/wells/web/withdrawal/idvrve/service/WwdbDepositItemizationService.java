package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositItemizationDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositItemizationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbDepositItemizationService {

    private final WwdbDepositItemizationMapper mapper;

    /**
     * 입금내역조회(모바일) - W-WD-S-0041
     * @param dto
     * @return
     */
    public List<WwdbDepositItemizationDto.SearchRes> getDepositItemizations(
        WwdbDepositItemizationDto.SearchReq dto
    ) {
        return mapper.selectDepositItemizations(dto);
    }
}
