package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaDisuseOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaDisuseOutOfStorageAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaDisuseOutOfStorageAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 폐기출고집계현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/disuse-out-of-storage-agrg")
public class WsnaDisuseOutOfStorageAgrgController {

    private final WsnaDisuseOutOfStorageAgrgService service;

    @ApiOperation(value = "폐기출고집계현황", notes = "조회조건에 일치하는 폐기출고집계현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "확인시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "확인종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "품목등급코드", paramType = "query"),
        @ApiImplicitParam(name = "rtngdProcsTpCd", value = "반품처리유형", paramType = "query"),
        @ApiImplicitParam(name = "itmCdFrom", value = "품목코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdTo", value = "품목코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),

    })
    @GetMapping
    public List<SearchRes> getDisuseOutOfStorageAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getDisuseOutOfStorageAgrgs(dto);
    }
}
