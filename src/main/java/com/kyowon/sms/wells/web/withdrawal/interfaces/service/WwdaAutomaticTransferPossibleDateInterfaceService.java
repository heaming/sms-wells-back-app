package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutomaticTransferPossibleDateInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdaAutomaticTransferPossibleDateInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdaAutomaticTransferPossibleDateInterfaceService {

    private final WwdaAutomaticTransferPossibleDateInterfaceMapper mapper;

    //[EAI_WWDI1011] wells 카드 자동이체 가능일자 조회
    public List<WwdaAutomaticTransferPossibleDateInterfaceDto.SearchRes> getPassibleDatesByCard(
        WwdaAutomaticTransferPossibleDateInterfaceDto.SearchReq dto
    ) {
        return mapper.selectCardAutomaticTransferPossibleDt(dto);
    }

    //[EAI_WWDI1012] wells 계좌 자동이체 가능일자 조회
    public List<WwdaAutomaticTransferPossibleDateInterfaceDto.SearchRes> getPassibleDatesByAcount(
        WwdaAutomaticTransferPossibleDateInterfaceDto.SearchReq dto
    ) {
        return mapper.selectAccountAutomaticTransferPossibleDt(dto);
    }
}
