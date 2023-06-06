package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterHouseholdInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCenterHouseholdInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCenterHouseholdInterfaceService;
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
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 고객 센터 인터페이스 가구화 정보 조회")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/search-household")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsniCenterHouseholdInterfaceController {

    private final WsniCenterHouseholdInterfaceService service;

    @ApiOperation(value = "가구화 정보 조회", notes = "고객센터에서 특정 계약번호에 가구화로 묶여있는 계약정보를 조회한다")
    @PostMapping
    public EaiWrapper getCustomerHouseholds(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqWrapper
    ) {
        EaiWrapper<List<SearchRes>> resWrapper = reqWrapper.newResInstance();
        resWrapper.setBody(service.getCustomerHouseholds(reqWrapper.getBody()));
        return resWrapper;
    }
}
