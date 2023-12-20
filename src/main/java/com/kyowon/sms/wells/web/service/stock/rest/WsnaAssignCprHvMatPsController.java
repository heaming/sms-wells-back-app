package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignCprHvMatPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignCprHvMatPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAssignCprHvMatPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 배정건 대비 보유 자재 현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/assign-cpr-hv-mat-ps")
public class WsnaAssignCprHvMatPsController {
    private final WsnaAssignCprHvMatPsService service;

    @GetMapping("/paging")
    @ApiOperation(value = "배정건 대비 보유 자재 현황 페이징 조회", notes = "배정건 대비 보유 자재 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "조회시작일", paramType = "query", example = "20230910"),
        @ApiImplicitParam(name = "endDt", value = "조회종료일", paramType = "query", example = "20230911"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "품목구분 하위", paramType = "query"),
        @ApiImplicitParam(name = "sapItemCdFrom", value = "품목코드 조회 시작", paramType = "query"),
        @ApiImplicitParam(name = "sapItemCdTo", value = "품목코드 조회 종료", paramType = "query"),
        @ApiImplicitParam(name = "strtSapCd", value = "SAP코드 조회 종료", paramType = "query"),
        @ApiImplicitParam(name = "endSapCd", value = "SAP코드 조회 종료", paramType = "query"),
        @ApiImplicitParam(name = "wareNoD", value = "창고", paramType = "query"),
        @ApiImplicitParam(name = "wareNoM", value = "빌딩", paramType = "query")
    })
    public PagingResult<SearchRes> getAssignCprHvMatPsPages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAssignCprHvMatPsPages(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    @ApiOperation(value = "배정건 대비 보유 자재 현황 엑셀 다운로드", notes = "배정건 대비 보유 자재 현황 엑셀 다운로드용 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtDt", value = "조회시작일", paramType = "query", example = "20230910"),
        @ApiImplicitParam(name = "endDt", value = "조회종료일", paramType = "query", example = "20230911"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "품목구분 하위", paramType = "query"),
        @ApiImplicitParam(name = "sapItemCdFrom", value = "품목코드 조회 시작", paramType = "query"),
        @ApiImplicitParam(name = "sapItemCdTo", value = "품목코드 조회 종료", paramType = "query"),
        @ApiImplicitParam(name = "strtSapCd", value = "SAP코드 조회 종료", paramType = "query"),
        @ApiImplicitParam(name = "endSapCd", value = "SAP코드 조회 종료", paramType = "query"),
        @ApiImplicitParam(name = "wareNoD", value = "창고", paramType = "query"),
        @ApiImplicitParam(name = "wareNoM", value = "빌딩", paramType = "query")
    })
    public List<SearchRes> getAssignCprHvMatPsExcelDownload(SearchReq dto) {
        return service.getAssignCprHvMatPsExcelDownload(dto);
    }
}
