package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaPcsvOutOfStorageMgtDto.*;
import com.kyowon.sms.wells.web.service.stock.service.WsnaPcsvOutOfStorageMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 택배설치상품 출고관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/pcsv-out-of-storage")
public class WsnaPcsvOutOfStorageMgtController {

    private final WsnaPcsvOutOfStorageMgtService service;

    @ApiOperation(value = "택배설치상품 출고관리", notes = "조회조건에 일치하는 택배설치상품 출고관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "selCnt", value = "조회제한건수", paramType = "query"),
        @ApiImplicitParam(name = "startDt", value = "계약시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "계약종료일자", paramType = "query"),
        @ApiImplicitParam(name = "vstFshDt", value = "출고확정일자", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "출고구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getPcsvOutOfStorages(
        @Valid
        SearchReq dto
    ) {
        return service.getPcsvOutOfStorages(dto);
    }

    @ApiOperation(value = "택배설치상품 출고관리 엑셀다운로드", notes = "조회조건에 일치하는 택배설치상품 출고관리 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "계약시작일자", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "계약종료일자", paramType = "query"),
        @ApiImplicitParam(name = "vstFshDt", value = "출고확정일자", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "출고구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(SearchReq dto) {
        return service.getPcsvOutOfStoragesExcelDownload(dto);
    }

    @ApiOperation(value = "택배설치상품 출고관리 재고수량 조회", notes = "조회조건에 일치하는 택배설치상품 출고관리 재고 수량 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wkWareNo", value = "창고코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", required = true),
    })
    @GetMapping("/stock-qty")
    public String getPcsvOutOfStorageStockQty(SearchReq dto) {
        return service.getPcsvOutOfStorageStockQty(dto);
    }

    @ApiOperation(value = "택배설치상품 출고관리 저장", notes = "출고관리 정보를 저장한다.")
    @PostMapping
    public SaveResponse savePcsvOutOfStorage(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.savePcsvOutOfStorage(dtos))
            .build();
    }

    @ApiOperation(value = "택배 물류센터 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/logistics-centers")
    public List<LogisticsCentersRes> getPcsvLogisticsCenters() {
        return service.getPcsvLogisticsCenters();
    }

    @ApiOperation(value = "택배 상품 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/products")
    public List<ProductsRes> getPcsvProducts(ProductsReq dto) {
        return service.getPcsvProducts(dto);
    }
}
