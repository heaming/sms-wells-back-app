package com.kyowon.sms.wells.web.fee.aggregate.rest;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeMeetingAttendanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaHomeMasterFeeMeetingAttendanceService;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaManagerFeeMeetingAttendanceService;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaPlannerFeeMeetingAttendanceService;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WFEA] 수수료 생성관리-미팅참석집계(wells)")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/fee-meeting-attendances")
@Slf4j
public class WfeaFeeMeetingAttendanceController {
    private final WfeaManagerFeeMeetingAttendanceService mngerService;
    private final WfeaPlannerFeeMeetingAttendanceService plarService;
    private final WfeaHomeMasterFeeMeetingAttendanceService Hmstservice;

    @ApiOperation(value = "수수료 생성관리-미팅참석집계(웰스) 저장", notes = "수수료 생성관리-미팅참석집계을 저장한다.")
    @PostMapping
    public SaveResponse saveWellsFeeMeetingAttendances(
        @RequestBody
        @Valid
        WfeaFeeMeetingAttendanceDto.SaveReq dto
    ) throws Exception {
        int processCount = 0;
        if (dto.ogTpCd().equals("W01")) {
            processCount = plarService.savePlannerFeeMeetingAttendances(dto);
        } else if (dto.ogTpCd().equals("W02")) {
            processCount = mngerService.saveManagerFeeMeetingAttendances(dto);
        } else if (dto.ogTpCd().equals("W03")) {
            processCount = Hmstservice.saveHomeMasterFeeMeetingAttendances(dto);
        }
        return SaveResponse.builder()
            .processCount(processCount)
            .build();
    }
}
