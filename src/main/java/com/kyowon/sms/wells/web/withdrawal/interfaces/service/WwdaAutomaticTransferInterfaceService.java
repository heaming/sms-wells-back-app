package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdaAutomaticTransferConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutomaticTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutomaticTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutomaticTransferObjectItemizationInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdaAutomaticTransferInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdaAutomaticTransferInterfaceService {

    private final WwdaAutomaticTransferInterfaceMapper mapper;
    private final WwdaAutomaticTransferConverter converter;

    /**
     * 자동이체 출금내역 조회
     * @param dto
     * @return
     */
    public List<WwdaAutomaticTransferInterfaceDto.SearchPaymentAndWithdrawalRes> getPaymentAndWithdrawalItemizations(
        WwdaAutomaticTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectPaymentAndWithdrawalItemizations(dto);
    }

    /**
    * 자동이체 변경내역 조회
    * @param dto
    * @return
    */
    public List<WwdaAutomaticTransferInterfaceDto.SearchChangeRes> getChangeItemizations(
        WwdaAutomaticTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectChangeItemizations(dto);
    }

    /**
    * 자동이체 대상목록 조회
    * @param dto
    * @return
    */
    public List<WwdaAutomaticTransferInterfaceDto.SearchObjectRes> getObjectItemizations(
        WwdaAutomaticTransferInterfaceDto.SearchObjectReq dto
    ) {
        List<WwdaAutomaticTransferObjectItemizationInterfaceDvo> selectResults = mapper.selectObjectItemizations(dto);
        for (WwdaAutomaticTransferObjectItemizationInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutomaticTransferInterfaceDto.SearchObjectRes> results = converter
            .mapWwdaAutomaticTransferDvoToSearchObjectRes(selectResults);

        return results;
    }

    /**
    * 자동이체 정보 조회
    * @param dto
    * @return
    */
    public List<WwdaAutomaticTransferInterfaceDto.SearchRes> getInfos(
        WwdaAutomaticTransferInterfaceDto.SearchReq dto
    ) {
        List<WwdaAutomaticTransferInfoInterfaceDvo> selectResults = mapper.selectInfos(dto);
        for (WwdaAutomaticTransferInfoInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutomaticTransferInterfaceDto.SearchRes> results = converter
            .mapWwdaAutomaticTransferDvoToSearchRes(selectResults);

        return results;
    }

}
