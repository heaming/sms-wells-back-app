package com.kyowon.sms.wells.web.fee.aggregate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaBsFeeMgtDto;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaBsFeeMgtMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * BS 실적 및 수수료
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WfeaBsFeeMgtService {

    private final WfeaBsFeeMgtMapper mapper;

    /**
     * BS 수수료 내역 조회
     * @param dto
     * @return 조회결과
     */
    public List<WfeaBsFeeMgtDto.SearchRes> getBsFees(
        WfeaBsFeeMgtDto.SearchReq dto
    ) {
        return mapper.selectBsFees(dto);
    }

}
