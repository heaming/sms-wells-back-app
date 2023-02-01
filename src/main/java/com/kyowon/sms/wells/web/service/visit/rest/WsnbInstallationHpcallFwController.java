package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallationHpcallFwService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 설치, A/S 해피콜 발송(리뉴얼)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "installation-hpcall-fw")
public class WsnbInstallationHpcallFwController {

    private final WsnbInstallationHpcallFwService service;

    @ApiOperation(value = "설치, A/S 해피콜 발송(리뉴얼)", notes = "설치, A/S 해피콜을 발송한다.")
    @PostMapping
    public SaveResponse sendInstallationHpcallFws() throws Exception {
        return SaveResponse.builder()
            .processCount(service.sendInstallationHpcallFws())
            .build();
    }
}
