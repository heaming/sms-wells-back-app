package com.kyowon.sms.wells.web.competence.affiars.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;
import com.kyowon.sms.wells.web.competence.affiars.service.WwpseGenenalAffairsElcBizAkService;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] 총무전자업무요청")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/electronic-business-ak")
public class WwpseGenenalAffairsElcBizAkController {

    private final WwpseGenenalAffairsElcBizAkService service;

    @ApiOperation(value = "총무전자업무요청 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getGenenalAffairsElcBizAkPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getGenenalAffairsElcBizAkPages(dto, pageInfo);
    }

    @ApiOperation(value = "총무전자업무요청 팝업", notes = "")
    @GetMapping("/business-popup")
    public SearchRes getBusinessPopup(
        @Valid
        SearchReq dto
    ) {
        return service.getRentManagementPopup(dto);
    }

    @ApiOperation(value = "총무전자업무요청 업무요청타입", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type")
    public Map<String, List> getBusinessType(
        @Valid
        BaseSearchReq dto
    ) {
        return service.getBusinessType(dto);
    }

    @ApiOperation(value = "총무전자업무요청 기본정보", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/base-information")
    public List<BaseSearchRes> getBaseInfo(
        @Valid
        BaseSearchReq dto
    ) {
        return service.getBaseInfo(dto);
    }

    @ApiOperation(value = "총무전자업무요청 저장", notes = "")
    @PostMapping("/genenal-affairs")
    public SaveResponse saveGenenalAffairsElcBizAk(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveGenenalAffairsElcBizAk(dto)).build();
    }

    @ApiOperation(value = "총무전자업무요청 저장", notes = "")
    @PutMapping("/business-procs")
    public SaveResponse saveBusinessProcs(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveRentProcs(dto)).build();
    }

    @ApiOperation(value = "총무전자업무요청 현황 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<WwpseGenenalAffairsElcBizAkDto.SearchRes> getGenenalAffairsElcBizAkPssForExcelDownload(
        @Valid
        WwpseGenenalAffairsElcBizAkDto.SearchReq dto
    ) {
        return service.getGenenalAffairsElcBizAkForExcelDownload(dto);
    }
}
