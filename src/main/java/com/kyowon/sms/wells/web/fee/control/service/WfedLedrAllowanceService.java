package com.kyowon.sms.wells.web.fee.control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.control.dto.WfedLedrAllowanceDto;
import com.kyowon.sms.wells.web.fee.control.mapper.WfedLedrAllowanceMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 단장 수당 관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfedLedrAllowanceService {

    private final WfedLedrAllowanceMapper mapper;

    /**
     * 단장 수당 목록 조회
     * @param dto : {
     * perfYm : 실적년월,
     * rsbTp : 직책유형,
     * inqrDv : 조회구분,
     * no : 번호 }
     * @return 조회결과
     */
    public List<WfedLedrAllowanceDto.SearchRes> getLeaderAllowances(WfedLedrAllowanceDto.SearchReq dto) {
        /**
         * TODO : 조회구분 : 개인별/합계 쿼리 분리 필요
         * */
        return this.mapper.selelctLeaderAllowances(dto);
    }

}
