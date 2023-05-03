package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferPossibleDateInterfaceDto;

@Mapper
public interface WwdaAutoTransferPossibleDateInterfaceMapper {

    //[EAI_WWDI1011] wells 카드 자동이체 가능일자 조회
    List<WwdaAutoTransferPossibleDateInterfaceDto.SearchRes> selectCardAutomaticTransferPossibleDt(
        WwdaAutoTransferPossibleDateInterfaceDto.SearchReq dto
    );

    //[EAI_WWDI1012] wells 계좌 자동이체 가능일자 조회
    List<WwdaAutoTransferPossibleDateInterfaceDto.SearchRes> selectAccountAutomaticTransferPossibleDt(
        WwdaAutoTransferPossibleDateInterfaceDto.SearchReq dto
    );

}
