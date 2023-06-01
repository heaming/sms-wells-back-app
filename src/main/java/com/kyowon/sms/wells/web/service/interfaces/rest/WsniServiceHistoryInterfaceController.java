package com.kyowon.sms.wells.web.service.interfaces.rest;

import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchReq;
import static com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.service.WsniServiceHistoryInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 고객 센터 인터페이스 서비스 이력 조회")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/service-history")
@RequiredArgsConstructor
@Validated
public class WsniServiceHistoryInterfaceController {

    private final WsniServiceHistoryInterfaceService service;

    @ApiOperation(value = "W-SV-I-0039 고객 센터 인터페이스 서비스 이력 조회")
    @PostMapping
    public EaiWrapper getServiceHistory(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        // Response EaiWrapper
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();

        List<SearchRes> res = service.getServiceHistory(reqWrapper.getBody());

        // Response Body Setting
        resWrapper.setBody(res);

        return resWrapper;
    }
}
