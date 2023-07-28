package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.FindItemRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaOutOfStorageAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 출고집계현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/out-of-storage-agrg")
public class WsnaOutOfStorageAgrgController {

    private final WsnaOutOfStorageAgrgService service;

    @ApiOperation(value = "출고집계현황", notes = "조회조건에 일치하는 출고집계현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "출고시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query"),
        @ApiImplicitParam(name = "sapMatCdFrom", value = "SAP코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "sapMatCdTo", value = "SAP코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdFrom", value = "품목코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdTo", value = "품목코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목", paramType = "query"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
    })
    @GetMapping
    public List<HashMap<String, String>> getOutOfStorageAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getOutOfStorageAgrgs(dto);
    }

    @ApiOperation(value = "출고집계현황 엑셀다운로드", notes = "조회조건에 일치하는 출고집계현황 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "출고시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "ostrTpCd", value = "출고유형", paramType = "query"),
        @ApiImplicitParam(name = "sapMatCdFrom", value = "SAP코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "sapMatCdTo", value = "SAP코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdFrom", value = "품목코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdTo", value = "품목코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCd", value = "품목", paramType = "query"),
        @ApiImplicitParam(name = "matUtlzDvCd", value = "자재구분", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<HashMap<String, String>> excelDownload(SearchReq dto) {
        return service.getOutOfStorageAgrgExcelDownload(dto);
    }

    @GetMapping("/ware-houses")
    @ApiOperation(value = "출고집계현황 창고 조회", notes = "출고집계현황 창고를 조회한다.")
    public List<WsnzWellsCodeWareHouseDvo> getWareHouseNames() {
        return this.service.getWareHouses();
    }

    @ApiOperation(value = "조회조건에 해당하는 품목코드 조회", notes = "조회조건에 해당하는 품목코드를 조회한다.")
    @GetMapping("/filter-items")
    public List<FindItemRes> getItemProductCodes(
        SearchReq dto
    ) {
        return this.service.getItemProductCodes(dto);
    }

}
