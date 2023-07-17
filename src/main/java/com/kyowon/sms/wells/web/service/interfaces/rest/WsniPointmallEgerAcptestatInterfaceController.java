package com.kyowon.sms.wells.web.service.interfaces.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniPointmallEgerAcptestatDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniPointmallEgerAcptestatDto.SearchRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniPointmallEgerAcptestatService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 포인트몰 금융리스 안마의자,전기레인지 엔지니어 수락상태값 조회")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/pointmall-eger-acptestat")
@RequiredArgsConstructor
@Validated
public class WsniPointmallEgerAcptestatInterfaceController {

    private final WsniPointmallEgerAcptestatService service;

    @ApiOperation(value = "포인트몰 금융리스 안마의자,전기레인지 엔지니어 수락상태값 조회", notes = "포인트몰 금융리스 안마의자, 전기레인지 엔지니어 수락상태값 정보를 조회하는 인터페이스이다.")
    @PostMapping
    public EaiWrapper getPointmallEgerAcptestats(
        @Valid
        @RequestBody
        EaiWrapper<SearchReq> reqEaiWrapper
    ) {
        EaiWrapper<List<SearchRes>> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.getPointmallEgerAcptestats(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }
}
