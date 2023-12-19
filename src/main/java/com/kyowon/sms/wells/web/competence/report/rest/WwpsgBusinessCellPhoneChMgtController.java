package com.kyowon.sms.wells.web.competence.report.rest;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto;
import com.kyowon.sms.wells.web.competence.report.service.WwpsgBusinessCellPhoneChMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto.*;

@Api(tags = "[] 업무폰변경 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/business-cell-phone")
public class WwpsgBusinessCellPhoneChMgtController {

    private final WwpsgBusinessCellPhoneChMgtService service;

    @ApiOperation(value = "업무폰변경 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBusinessCellPhoneChMgtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBusinessCellPhoneChMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "업무폰변경 관리 팝업", notes = "")
    @GetMapping("/rent-popup")
    public WwpsgBusinessCellPhoneChMgtDto.PopupSearchRes getRentManagementPopup(
        @Valid
        WwpsgBusinessCellPhoneChMgtDto.SearchReq dto
    ) {
        return service.getRentManagementPopup(dto);
    }

    @ApiOperation(value = "업무폰변경 관리 기본정보", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/base-information")
    public Map<String, List> getBusinessType(
        @Valid
        WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto
    ) {
        return service.getBaseInfo(dto);
    }

    @ApiOperation(value = "업무폰 관리 판매자 파트너 정보", notes = "")
    @GetMapping("/prtnr-info")
    public SellPrtnrRes getPrtnrInfo(
        @Valid
        WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto
    ) {
        return service.getPrtnrInfo(dto);
    }

    @ApiOperation(value = "업무폰 관리 저장", notes = "")
    @PostMapping("/")
    public SaveResponse saveBusinessSellPhone(
        @RequestBody
        @Valid
        WwpsgBusinessCellPhoneChMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveBusinessSellPhone(dto)).build();
    }

    @ApiOperation(value = "업무폰관리 처리 저장", notes = "")
    @PutMapping("/business-phone-procs")
    public SaveResponse saveBusiPhoneRentProcs(
        @RequestBody
        @Valid
        WwpsgBusinessCellPhoneChMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveBusiPhoneRentProcs(dto)).build();
    }

    @ApiOperation(value = "업무폰변경 관리 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getBusinessCellPhoneChMgtsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessCellPhoneChMgtsForExcelDownload(dto);
    }
}
