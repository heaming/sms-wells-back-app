package com.kyowon.sms.wells.web.service.adrwork.rest;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfAsEnrgMatMngtDto.*;
import com.kyowon.sms.wells.web.service.adrwork.service.WsnfAsEnrgMatMngtService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "[WSNF] W-SV-U-0198M01 AS유형별 권장자재 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/as-encourage-materials-mngt")
@Slf4j
public class WsnfAsEnrgMatMngtController {

    private final WsnfAsEnrgMatMngtService service;

    @ApiOperation(value = "배정정보 조회", notes = "배정정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "vstCnfmdtStart", value = "방문확정일", paramType = "query"),
        @ApiImplicitParam(name = "vstCnfmdtEnd", value = "방문확정일", paramType = "query"),
        @ApiImplicitParam(name = "rcpdtStart", value = "접수일자", paramType = "query"),
        @ApiImplicitParam(name = "rcpdtEnd", value = "접수일자", paramType = "query"),
        @ApiImplicitParam(name = "vstDuedtStart", value = "예정일자", paramType = "query"),
        @ApiImplicitParam(name = "vstDuedtEnd", value = "예정일자", paramType = "query"),
        @ApiImplicitParam(name = "wkExcnDtStart", value = "처리일자", paramType = "query"),
        @ApiImplicitParam(name = "wkExcnDtEnd", value = "처리일자", paramType = "query"),
    })
    @GetMapping
    public PagingResult<SearchRes> getAsEncourageMaterials(
        SearchReq dto, @Valid PageInfo pageInfo
    ) {
        return service.getAsEncourageMaterials(dto, pageInfo);
    }
}
