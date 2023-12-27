package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAsMaterialOutOfStoragePsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/as-material-out-of-storage-ps")
@Api(tags = "[WSNA] A/S 자재 출고 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaAsMaterialOutOfStoragePsController {
    private final WsnaAsMaterialOutOfStoragePsService service;

    @ApiOperation(value = "A/S 자재 출고 현황 조회", notes = "A/S 자재 출고 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "처리시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "처리종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "refriType", value = "유무상구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "serviceType", value = "서비스유형", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "엔지니어", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "installBase", value = "설치기준", paramType = "query"),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "업무유형(서비스업무세분류코드)", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getAsMaterialOutOfStoragePss(
        @Valid
        SearchReq dto
    ) {
        return service.getAsMaterialOutOfStoragePss(dto);
    }
}
