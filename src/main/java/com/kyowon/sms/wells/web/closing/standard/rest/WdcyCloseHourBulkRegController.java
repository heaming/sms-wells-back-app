package com.kyowon.sms.wells.web.closing.standard.rest;

import com.kyowon.sms.wells.web.closing.standard.dto.WdcyCloseHourBulkRegDto.CreateReq;
import com.kyowon.sms.wells.web.closing.standard.service.WdcyCloseHourBulkRegService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "[WDCY] 마감시간 일괄등록")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/standard")
public class WdcyCloseHourBulkRegController {

    private final WdcyCloseHourBulkRegService service;

    @ApiOperation(value = "마감시간 일괄등록", notes = "마감시간 일괄등록")
    @PostMapping
    public SaveResponse createCloseHour(
        @RequestBody
        @Valid
        CreateReq dto
    ) {
        return SaveResponse.builder().processCount(service.createCloseHour(dto)).build();
    }
}
