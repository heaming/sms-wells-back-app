package com.kyowon.sms.wells.web.contract.interfaces.service;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiPackageContractRelationDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.interfaces.mapper.WctiPackageContractRelationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctiPackageContractRelationService {

    private final WctiPackageContractRelationMapper mapper;

    /**
    * 패키지연관 계약건 조회
    * @programId   EAI_WSSI1067
    * @param       req
    * @return      list
    */
    public List<SearchRes> getPackageContractRelations(SearchReq req) {
        return mapper.selectPackageContractRelations(req);
    }
}
