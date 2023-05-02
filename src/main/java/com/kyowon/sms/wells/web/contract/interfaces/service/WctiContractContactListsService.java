package com.kyowon.sms.wells.web.contract.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactListsDto.SearchReq;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractContactListsDto.SearchRes;
import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiContractContactListsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiContractContactListsService {
    private final WctiContractContactListsMapper mapper;

    /**
    * 계약 컨택 현황 조회
    * -   고객센터 아웃바운드운영팀에서 계약 컨택을 진행하는 건들을 조회한다.
    * @interfaceId   EAI_WSSI1074
    * @programId   W-SS-I-0031
    * @param       dto
    * @return      list
    */
    public List<SearchRes> getContractContactPs(SearchReq dto) {
        return mapper.selectContractContactPs(dto);
    }
}
