package com.kyowon.sms.wells.web.organization.hmnrsc.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.common.web.organization.attachment.dto.ZogeSeizureDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.*;
import com.kyowon.sms.wells.web.organization.hmnrsc.service.WogcPartnerEngineerService;
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

    @ApiOperation(value = "엔지니어 출근 관리 목록 저장", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PostMapping("/{prtnrNo}")
    public SaveResponse saveEngineerAttends(
        @RequestBody
        @Valid
        List<WogcPartnerEngineerDto.SaveEngineerAttendReq> dtos,
        @PathVariable
        String prtnrNo
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveEngineerAttends(dtos, prtnrNo)).build();
    }

    @ApiOperation(value = "엔지니어 휴가상세 조회", notes = "조회 조건에 일치하는 엔지니어 휴가상세 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
    })
    @GetMapping("/vacations")
    PagingResult<SearchVacationRes> getVacations(SearchVacationReq dto, PageInfo pageInfo) {
        return service.getVacations(dto, pageInfo);
    }

    @ApiOperation(value = "휴가상세 관리 목록 저장", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PostMapping("/vacations")
    public SaveResponse saveVacations(
        @RequestBody
        @Valid
        List<WogcPartnerEngineerDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveVacations(dtos)).build();
    }

    @ApiOperation(value = "휴가상세 관리 목록 삭제", notes = "선택한 휴가상세 관리 목록을 일괄 삭제한다.")
    @PutMapping("/vacations")
    public SaveResponse removeVacations(
        @RequestBody
        @Valid
        List<WogcPartnerEngineerDto.RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.removeVacations(dtos)).build();
    }

    @ApiOperation(value = "서비스센터 조 관리 조회", notes = "조회 조건에 일치하는 서비스센터 조관리 목록을 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "wkGrpCd", value = "그룹", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "vlDt", value = "적용기준일", paramType = "query", required = false),

    })
    @GetMapping("/joe-management/paging")
    PagingResult<FindJoeManagementRes> getJoeManagementPages(FindJoeManagementReq dto, PageInfo pageInfo) {
        return service.getJoeManagementPages(dto, pageInfo);
    }

    @ApiOperation(value = "서비스센터 조 관리 엑셀다운로드", notes = "조회 조건에 일치하는 서비스센터 조관리 목록을 엑셀로 저장한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "wkGrpCd", value = "그룹", paramType = "query", required = false),
        @ApiImplicitParam(name = "rsbDvCd", value = "직책", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "vlDt", value = "적용기준일", paramType = "query", required = false),

    })
    @GetMapping("/joe-management/excel-download")
    List<FindJoeManagementRes> getJoeManagementForExcelDownload(FindJoeManagementReq dto) {
        return service.getJoeManagementForExcelDownload(dto);
    }

    @ApiOperation(value = "서비스센터 조 저장", notes = "서비스센터 조 저장한다.")
    @PostMapping("/joe-management")
    public SaveResponse saveJoeManagement(
        @Valid
        @RequestBody
        List<WogcPartnerEngineerDto.SaveJoeManagementReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveJoeManagement(dtos))
            .build();
    }

    @ApiOperation(value = "엔지니어 등급 관리 조회", notes = "조회 조건에 일치하는 엔지니어 등급 관리 조회목록을 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrGdCd", value = "직무", paramType = "query", required = false),
        @ApiImplicitParam(name = "searchYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "chk", value = "미등록", paramType = "query", required = false),
    })
    @GetMapping("/engineer-grade/paging")
    PagingResult<FindEngineerGradeRes> getEngineerGradePages(FindEngineerGradeReq dto, PageInfo pageInfo) {
        return service.getEngineerGradePages(dto, pageInfo);
    }

    @ApiOperation(value = "엔지니어 등급 관리 엑셀다운로드", notes = "조회 조건에 일치하는 엔지니어 등급 관리 목록을 엑셀로 저장한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogLevlDvCd1", value = "1레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "ogLevlDvCd2", value = "2레벨 조직코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrGdCd", value = "직무", paramType = "query", required = false),
        @ApiImplicitParam(name = "searchYm", value = "관리년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "chk", value = "미등록", paramType = "query", required = false),
    })
    @GetMapping("/engineer-grade/excel-download")
    List<FindEngineerGradeRes> getEngineerGradeForExcelDownload(FindEngineerGradeReq dto) {
        return service.getEngineerGradeForExcelDownload(dto);
    }

    @ApiOperation(value = "엔지니어 등급관리 저장", notes = "엔지니어 등급 관리 저장한다.")
    @PostMapping("/engineer-grade")
    public SaveResponse saveEngineerGrade(
        @Valid
        @RequestBody
        List<WogcPartnerEngineerDto.SaveEngineerGradeReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveEngineerGrade(dtos))
            .build();
    }

    @ApiOperation(value = "엔지니어 등급관리 엑셀업로드", notes = "엔제니어 등급정보를 엑셀업로드한다. ")
    @PostMapping("/engineer-grade/excel-upload/{baseYm}")
    public ExcelUploadDto.UploadRes saveEngineerGradeForDirectExcelUpload(
        @RequestParam("file")
        MultipartFile file,
        @PathVariable(name = "baseYm")
        String baseYm
    ) throws Exception {
        return service.saveEngineerGradeForDirectExcelUpload(file, baseYm);
    }

}
