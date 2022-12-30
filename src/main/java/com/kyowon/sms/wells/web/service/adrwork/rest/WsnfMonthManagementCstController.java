package com.kyowon.sms.wells.web.service.adrwork.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfMonthManagementCstDto;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNF] 월관리 고객 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/month-management")
@Slf4j
public class WsnfMonthManagementCstController {

    @ApiOperation(value = "월관리 고객 생성 - 생성", notes = "월관리 고객 생성")
    @PostMapping
    public SaveResponse createMonthManagementCst(
        @Valid
        @RequestBody
        WsnfMonthManagementCstDto.CreateReq dto
    ) throws Exception {
        log.info(
            "[WsnfMonthManagementCstController.createMonthManagementCst] WsnfMonthManagementCstJob-Create batch Job execute."
                + dto.managementYm() + " / " + dto.createTarget()
        );

        //TODO Job Execution

        return SaveResponse.builder()
            .processCount(1)
            .build();
    }

    @ApiOperation(value = "월관리 고객 생성 - 생성", notes = "월관리 고객 생성")
    @DeleteMapping
    public SaveResponse deleteMonthManagementCst(
        @Valid
        @RequestBody
        WsnfMonthManagementCstDto.RemoveReq dto
    ) throws Exception {
        log.info(
            "[WsnfMonthManagementCstController.deleteMonthManagementCst] WsnfMonthManagementCstJob-Delete batch Job execute."
                + dto.managementYm() + " / " + dto.createTarget()
        );

        //TODO Job Execution

        return SaveResponse.builder()
            .processCount(1)
            .build();
    }

}
