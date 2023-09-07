package com.kyowon.sms.wells.web.fee.aggregate.service;

import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;
import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaManagerFeeMeetingAttendanceConverter;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaManagerFeeMeetingAttendanceMapper;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaManagerFeeMeetingAttendanceDvo;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 수수료 생성관리-미팅참석집계(매니저) 서비스
 * </pre>
 *
 * @author minkyu.bae
 * @since 2023-07-24
 */
@Service
@RequiredArgsConstructor
public class WfeaManagerFeeMeetingAttendanceService {

    private final WfeaManagerFeeMeetingAttendanceMapper mapper;
    private final WfeaManagerFeeMeetingAttendanceConverter converter;

    /**
     * 수수료 생성관리-미팅참석집계(매니저) 삭제 후 저장
     * @param dto 삭제 및 저장 조건 정보
     * @return 저장 결과
     */
    @Transactional
    public int saveManagerFeeMeetingAttendances(SaveReq dto) {

        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();

        int processCount = 0;

        WfeaManagerFeeMeetingAttendanceDvo dvo = converter.mapSaveReqToWfeaManagerFeeMeetingAttendanceDvo(dto);

        mapper.deleteManagerFeeMeetingAttendances(dvo);

        processCount = mapper.insertManagerFeeMeetingAttendances(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_AGRG_FAIL");

        return processCount;
    }
}
