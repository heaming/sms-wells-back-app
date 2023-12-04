package com.kyowon.sms.wells.web.service.interfaces.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniSidingServiceChangesService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.annotation.InterfaceController;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@InterfaceController
@Api(tags = SnServiceConst.REST_INTERFACE_DOC_V1 + ": 모종 상품/서비스 변경 처리")
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/siding-product-service-change")
@RequiredArgsConstructor
@Validated
public class WsniSidingServiceChangesController {

    private final WsniSidingServiceChangesService service;

    @ApiOperation(value = "모종 상품/서비스 변경 처리", notes = "EAI_WSVI1002 W-SV-I-0025 모종 상품/서비스 변경 처리하는 API")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약순번", paramType = "query", required = true),
        @ApiImplicitParam(name = "asAkDvCd", value = "AS요청구분코드 (1:패키지변경, 4:차월 방문 중지)", paramType = "query", required = true),
        @ApiImplicitParam(name = "akChdt", value = "요청변경일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "bfchPdCd", value = "변경전상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "afchPdCd", value = "변경후상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "choCapslCn", value = "제품상품코드목록", paramType = "query"),
        @ApiImplicitParam(name = "mtrProcsStatCd", value = "자료처리상태코드 (1:신규, 2:변경, 3:취소)", paramType = "query", required = true),
    })
    @PostMapping
    public EaiWrapper saveSidingPackageOrProductChange(
        @Valid
        @RequestBody
        EaiWrapper<SaveReq> reqEaiWrapper
    ) throws Exception {
        EaiWrapper<SaveRes> resEaiWrapper = reqEaiWrapper.newResInstance();
        resEaiWrapper.setBody(service.saveSidingPackageOrProductChange(reqEaiWrapper.getBody()));
        return resEaiWrapper;
    }

}
