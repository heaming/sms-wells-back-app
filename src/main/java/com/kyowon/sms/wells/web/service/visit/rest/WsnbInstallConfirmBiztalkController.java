package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallConfirmBiztalkService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/install-confirm-biztalks")
@Api(tags = "[WSNB] 설치 확인서 알림톡 발송 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsnbInstallConfirmBiztalkController {
    private WsnbInstallConfirmBiztalkService service;

    @ApiOperation(value = "설치 확인서 알림톡 발송", notes = "제품 설치완료 후 알림톡을 발송한다.")
    @PostMapping
    public SaveResponse sendInstallConfirmBiztalks() throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.sendInstallConfirmBiztalks())
            .build();
    }

}
