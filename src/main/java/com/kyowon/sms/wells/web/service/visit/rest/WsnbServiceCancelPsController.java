package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbServiceCancelPsDto.*;
import com.kyowon.sms.wells.web.service.visit.service.WsnbServiceCancelPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WSNB] 서비스 취소 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/service-cancel-ps")
public class WsnbServiceCancelPsController {
    private final WsnbServiceCancelPsService service;

    //WsnbServiceCancelPsDto
    @ApiOperation(value = "서비스 취소 현황 조회", notes = "조회조건에 따른 서비스 취소 현황 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "serviceType", value = "서비스유형", paramType = "query"),
        @ApiImplicitParam(name = "serviceCenter", value = "서비스센터", paramType = "query"),
        @ApiImplicitParam(name = "engineer", value = "엔지니어", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "wkPrgsStatCd", value = "작업상태", paramType = "query"),
        @ApiImplicitParam(name = "canCaus", value = "취소원인", paramType = "query"),
        @ApiImplicitParam(name = "canRson", value = "취소사유", paramType = "query"),
        @ApiImplicitParam(name = "selectDiv", value = "조회일자 구분", paramType = "query"),
        @ApiImplicitParam(name = "fromDate", value = "조회시작일", paramType = "query"),
        @ApiImplicitParam(name = "toDate", value = "조회종료일", paramType = "query"),
        @ApiImplicitParam(name = "lgstProcsCtYn", value = "물류처리 건 제외 여부", paramType = "query"),
        @ApiImplicitParam(name = "pcsvFwYn", value = "택배발송 제외 여부", paramType = "query"),
        @ApiImplicitParam(name = "dtlIzFltring", value = "상세내역필터링", paramType = "query")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getServiceCancelPsPages(SearchReq dto, @Valid PageInfo pageInfo){
        return service.getServiceCancelPsPages(dto, pageInfo);
    }

    @ApiOperation(value = "서비스 취소 현황 조회 (엑셀 다운로드)", notes = "조회조건에 해당하는 서비스 취소 내역을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getServiceCancelPsExcelDownload(SearchReq dto){
        return service.getServiceCancelPsExcelDownload(dto);
    }
}
