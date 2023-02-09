package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbBusinessAsBiztalkService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/business-as-biztalks")
@Api(tags = "[WSNB] 고객센터에서 접수한 아웃소싱 업체 여러건 AS건에 대해 알림톡 발송 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsnbBusinessAsBiztalkController {
    private WsnbBusinessAsBiztalkService service;

    @ApiOperation(value = "고객센터에서 접수한 아웃소싱 업체 여러건 AS건에 대해 알림톡 발송", notes = "고객센터에서 접수한 아웃소싱 업체 어리건 AS건에 대하여 고객에게 알림톡으로 발송한다. .")
    @PostMapping
    public SaveResponse sendBusinessAsBiztalks() throws Exception {
        return SaveResponse.builder()
            .processCount(service.sendBusinessAsBiztalks())
            .build();
    }

}
