package com.kyowon.sms.wells.web.organization.insurance.rest;

import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.SearchRes;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.EditReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.RemoveReq;

import com.kyowon.sms.wells.web.organization.insurance.service.WogdIndustrialDisasterInsuranceService;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController
@Api(tags = "[WOGD] 산재보험 REST API")
@RequestMapping(OgConst.REST_PREFIX_SMS_WELLS + "/insurance/industrial-disaster")
@RequiredArgsConstructor
@Validated
public class WogdIndustrialDisasterInsuranceController {

    private final WogdIndustrialDisasterInsuranceService service;

    @ApiOperation(value = "산재보험 조회 ", notes = "검색조건을 입력 받아 산재보험 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202303", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", example = "12345", required = false),
        @ApiImplicitParam(name = "bizStatCd", value = "사업자구분", paramType = "query", example = "1", required = true),
    })
    @GetMapping("/pages")
    PagingResult<SearchRes> getIndustrialDisasterInsurances(SearchReq dto, PageInfo pageInfo) {
        return service.getIndustrialDisasterInsurances(dto, pageInfo);
    }

    @ApiOperation(value = "산재보험 엑셀다운로드 ", notes = "검색조건을 입력 받아 산재보험 목록을 엑셀로 저장한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query", example = "202303", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", example = "12345", required = false),
        @ApiImplicitParam(name = "bizStatCd", value = "사업자구분", paramType = "query", example = "1", required = true),
    })
    @GetMapping("/excel-download")
    List<SearchRes> getIndustrialDisasterInsurancesForExcelDownload(SearchReq dto) {
        return service.getIndustrialDisasterInsurancesForExcelDownload(dto);
    }

    @ApiOperation(value = "산재보험 확정 ", notes = "선택한 산재보험 목록을 확정처리한다.")
    @PutMapping
    SaveResponse editIndustrialDisasterInsurances(
        @RequestBody
        @Valid
        @NotEmpty
        List<EditReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.editIndustrialDisasterInsurances(dtos)).build();
    }

    @ApiOperation(value = "산재보험 삭제 ", notes = "선택한 산재보험 목록을 삭제처리한다.")
    @DeleteMapping
    SaveResponse removeIndustrialDisasterInsurances(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeIndustrialDisasterInsurances(dtos)).build();
    }

    @ApiOperation(value = "산재보험 엑셀업로드", notes = "산재보험정보를 엑셀업로드한다. ")
    @PostMapping("/excel-upload/{baseYm}")
    public ExcelUploadDto.UploadRes saveIndustrialDisasterInsurancesForDirectExcelUpload(
        @RequestParam("file")
        MultipartFile file,
        @PathVariable(name = "baseYm")
        String baseYm
    ) throws Exception {
        return service.saveIndustrialDisasterInsurancesForDirectExcelUpload(file, baseYm);
    }
}
