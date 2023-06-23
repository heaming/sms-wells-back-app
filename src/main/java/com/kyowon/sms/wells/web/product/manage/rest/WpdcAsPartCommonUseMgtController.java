package com.kyowon.sms.wells.web.product.manage.rest;

import static com.kyowon.sms.wells.web.product.manage.dto.WpdcAsPartCommonUseMgtDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.product.manage.service.WpdcAsPartCommonUseMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WPDC] 상품 >> 상품운영관리 >> AS부품 공용관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/as-common-uses")
public class WpdcAsPartCommonUseMgtController {

    private final WpdcAsPartCommonUseMgtService service;

    @ApiOperation(value = "AS부품 목록 조회", notes = "조회조건에 따라 AS부품 리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdtCateHigh", value = "상품대분류ID", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prdtCateMid", value = "상품중분류ID", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "prdtCateLow", value = "상품소분류ID", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "asMatItmKndCd", value = "품목종류코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "asMatItmGrpCd", value = "품목그룹코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "svMatGrpCd", value = "자재그룹코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "pdCd", value = "제품코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "sapMatCd", value = "자재코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "sapItemCdFrom", value = "시작품목코드", paramType = "query", required = false, example = ""),
        @ApiImplicitParam(name = "sapItemCdTo", value = "종료품목코드", paramType = "query", required = false, example = ""),
    })
    @GetMapping("/parts")
    public List<SearchPartRes> getAsParts(@Valid SearchPartReq dto) {
        return service.getAsParts(dto);
    }

    @ApiOperation(value = "AS부품 관련 제품 목록 조회", notes = "AS부품과 연결된 제품 리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "partPdCd", value = "부품코드", paramType = "query", required = false, example = ""),
    })
    @GetMapping("/products/{partPdCd}")
    public List<SearchProductRes> getProductsByPart(@PathVariable String partPdCd) {
        return service.getProductsByPart(partPdCd);
    }

}
