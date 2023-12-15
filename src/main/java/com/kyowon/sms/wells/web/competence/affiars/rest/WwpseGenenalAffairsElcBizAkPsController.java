package com.kyowon.sms.wells.web.competence.affiars.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.service.WwpseGenenalAffairsElcBizAkPsService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 총무전자업무요청 현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/electronic-business-ak-ps")
public class WwpseGenenalAffairsElcBizAkPsController {

    private final WwpseGenenalAffairsElcBizAkPsService service;

    @ApiOperation(value = "총무전자업무요청 현황 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<WwpseGenenalAffairsElcBizAkPsDto.SearchRes> getGenenalAffairsElcBizAkPsPages(
        @Valid
        WwpseGenenalAffairsElcBizAkPsDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getGenenalAffairsElcBizAkPsPages(dto, pageInfo);
    }

    @ApiOperation(value = "총무전자업무요청 현황 업무요청타입", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type")
    public List<WwpseGenenalAffairsElcBizAkPsDto.businessTypeRes> getBusinessType(
        @Valid
        WwpseGenenalAffairsElcBizAkPsDto.BaseSearchReq dto
    ) {
        return service.getBusinessType(dto);
    }

    @ApiOperation(value = "총무전자업무요청 현황 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<WwpseGenenalAffairsElcBizAkPsDto.SearchRes> getGenenalAffairsElcBizAkPssForExcelDownload(
        @Valid
        WwpseGenenalAffairsElcBizAkPsDto.SearchReq dto
    ) {
        return service.getGenenalAffairsElcBizAkPssForExcelDownload(dto);
    }
}
