package com.kyowon.sms.wells.web.fee.calculation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.calculation.converter.WfebEgerAllowanceConverter;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebEgerAllowanceDvo;
import com.kyowon.sms.wells.web.fee.calculation.mapper.WfebEgerAllowanceMapper;
import com.sds.sflex.system.config.validation.BizAssert;

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
    private final WfebEgerAllowanceConverter converter;

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

    /**
     * 엔지니어 수당 생성 - 생성
     * @param dto : {
     * param1 : 실적년월,
     * param2 : 직책유형
     * @return 생성건수
     */
    @Transactional
    public int saveEgerAllowances(WfebEgerAllowanceDto.SaveReq dto) {
        int processCount = 0;

        WfebEgerAllowanceDvo dvo = this.converter.mapSaveReqToWfebEgerAllowanceDvo(dto);

        this.mapper.deleteEgerAllowances(dvo);
        processCount = this.mapper.insertEgerAllowances(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_CRT_FAIL");

        return processCount;
    }
}
