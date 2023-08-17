package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaFilterOutOfStorageAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 필터출고집계현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/filter-out-of-storage-agrg")
public class WsnaFilterOutOfStorageAgrgController {

    private final WsnaFilterOutOfStorageAgrgService service;

    @ApiOperation(value = "필터출고집계현황", notes = "조회조건에 일치하는 필터출고집계현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "확인시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "확인종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query"),

    })
    @GetMapping
    public List<HashMap<String, String>> getDisuseOutOfStorageAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getFilterOutOfStorageAgrgs(dto);
    }
}
