package com.kyowon.sms.wells.web.competence.report.rest;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.kyowon.sms.common.web.bond.consultation.dto.ZbncBondCollectionRelayServiceDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbDepositItemizationBulkPrntDto;
import com.kyowon.sms.wells.web.competence.report.service.WwpsgBusinessManagerReportMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[] BM보고서 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/business-manager-report")
public class WwpsgBusinessManagerReportMgtController {

    private final WwpsgBusinessManagerReportMgtService service;

    @ApiOperation(value = "BM보고서 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBusinessManagerReportMgtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBusinessManagerReportMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "BM보고서 관리 업무유형 내용", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type-content")
    public List<businessTypeRes> getBusinessTypeContent(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessTypeContent(dto);
    }

    @ApiOperation(value = "BM보고서 관리 업무유형", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/business-type")
    public Map<String, List> getBusinessType(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessType(dto);
    }

    @ApiOperation(value = "BM보고서 관리 파트너 연락처", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/prtnr-info")
    public List<PrtnrRes> getPrtnrInfo(
        @Valid
        SearchReq dto
    ) {
        return service.getPrtnrInfo(dto);
    }

    @ApiOperation(value = "BM보고서 관리 저장", notes = "")
    @PostMapping("/business-manager-report")
    public SaveResponse saveBusinessManagerReport(
        @RequestBody
        @Valid
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.saveBusinessManagerReport(dtos)).build();
    }

    @ApiOperation(value = "보고서 업무 유형 저장", notes = "")
    @PostMapping("/report-business-type")
    public SaveResponse saveReportBusinessType(
        @RequestBody
        @Valid
        SaveReq dto
    ) {
        return SaveResponse.builder().processCount(this.service.saveReportBusinessType(dto)).build();
    }

    @ApiOperation(value = "BM보고서 관리 삭제", notes = "")
    @DeleteMapping("/")
    public SaveResponse removeSpectxBlkPrnts(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(this.service.removeBusinessManagerReport(dtos)).build();
    }

}
