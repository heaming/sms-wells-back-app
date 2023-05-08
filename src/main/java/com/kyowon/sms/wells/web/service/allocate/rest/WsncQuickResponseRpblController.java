package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncQuickResponseRpblDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncQuickResponseRpblService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 고객 QR코드 재발행 내역 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/quick-response-rpbls")
@Slf4j
public class WsncQuickResponseRpblController {

    private final WsncQuickResponseRpblService service;

    @ApiOperation(value = "고객 QR코드 재발행 내역 조회", notes = "조회조건에 따른 고객 QR코드 재발행 내역 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "pdPrpVal20", value = "상품그룹", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "사번", paramType = "query", required = false),
        @ApiImplicitParam(name = "mngtDptmtCd", value = "총괄단", paramType = "query", required = false),
        @ApiImplicitParam(name = "rgnlGrpCd", value = "지역단", paramType = "query", required = false),
        @ApiImplicitParam(name = "branchCd", value = "지점", paramType = "query", required = false),
        @ApiImplicitParam(name = "mngrCd", value = "매니저", paramType = "query", required = false),
        @ApiImplicitParam(name = "svcCntrCd", value = "서비스센터", paramType = "query", required = false),
        @ApiImplicitParam(name = "engineerCd", value = "엔지니어", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<WsncQuickResponseRpblDto.SearchRes> getQuickResponseRpbls(
        WsncQuickResponseRpblDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getQuickResponseRpbls(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<WsncQuickResponseRpblDto.SearchRes> getQuickResponseRpblsExcelDownload(
        WsncQuickResponseRpblDto.SearchReq dto
    ) {
        return service.getQuickResponseRpblsExcelDownload(dto);
    }
}
