package com.kyowon.sms.wells.web.closing.performance.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountHomeCardDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccProductAccountHomeCardService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCC] 홈카드 계정순증 - (W-CL-U-1101M01)")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/product-account")
public class WdccProductAccountHomeCardController {
    private final WdccProductAccountHomeCardService service;

    /**
     * 홈카드 계정순증
     */
    @ApiOperation(value = "홈카드의 계정순증(상품별 계정현황)", notes = "홈카드의 계정순증(상품별 계정현황)")
    @GetMapping("/homeCard/accNinc")
    public SearchRes getProductAccountHomeCard() {
        return service.getProductAccountHomeCard();
    }
}
