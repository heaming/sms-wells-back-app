package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.SalesControlItemizationDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbSalesControlItemizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbSalesControlItemizationService {
    private final WwdbSalesControlItemizationMapper mapper;

    /**
     * Wells 매출조정 내역 조회
     * @param dto
     * @return
     */
    public List<SalesControlItemizationDto.SearchRes> getSalesControlItemizations(
        SalesControlItemizationDto.SearchReq dto
    ) {
        return mapper.selectSalesControlItemizations(dto);
    }
}
