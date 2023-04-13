package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.service.WsncCapsuleRglrPrchsCstService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/capsule-rglr-prchs-cst")
@Api(tags = "[WSNC] 홈카페 캡슐 정기구매 고객 스케쥴 인서트/업데이트 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncCapsuleRglrPrchsCstController {

    private final WsncCapsuleRglrPrchsCstService service;

    @ApiOperation(value = "홈카페 캡슐 정기구매 고객의 스케쥴 정보를 insert/update한다.")
    @PostMapping
    public SaveResponse saveCapsuleRglrPrchsCsts() {
        return SaveResponse.builder().processCount(service.saveCapsuleRglrPrchsCsts()).build();
    }

}
