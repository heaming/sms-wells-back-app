package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductReturningGoodsMgtDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMdProductReturningGoodsMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] MD상품 반품관리 REST API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(SnServiceConst.REST_URL_V1 + "/md-product-returning-goods")

public class WsnaMdProductReturningGoodsMgtController {

    private final WsnaMdProductReturningGoodsMgtService service;

    @ApiOperation(value = "MD상품 반품관리 목록 조회", notes = "조회조건에 일치하는 MD상품 반품관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "startDt", value = "요청시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "요청종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대폰번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대폰번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대폰번호3", paramType = "query"),
        @ApiImplicitParam(name = "bcNo", value = "제품시리얼넘버", paramType = "query"),
        @ApiImplicitParam(name = "prtnrBzsCd", value = "파트너업체코드", paramType = "query"),
    })
    @GetMapping
    public List<WsnaMdProductReturningGoodsMgtDvo> getMdProductReturningGoods(
        @Valid
        SearchReq dto
    ) {
        return service.getMdProductReturningGoods(dto);
    }

    @ApiOperation(value = "MD상품 반품 등록/취소/수불처리 저장", notes = "MD상품 등록/취소/수불처리 정보를 저장한다.")
    @PostMapping
    public SaveResponse saveMdProductReturningGoods(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveMdProductReturningGoods(dtos))
            .build();
    }

}
