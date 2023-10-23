package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaCollectionMaterialsAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaCollectionMaterialsAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaCollectionMaterialsAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 회수자재집계현황 REST API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/collection-materials-agrg")

public class WsnaCollectionMaterialsAgrgController {

    private final WsnaCollectionMaterialsAgrgService service;

    @ApiOperation(value = "회수자재집계현황", notes = "조회조건에 일치하는 회수자재집계현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "확인시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "확인종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고상세구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareNoM", value = "창고번호", paramType = "query"),
        @ApiImplicitParam(name = "wareNoD", value = "창고번호상세", paramType = "query"),

    })
    @GetMapping
    public List<SearchRes> getFilterOutOfStorageAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getFilterOutOfStorageAgrgs(dto);
    }
}
