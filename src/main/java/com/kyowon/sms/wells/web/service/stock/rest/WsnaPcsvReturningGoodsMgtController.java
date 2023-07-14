package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvReturningGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaPcsvReturningGoodsMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] 택배설치상품 반품관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/pcsv-returning-goods")

public class WsnaPcsvReturningGoodsMgtController {

    private final WsnaPcsvReturningGoodsMgtService service;

    @ApiOperation(value = "택배설치상품 반품관리 목록 조회", notes = "조회조건에 일치하는 택배설치상품 반품관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "startDt", value = "요청시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "요청종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "계약고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대폰번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대폰번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대폰번호3", paramType = "query"),
        @ApiImplicitParam(name = "bcNo", value = "제품시리얼넘버", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getPcsvReturningGoodsPages(
        @Valid
        SearchReq dto
    ) {
        return service.getPcsvReturningGoods(dto);
    }

    @ApiOperation(value = "택배설치상품 반품관리 엑셀다운로드", notes = "조회조건에 일치하는 택배설치상품 반품관리 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "startDt", value = "요청시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "요청종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "휴대폰번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "휴대폰번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "휴대폰번호3", paramType = "query"),
        @ApiImplicitParam(name = "bcNo", value = "제품시리얼넘버", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(SearchReq dto) {

        return service.getPcsvReturningGoodsExcelDownload(dto);
    }

    @ApiOperation(value = "택배설치상품 반품 등록/취소/수불처리 저장", notes = "반품관리 반품 등록/취소/수불처리 정보를 저장한다.")
    @PostMapping
    public SaveResponse savePcsvReturningGoods(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {

        return SaveResponse.builder()
            .processCount(service.savePcsvReturningGoods(dtos))
            .build();
    }

    @ApiOperation(value = "택배 물류센터 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/logistics-centers")
    public List<WsnaPcsvReturningGoodsMgtDto.FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return service.getPcsvLogisticsCenters();
    }

    @ApiOperation(value = "택배 상품 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/products")
    public List<WsnaPcsvReturningGoodsMgtDto.FindProductsRes> getPcsvProducts(
        WsnaPcsvReturningGoodsMgtDto.FindProductsReq dto
    ) {
        return service.getPcsvProducts(dto);
    }

}
