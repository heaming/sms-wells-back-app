package com.kyowon.sms.wells.web.service.adrwork.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.adrwork.dvo.WsnfAsEnrgMatMngtDvo;
import com.kyowon.sms.wells.web.service.adrwork.service.WsnfAsEnrgMatMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNF] W-SV-U-0198M01 AS유형별 필요자재 관리")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/as-encourage-materials-mngt")
public class WsnfAsEnrgMatMngtController {

    private final WsnfAsEnrgMatMngtService service;

    @ApiOperation(value = "AS유형별 필요자재 관리 조회", notes = "AS유형별 필요자재 관리 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sapMatCd", value = "SAP코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "품목코드", paramType = "query"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query"),
        @ApiImplicitParam(name = "itmPdCd", value = "추천자재코드", paramType = "query"),
        @ApiImplicitParam(name = "itmPdNm", value = "추천자재", paramType = "query"),
        @ApiImplicitParam(name = "cnslTpLcsfCdNm", value = "접수증상", paramType = "query"),
        @ApiImplicitParam(name = "cnslCn", value = "접수증상상세", paramType = "query"),
        @ApiImplicitParam(name = "itmRcmdRnk", value = "추천순위", paramType = "query"),
        @ApiImplicitParam(name = "itmRcmdQty", value = "수량", paramType = "query"),
    })
    @GetMapping
    public PagingResult<WsnfAsEnrgMatMngtDvo> getAsEncourageMaterials(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAsEncourageMaterials(dto, pageInfo);
    }

    @ApiOperation(value = "AS 유형별 필요자재관리 조회 엑셀 다운로드", notes = "검색 조건을 입력받아 AS 유형별 필요자재관리를 엑셀 다운로드한다.")
    @GetMapping("/excel-download")
    public List<WsnfAsEnrgMatMngtDvo> getAsEncourageMaterialsForExcelDowload(SearchReq dto) {
        return service.getAsEncourageMaterialsForExcelDowload(dto);
    }

}
