package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaPlannerFeeMeetingAttendanceConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto.SaveReq;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaPlannerFeeMeetingAttendanceDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaPlannerFeeMeetingAttendanceMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 생성관리-미팅참석집계(플래너) 서비스
 * </pre>
 *
 * @author minkyu.bae
 * @since 2023-07-24
 */
@Service
@RequiredArgsConstructor
public class WfeaPlannerFeeMeetingAttendanceService {

    private final WfeaPlannerFeeMeetingAttendanceMapper mapper;
    private final WfeaPlannerFeeMeetingAttendanceConverter converter;

    /**
     * 수수료 생성관리-미팅참석집계(플래너) 삭제 후 저장
     * @param dto 삭제 및 저장 조건 정보
     * @return 저장 결과
     */
    @Transactional
    public int savePlannerFeeMeetingAttendances(SaveReq dto) {

        int processCount = 0;
        WfeaPlannerFeeMeetingAttendanceDvo dvo = converter.mapSaveReqToWfeaPlannerFeeMeetingAttendanceDvo(dto);

        mapper.deletePlannerFeeMeetingAttendances(dvo);

        processCount = mapper.insertPlannerFeeMeetingAttendances(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_AGRG_FAIL");

        return processCount;
    }

    public void deletePlannerFeeMeetingAttendances(
        String ogTpCd, /* 조직유형 */
        String perfYm, /* 실적년월 */
        String rsbTpCd, /* 직책유형코드 */
        String feeTcntDvCd /* 수수료차수구분코드 */
    ) {
        WfeaPlannerFeeMeetingAttendanceDvo dvo = converter.mapSaveReqToWfeaPlannerFeeMeetingAttendanceDvo(
            SaveReq.builder()
                .ogTpCd(ogTpCd)
                .perfYm(perfYm)
                .rsbTpCd(rsbTpCd)
                .feeTcntDvCd(feeTcntDvCd)
                .build()
        );
        mapper.deletePlannerFeeMeetingAttendances(dvo);
    }
}
