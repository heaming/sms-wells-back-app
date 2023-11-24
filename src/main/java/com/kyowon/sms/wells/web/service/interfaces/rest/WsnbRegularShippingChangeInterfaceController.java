package com.kyowon.sms.wells.web.service.interfaces.rest;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsnbRegularShippingChangeDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsnbRegularShippingChangeService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@InterfaceController
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/regular-shipping-change")
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": [WSNB] 정기배송 변경 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbRegularShippingChangeInterfaceController {

    private final WsnbRegularShippingChangeService service;

    @ApiOperation(value = "홈까패 캡슐 정기배송 변경처리")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약순번", paramType = "query", required = true),
        @ApiImplicitParam(name = "asAkDvCd", value = "AS요청구분코드 (1:패키지변경, 4:차월 방문 중지)", paramType = "query", required = true),
        @ApiImplicitParam(name = "akChdt", value = "요청변경일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "bfchPdCd", value = "변경전상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "afchPdCd", value = "변경후상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "mtrProcsStatCd", value = "자료처리상태코드 (1:신규, 2:변경, 3:취소)", paramType = "query", required = true),
        @ApiImplicitParam(name = "choCapslCn", value = "판매코드,수량 | 판매코드, 수량 | ...", paramType = "query", required = true),
    })
    @PostMapping
    public EaiWrapper editRegularShippingChange(
        @RequestBody
        @Valid
        EaiWrapper<SaveReq> reqEaiWrapper
    ) throws Exception {
        EaiWrapper<SaveRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.saveRegularShippingChange(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }
}
