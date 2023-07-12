package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSeedingShippingDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniSeedingShippingInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": wells홈페이지 모종배송내역 조회")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/seeding-shipping")
//@Api(tags = "[WSNB] wells홈페이지 모종배송내역 조회")
@RequiredArgsConstructor
@Validated
public class WsniSeedingShippingInterfaceController {

    private final WsniSeedingShippingInterfaceService service;

    @ApiOperation(value = "wells홈페이지 모종배송내역 조회", notes = "Wells홈페이지 모종배송내역 조회 API 이다.")
    @PostMapping
    public EaiWrapper getSeedingShippings(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<List<SearchRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getSeedingShippings(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }
}
