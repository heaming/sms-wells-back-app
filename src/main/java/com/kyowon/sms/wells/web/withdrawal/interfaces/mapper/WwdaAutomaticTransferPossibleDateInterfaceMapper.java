package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutomaticTransferPossibleDateInterfaceDto;

@Mapper
public interface WwdaAutomaticTransferPossibleDateInterfaceMapper {

    //[EAI_WWDI1011] wells 카드 자동이체 가능일자 조회
    List<WwdaAutomaticTransferPossibleDateInterfaceDto.SearchRes> selectCardAutomaticTransferPossibleDt(
        WwdaAutomaticTransferPossibleDateInterfaceDto.SearchReq dto
    );

    //[EAI_WWDI1012] wells 계좌 자동이체 가능일자 조회
    List<WwdaAutomaticTransferPossibleDateInterfaceDto.SearchRes> selectAccountAutomaticTransferPossibleDt(
        WwdaAutomaticTransferPossibleDateInterfaceDto.SearchReq dto
    );

}
