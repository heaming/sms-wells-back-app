package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebEgerAllowanceMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 엔지니어 수당 생성관리
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.02.01
 */
@Service
@RequiredArgsConstructor
public class WfebEgerAllowanceService {

    private final WfebEgerAllowanceMapper mapper;

    /**
     * 엔지니어 수당 내역 - 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schRsbTp : 직책유형,
     * schOgLevl1 : 조직레벨1,
     * schOgLevl2 : 조직레벨2,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfebEgerAllowanceDto.SearchEngineerRes> getEngineerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    ) {
        return this.mapper.selectEngineerAllowances(dto);
    }

    /**
     * 엔지니어 관리자 수당 내역 - 조회
     * @param dto : {
     * schPerfYm : 실적년월,
     * schRsbTp : 직책유형,
     * schOgLevl1 : 조직레벨1,
     * schOgLevl2 : 조직레벨2,
     * schNo : 번호 }
     * @return 조회결과
     */
    public List<WfebEgerAllowanceDto.SearchEngineerManagerRes> getEngineerManagerAllowances(
        WfebEgerAllowanceDto.SearchReq dto
    ) {
        return this.mapper.selectEngineerManagerAllowances(dto);
    }

}
