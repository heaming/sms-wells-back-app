package com.kyowon.sms.wells.web.fee.aggregate.rest;

import static com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNchnFeeDto.SaveReq;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaNchnFeeService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/nchn-fees")
@Api(tags = "[WFEA] 신채널 수수료 생성관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeaNchnFeeController {

    private final WfeaNchnFeeService service;

    @ApiOperation(value = "신채널 실적 집계", notes = "실적년월의 신채널 실적을 집계한다.")
    @PostMapping
    public SaveResponse aggregateNchnPerformances(
        @Valid @RequestBody
        SaveReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.aggregateNchnPerformances(dto))
            .build();
    }
}
