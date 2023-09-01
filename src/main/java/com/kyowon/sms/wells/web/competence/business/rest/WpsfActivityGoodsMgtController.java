package com.kyowon.sms.wells.web.competence.business.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.competence.business.dto.WpsfActivityGoodsMgtDto;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfActivityGoodsMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfActivityGoodsMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.business.service.WpsfActivityGoodsMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.psCompetenceConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WPSF] 활동물품 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(psCompetenceConst.REST_URL_V1 + "/business/activity")
public class WpsfActivityGoodsMgtController {

    private final WpsfActivityGoodsMgtService service;

    @ApiOperation(value = "활동물품신청기준기본 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
    })
    @GetMapping("/base")
    public SearchRes getApplicationBase(
        @Valid
        SearchReq dto
    ) {
        return service.getApplicationBase(dto);
    }

    @ApiOperation(value = "활동물품신청내역 조회 - 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "aplcDt", value = "신청년월"),
        @ApiImplicitParam(name = "aplcStatCd", value = "조회구분", defaultValue = "40"),
        @ApiImplicitParam(name = "prtnrNo", value = "번호"),
        @ApiImplicitParam(name = "actiGdsSn", value = "활동물품일련번호"),
        @ApiImplicitParam(name = "actiGdsStddCd", value = "활동물품규격코드"),
        @ApiImplicitParam(name = "aplcQty", value = "신청수량"),
    })
    @GetMapping("/paging")
    public PagingResult<WpsfActivityGoodsMgtDto.SearchStatRes> getActivityGoodsApplicationIzPages(
        @Valid
        WpsfActivityGoodsMgtDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getActivityGoodsApplicationIzPages(dto, pageInfo);
    }

    @ApiOperation(value = "활동물품신청내역 조회 - 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "aplcDt", value = "신청년월"),
        @ApiImplicitParam(name = "aplcStatCd", value = "조회구분", defaultValue = "40"),
        @ApiImplicitParam(name = "prtnrNo", value = "번호"),
        @ApiImplicitParam(name = "actiGdsSn", value = "활동물품일련번호"),
        @ApiImplicitParam(name = "actiGdsStddCd", value = "활동물품규격코드"),
        @ApiImplicitParam(name = "aplcQty", value = "신청수량"),
    })
    @GetMapping("/excel-download")
    public List<WpsfActivityGoodsMgtDto.SearchStatRes> getActivityGoodsApplicationIzExcelDownload(
        @Valid
        WpsfActivityGoodsMgtDto.SearchReq dto
    ) {
        return service.getActivityGoodsApplicationIzExcelDownload(dto);
    }

    @ApiOperation(value = "활동물품신청기준기본 저장", notes = "활동물품기본을 저장한다.")
    @PostMapping("/base")
    public SaveResponse saveBase(
        @RequestBody
        @Valid
        WpsfActivityGoodsMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveApplicationBase(dto))
            .build();
    }

    @ApiOperation(value = "활동물품기본 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
    })
    @GetMapping("/goods-base")
    public List<WpsfActivityGoodsMgtDto.SearchAcriGdsBasRes> getMaxActivityGoodsBase(
        @Valid
        SearchReq dto
    ) {
        return service.getMaxActivityGoodsBase(dto);
    }

    @ApiOperation(value = "활동물품기본 전체조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "actiGdsCd", value = "활동물품코드", required = true),
    })
    @GetMapping("/goods-base-all")
    public List<WpsfActivityGoodsMgtDto.SearchAcriGdsBasRes> getActivityGoodsBase(
        @Valid
        SearchReq dto
    ) {
        return service.getActivityGoodsBase(dto);
    }

    @ApiOperation(value = "활동물품기본 저장", notes = "활동물품기본을 저장한다.")
    @PostMapping("/goods-base")
    public SaveResponse saveActivityGoodsBase(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.EditReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveActivityGoodsBase(dtos))
            .build();
    }

    @ApiOperation(value = "활동물품기본 삭제", notes = "활동물품기본을 삭제한다.")
    @DeleteMapping("/goods-base")
    public SaveResponse removeGoodsBase(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.RemoveReq> dtos
    ) {

        return SaveResponse.builder().processCount(service.removeGoodsBase(dtos)).build();
    }

    @ApiOperation(value = "활동물품기본 코드조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
        @ApiImplicitParam(name = "aplcDt", value = "신청년월", required = true),
    })
    @GetMapping("/base-codes")
    public List<WpsfActivityGoodsMgtDto.SearchBaseCodeRes> getGdsBaseCodes(
        @Valid
        SearchReq dto
    ) {
        return service.getGdsBaseCodes(dto);
    }

    @ApiOperation(value = "활동물품규격구분기본 코드조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "aplcDt", value = "신청년월", required = true),
    })
    @GetMapping("/stdd-codes")
    public List<WpsfActivityGoodsMgtDto.SearchCodeRes> getStddBases(
        @Valid
        SearchReq dto
    ) {
        return service.getStddBases(dto);
    }

    @ApiOperation(value = "활동물품규격구분상세 코드조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "aplcDt", value = "신청년월", required = true),
    })
    @GetMapping("/dtl-codes")
    public List<WpsfActivityGoodsMgtDto.SearchCodeRes> getStddDtls(
        @Valid
        SearchReq dto
    ) {
        return service.getStddDtls(dto);
    }

    @ApiOperation(value = "활동물품신청 ", notes = "활동물품신청을 저장한다.")
    @PostMapping("/application")
    public SaveResponse saveApplication(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.SaveApplicationReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveApplication(dtos))
            .build();
    }

    @ApiOperation(value = "활동물품신청 취소,반품등록", notes = "활동물품신청 취소,반품등록 한다.")
    @DeleteMapping("/application")
    public SaveResponse removeApplication(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.RemovepplicationReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeApplication(dtos))
            .build();
    }

    @ApiOperation(value = "수수료 공재 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogTpCd", value = "조직유형코드", required = true),
    })
    @GetMapping("/deduction-itemization")
    public List<WpsfActivityGoodsMgtDto.SearchDeductionItemizationRes> getDeductionItemization(
        @Valid
        SearchReq dto
    ) {
        return service.getDeductionItemization(dto);
    }

    @ApiOperation(value = "수수료 공재 등록", notes = "수수료 공재 등록한다.")
    @PostMapping("/deduction-itemization")
    public SaveResponse saveDeductionItemization(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.EditDeductionReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveDeductionItemization(dtos))
            .build();
    }

    @ApiOperation(value = "수수료 공재 삭제", notes = "수수료 공재 삭제 한다.")
    @DeleteMapping("/deduction-itemization")
    public SaveResponse removeDeductionItemization(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.RemoveDeductionReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeDeductionItemization(dtos))
            .build();
    }

    @ApiOperation(value = "활동물품신청 엑셀 업로드", notes = "활동물품신청 엑셀 업로드")
    @PostMapping("/excel-upload")
    public ExcelUploadDto.UploadRes saveExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return service.saveExcelUpload(file);
    }

    @ApiOperation(value = "활동물품기본 사이즈 조회", notes = "")
    @ApiImplicitParams(value = {

    })
    @GetMapping("/size-base")
    public List<WpsfActivityGoodsMgtDto.SearchSizeRes> getActivityGoodsSize(
        @Valid
        SearchReq dto
    ) {
        return service.getActivityGoodsSize(dto);
    }

    @ApiOperation(value = "활동물품기본 사이즈 저장", notes = "활동물품기본 사이즈 저장한다.")
    @PostMapping("/size-base")
    public SaveResponse saveActivityActivityGoodsSize(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.EditSizeReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveActivityActivityGoodsSize(dtos))
            .build();
    }

    @ApiOperation(value = "활동물품기본 사이즈 삭제", notes = "활동물품기본 사이즈 삭제 한다.")
    @DeleteMapping("/size-base")
    public SaveResponse removeActivityActivityGoodsSize(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.RemoveSizenReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeActivityActivityGoodsSize(dtos))
            .build();
    }

    @ApiOperation(value = "활동물품기본 사이즈 상세 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "actiGdsStddDvId", value = "활동물품규격구분ID", required = true),
    })
    @GetMapping("/size-detail")
    public List<WpsfActivityGoodsMgtDto.SearchSizeDetailRes> getActivityGoodsSizeDetail(
        @Valid
        SearchReq dto
    ) {
        return service.getActivityGoodsSizeDetail(dto);
    }

    @ApiOperation(value = "활동물품기본 사이즈 상세 저장", notes = "활동물품기본 사이즈 상세 저장한다.")
    @PostMapping("/size-detail")
    public SaveResponse saveActivityActivityGoodsSizeDetail(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.EditSizeDetailReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveActivityActivityGoodsSizeDetail(dtos))
            .build();
    }

    @ApiOperation(value = "활동물품기본 사이즈 상세  삭제", notes = "활동물품기본 사이즈 상세 삭제 한다.")
    @DeleteMapping("/size-detail")
    public SaveResponse removeActivityActivityGoodsSizeDetail(
        @RequestBody
        @Valid
        List<WpsfActivityGoodsMgtDto.RemoveSizenDetailReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeActivityActivityGoodsSizeDetail(dtos))
            .build();
    }

}
