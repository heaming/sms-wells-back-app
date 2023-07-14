package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCapsulepkgChSppinfDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCapsulepkgChSppinfDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniCapsulepkgChSppinfInterfaceService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/capsulepkg-ch-sppinf")
//@Api(tags = "[WSNB] Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트")
@RequiredArgsConstructor
@Validated
public class WsniCapsulepkgChSppinfInterfaceController {

    private final WsniCapsulepkgChSppinfInterfaceService service;

    @ApiOperation(value = "Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트", notes = "Wells홈페이지 홈카페 캡슐 패키지 변경을 위한 배송 정보 리스트 조회 API이다.")
    @PostMapping
    public EaiWrapper getCapsulepkgChSppinfs(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<List<SearchRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getCapsulepkgChSppinfs(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }
}
