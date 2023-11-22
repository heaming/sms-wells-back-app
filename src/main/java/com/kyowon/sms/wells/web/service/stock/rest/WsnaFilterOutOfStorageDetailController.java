package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageDetailDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaFilterOutOfStorageDetailDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaFilterOutOfStorageDetailService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 필터출고현황(세부) REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/filter-out-of-storage-detail")
public class WsnaFilterOutOfStorageDetailController {

    private final WsnaFilterOutOfStorageDetailService service;

    @ApiOperation(value = "필터출고현황(세부) 목록", notes = "조회조건에 일치하는 필터출고현황(세부) 목록 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "출고시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareNoM", value = "창고번호", paramType = "query"),
        @ApiImplicitParam(name = "wareNoD", value = "창고번호상세", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "itmCdFrom", value = "품목코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdTo", value = "품목코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query"),
        @ApiImplicitParam(name = "fnlSellTpCd", value = "고객유형", paramType = "query"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "서비스업무대분류코드", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리자구분코드", paramType = "query"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외 여부", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getFilterOutOfStorageDetailPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getFilterOutOfStorageDetailPages(dto, pageInfo);
    }

    @ApiOperation(value = "필터출고현황(세부) 목록 엑셀다운로드", notes = "조회조건에 일치하는 필터출고현황(세부) 목록 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "출고시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "출고종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareNoM", value = "창고번호", paramType = "query"),
        @ApiImplicitParam(name = "wareNoD", value = "창고번호상세", paramType = "query"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "itmCdFrom", value = "품목코드(시작)", paramType = "query"),
        @ApiImplicitParam(name = "itmCdTo", value = "품목코드(종료)", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query"),
        @ApiImplicitParam(name = "fnlSellTpCd", value = "고객유형", paramType = "query"),
        @ApiImplicitParam(name = "svBizHclsfCd", value = "서비스업무대분류코드", paramType = "query"),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리자구분코드", paramType = "query"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자제외 여부", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getEtcOutOfStorageRsonsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getFilterOutOfStorageDetailsExcelDownload(dto);
    }

}
