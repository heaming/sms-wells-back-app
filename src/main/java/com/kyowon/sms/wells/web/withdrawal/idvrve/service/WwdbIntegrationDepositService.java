package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbIntegrationDepositDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbIntegrationDepositMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 통합입금목록 서비스
 * </pre>
 *
 * @author heungjun.lee
 * @since 2023-03-03
 */
@Service
@RequiredArgsConstructor
public class WwdbIntegrationDepositService {

    private final WwdbIntegrationDepositMapper mapper;

    /**
     * 통합입금목록 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @Transactional
    public PagingResult<SearchRes> getIntegrationDepositPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectIntegrationDeposit(dto, pageInfo);
    }

    /**
     * 통합입금목록 엑셀다운로드
     * @param dto
     * @return List<SearchRes>
     */
    @Transactional
    public List<SearchRes> getIntegrationDepositExcels(SearchReq dto) {
        return mapper.selectIntegrationDeposit(dto);
    }
}
