package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdaAutoTransferConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdaAutoTransferInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferInfoInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdaAutoTransferObjectItemizationInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdaAutoTransferInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdaAutoTransferInterfaceService {

    private final WwdaAutoTransferInterfaceMapper mapper;
    private final WwdaAutoTransferConverter converter;

    /**
     * 자동이체 출금내역 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchPaymentAndWithdrawalRes> getPaymentAndWithdrawalItemizations(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectPaymentAndWithdrawalItemizations(dto);
    }

    /**
    * 자동이체 변경내역 조회
    * @param dto
    * @return
    */
    public List<WwdaAutoTransferInterfaceDto.SearchChangeRes> getChangeItemizations(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectChangeItemizations(dto);
    }

    /**
    * 자동이체 대상목록 조회
    * @param dto
    * @return
    */
    public List<WwdaAutoTransferInterfaceDto.SearchObjectRes> getObjectItemizations(
        WwdaAutoTransferInterfaceDto.SearchObjectReq dto
    ) {
        List<WwdaAutoTransferObjectItemizationInterfaceDvo> selectResults = mapper.selectObjectItemizations(dto);
        for (WwdaAutoTransferObjectItemizationInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutoTransferInterfaceDto.SearchObjectRes> results = converter
            .mapWwdaAutoTransferDvoToSearchObjectRes(selectResults);

        return results;
    }

    /**
    * 자동이체 정보 조회
    * @param dto
    * @return
    */
    public List<WwdaAutoTransferInterfaceDto.SearchRes> getInfos(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        List<WwdaAutoTransferInfoInterfaceDvo> selectResults = mapper.selectInfos(dto);
        for (WwdaAutoTransferInfoInterfaceDvo selectResult : selectResults) {
            selectResult
                .setMpno(selectResult.getCralLocaraTno() + selectResult.getMexnoEncr() + selectResult.getCralIdvTno());
        }
        List<WwdaAutoTransferInterfaceDto.SearchRes> results = converter
            .mapWwdaAutoTransferDvoToSearchRes(selectResults);

        return results;
    }

    /**
     * 자동이체 구분(개인/법인) 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchCorporatePersonalityDivisionRes> getCorporatePersonalityDivisions(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectCorporatePersonalityDivisions(dto);
    }

    /**
     * 자동이체 은행 목록 조회
     * @param dto
     * @return
     */
    public List<WwdaAutoTransferInterfaceDto.SearchFinancialInstitutionCodeRes> getFinancialInstitutionCodes(
        WwdaAutoTransferInterfaceDto.SearchReq dto
    ) {
        return mapper.selectFinancialInstitutionCodes(dto);
    }

}
