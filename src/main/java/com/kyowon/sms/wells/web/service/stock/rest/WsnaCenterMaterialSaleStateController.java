package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaCenterMaterialSaleStateDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaCenterMaterialSaleStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] A/S(센터) 자재 매출 현황 REST API")
@RestController
@RequiredArgsConstructor
@Valid
@RequestMapping(SnServiceConst.REST_URL_V1 + "/center-material-sale-state")

public class WsnaCenterMaterialSaleStateController {
    @ApiOperation(value = "A/S(센터) 자재 매출 현황 목록 조회", notes = "조회조건에 일치하는 A/S(센터) 자재 매출 현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "확인일자시작", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "확인일자종료", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분(대)", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareNoM", value = "창고구분(중)", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareNoD", value = "창고구분(소)", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "창고구분코드상세", paramType = "query", required = true),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "itmCdFrom", value = "품목코드조회시작", paramType = "query"),
        @ApiImplicitParam(name = "itmCdTo", value = "품목코드조회종료", paramType = "query"),
    })
    @GetMapping
    public List<WsnaCenterMaterialSaleStateDto.SearchRes> getCenterMaterialSaleStates(
        @Valid
        WsnaCenterMaterialSaleStateDto.SearchReq dto
    ) {
        return service.getCenterMaterialSaleStates(dto);
    }

    private final WsnaCenterMaterialSaleStateService service;

}
