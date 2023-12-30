package com.kyowon.sms.wells.web.deduction.adsb.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAwAdsbMgtDto.CreateReq;
import com.kyowon.sms.wells.web.deduction.adsb.service.WdebAwAdsbMgtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEB] 재지급 대상 생성")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/adsb/allowance")
public class WdebAwAdsbMgtController {
    private final WdebAwAdsbMgtService service;

    @ApiOperation(value = "재지급 대상 생성", notes = "재지급 대상을 생성한다.")
    @PostMapping("/object")
    public SaveResponse saveAdsbObjectCreates(
        @RequestBody
        @Valid
        CreateReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveAdsbObjectCreates(dto))
            .build();

    }

}
