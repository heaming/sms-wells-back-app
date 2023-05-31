package com.kyowon.sms.wells.web.service.allocate.rest.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncCompanyIstStateConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyIstStateDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncCompanyIstStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncCompanyIstStateService {

    private final WsncCompanyIstStateMapper mapper;
    private final WsncCompanyIstStateConverter converter;

    /**
     * 전체 조회
     *
     * @programId : K-W-SV-U-0270M01
     * @param dto : 조회파라메터
     * @return 조회결과
     */
    public PagingResult<WsncCompanyIstStateDto.SearchAllRes> getCompanyIstStateAll(
        WsncCompanyIstStateDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectCompanyIstStateAll(dto, pageInfo);
    }

}
