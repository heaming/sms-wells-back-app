package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositNumberDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositNumberDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbIntegrationDepositNumberMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 통합입금번호 조회 서비스
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-04-03
 */
@Service
@RequiredArgsConstructor
public class WwdbIntegrationDepositNumberService {
    private final WwdbIntegrationDepositNumberMapper mapper;

    /**
     * 통합입금번호 목록 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getIntegrationDepositNumberPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIntegrationDepositNumbers(dto, pageInfo);
    }

    /**
     * 통합입금번호 목록 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    public List<SearchRes> getIntegrationDepositNumberExcels(SearchReq dto) {
        return mapper.selectIntegrationDepositNumbers(dto);
    }
}
