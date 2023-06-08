package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFilterChangeExpectedInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniFilterChangeExpectedInterfaceDto.SearchRes;

import com.kyowon.sms.wells.web.service.interfaces.service.WsniFilterChangeExpectedInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 고객 센터 필터교체 예정정보 인터페이스")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/filter-change-expected")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniFilterChangeExpectedInterfaceController {
    private final WsniFilterChangeExpectedInterfaceService service;

    @ApiOperation(value = "필터교체 예정정보 조회", notes = "고객센터 상담원이 고객대응을 위해 특정 계약에 대한 필터 교체 정보를 조회한다")
    @PostMapping
    public EaiWrapper getFilterChangeExpectedInfos(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getFilterChangeExpectedInfos(reqWrapper.getBody()));
        return resWrapper;
    }
}
