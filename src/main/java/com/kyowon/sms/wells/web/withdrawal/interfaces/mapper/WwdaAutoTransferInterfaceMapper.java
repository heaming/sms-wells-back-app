package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferObjectItemizationInterfaceDvo;

@Mapper
public interface WwdaAutoTransferInterfaceMapper {

    /**
     * 자동이체 출금내역 조회
     * @param dto
     * @return
     */
    List<WwdaAutoTransferInterfaceDto.SearchPaymentAndWithdrawalRes> selectPaymentAndWithdrawalItemizations(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    );

    /**
     * 자동이체 변경내역 조회
     * @param dto
     * @return
     */
    List<WwdaAutoTransferInterfaceDto.SearchChangeRes> selectChangeItemizations(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    );

    /**
     * 자동이체 대상목록 조회
     * @param dto
     * @return
     */
    List<WwdaAutoTransferObjectItemizationInterfaceDvo> selectObjectItemizations(
        WwdaAutoTransferInterfaceDto.SearchObjectReq dto
    );

    /**
     * 자동이체 정보 조회
     * @param dto
     * @return
     */
    List<WwdaAutoTransferInfoInterfaceDvo> selectInfos(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    );

}
