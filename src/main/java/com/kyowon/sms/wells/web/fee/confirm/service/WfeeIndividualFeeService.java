package com.kyowon.sms.wells.web.fee.confirm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.confirm.dto.WfeeIndividualFeeDto;
import com.kyowon.sms.wells.web.fee.confirm.mapper.WfeeIndividualFeeMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 개인 상세
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfeeIndividualFeeService {
    private final WfeeIndividualFeeMapper mapper;

    /**
     * 수수료 개인별 실적 상세 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfeeIndividualFeeDto.SearchRes> getIndividualPerformanceDetails(
        WfeeIndividualFeeDto.SearchReq dto
    ) {
        return this.mapper.selectIndividualPerformanceDetails(dto);
    }
}
