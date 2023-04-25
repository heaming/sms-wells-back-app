package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcPartnerEngineerService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
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
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SearchEngineerReq;

import java.util.List;

@RestController
@Api(tags = "[WOGC] 플래너 관리 REST API")
@Validated
@RequiredArgsConstructor
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/partner-engineer")
public class WogcPartnerEngineerController {
    private final WogcPartnerEngineerService service;

    @ApiOperation(value = "엔지니어 출근 관리 조회", notes = "조회 조건에 일치하는 엔지니어 출근관리 목록을 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseYm", value = "기준월", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseDt", value = "기준일자", paramType = "query", required = false),

    })
    @GetMapping("/attend/paging")
    PagingResult<SearchEngineerRes> getEngineerAttends(SearchEngineerReq dto, PageInfo pageInfo) {
        return service.getEngineerAttends(dto, pageInfo);
    }

    @ApiOperation(value = "엔지니어 출근 관리 엑셀다운로드", notes = "조회 조건에 일치하는 엔지니어 출근 관리 목록을 엑셀로 저장한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseYm", value = "기준월", paramType = "query", required = false),
        @ApiImplicitParam(name = "baseDt", value = "기준일자", paramType = "query", required = false),

    })
    @GetMapping("/attend/excel-download")
    List<SearchEngineerRes> getEngineerAttendsForExcelDownload(SearchEngineerReq dto) {
        return service.getEngineerAttendsForExcelDownload(dto);
    }
}
