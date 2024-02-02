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
        @ApiImplicitParam(name = "startDt", value = "계약시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "계약종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "lgstWkMthdCd", value = "물류작업코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "출고구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "wkWareNo", value = "창고번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "vstFshDt", value = "출고확정일자", paramType = "query"),
        @ApiImplicitParam(name = "selCnt", value = "조회제한건수", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getPcsvOutOfStorages(
        @Valid
        SearchReq dto
    ) {
        return service.getPcsvOutOfStorages(dto);
    }

    @ApiOperation(value = "택배설치상품 출고관리 저장", notes = "출고관리 정보를 저장한다.")
    @PostMapping
    public SaveResponse savePcsvOutOfStorages(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.savePcsvOutOfStorages(dtos))
            .build();
    }

    @ApiOperation(value = "택배 물류센터 목록 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/logistics-centers")
    public List<FindLogisticsCentersRes> getPcsvLogisticsCenters() {
        return service.getPcsvLogisticsCenters();
    }

    @ApiOperation(value = "택배 상품 목록 조회", notes = "조회조건에 일치하는 정보를 조회한다.")
    @GetMapping("/products")
    public List<FindProductsRes> getPcsvProducts() {
        return service.getPcsvProducts();
    }

}
