package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutomaticTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutomaticTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutomaticTransferObjectItemizationInterfaceDvo;

@Mapper
public interface WwdaAutomaticTransferInterfaceMapper {

    /**
     * 자동이체 출금내역 조회
     * @param dto
     * @return
     */
    List<WwdaAutomaticTransferInterfaceDto.SearchPaymentAndWithdrawalRes> selectPaymentAndWithdrawalItemizations(
        WwdaAutomaticTransferInterfaceDto.SearchReq dto
    );

    /**
     * 자동이체 변경내역 조회
     * @param dto
     * @return
     */
    List<WwdaAutomaticTransferInterfaceDto.SearchChangeRes> selectChangeItemizations(
        WwdaAutomaticTransferInterfaceDto.SearchReq dto
    );

    /**
     * 자동이체 대상목록 조회
     * @param dto
     * @return
     */
    List<WwdaAutomaticTransferObjectItemizationInterfaceDvo> selectObjectItemizations(
        WwdaAutomaticTransferInterfaceDto.SearchObjectReq dto
    );

    /**
     * 자동이체 정보 조회
     * @param dto
     * @return
     */
    List<WwdaAutomaticTransferInfoInterfaceDvo> selectInfos(
        WwdaAutomaticTransferInterfaceDto.SearchReq dto
    );

}
