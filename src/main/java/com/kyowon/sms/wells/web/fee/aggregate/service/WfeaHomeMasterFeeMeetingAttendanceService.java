package com.kyowon.sms.wells.web.fee.aggregate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.fee.aggregate.converter.WfeaHomeMasterFeeMeetingAttendanceConverter;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto.SaveReq;
import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaHomeMasterFeeMeetingAttendanceDvo;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaHomeMasterFeeMeetingAttendanceMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 수수료 생성관리-미팅참석집계(홈마스터) 서비스
 * </pre>
 *
 * @author minkyu.bae
 * @since 2023-07-24
 */
@Service
@RequiredArgsConstructor
public class WfeaHomeMasterFeeMeetingAttendanceService {

    private final WfeaHomeMasterFeeMeetingAttendanceMapper mapper;
    private final WfeaHomeMasterFeeMeetingAttendanceConverter converter;

    /**
     * 수수료 생성관리-미팅참석집계(홈마스터) 삭제 후 저장
     * @param dto 삭제 및 저장 조건 정보
     * @return 저장 결과
     */
    @Transactional
    public int saveHomeMasterFeeMeetingAttendances(SaveReq dto) {

        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();

        int processCount = 0;

        WfeaHomeMasterFeeMeetingAttendanceDvo dvo = converter.mapSaveReqToWfeaHomeMasterFeeMeetingAttendanceDvo(dto);

        mapper.deleteHomeMasterFeeMeetingAttendances(dvo);

        processCount = mapper.insertHomeMasterFeeMeetingAttendances(dvo);

        BizAssert.isTrue(processCount > 0, "MSG_ALT_AGRG_FAIL");

        return processCount;
    }
}
