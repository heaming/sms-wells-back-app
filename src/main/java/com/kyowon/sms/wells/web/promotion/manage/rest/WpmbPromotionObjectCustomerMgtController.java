package com.kyowon.sms.wells.web.promotion.manage.rest;

import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SearchRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.common.web.promotion.zcommon.constants.PmPromotionConst;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.ContractRes;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.promotion.manage.dto.WpmbPromotionObjectCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.promotion.manage.service.WpmbPromotionObjectCustomerMgtService;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[ZPMB] 상품 >> 프로모션 >> 프로모션 대상고객 일괄등록 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = PmPromotionConst.REST_URL_V1 + "/object-customers")
public class WpmbPromotionObjectCustomerMgtController {

    private final WpmbPromotionObjectCustomerMgtService service;

    @ApiOperation(value = "프로모션 대상고객 일괄등록 관리 - 페이징 조회", notes = "조회조건에 따라 프로모션 대상고객 일괄등록 리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "vlStrtDtm", value = "유효시작일시", paramType = "query", required = false),
        @ApiImplicitParam(name = "vlEndDtm", value = "유효종료일시", paramType = "query", required = false),
        @ApiImplicitParam(name = "pmotOjSpcDscDvCd", value = "특별할인코드", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getPromotionObjectCustomerPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getPromotionObjectCustomerPages(dto, pageInfo);
    }

    @ApiOperation(value = "프로모션 대상고객 계약정보 조회", notes = "프로모션 대상고객 계약정보를 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/contract-info")
    public ContractRes getContractInfo(
        @RequestParam
        String cntrNo,
        @RequestParam
        int cntrSn
    ) {
        return service.getContractInfo(cntrNo, cntrSn);
    }

    @ApiOperation(value = "프로모션 대상고객 일괄등록 관리 엑셀 다운로드", notes = "조회조건에 따라 엑셀 다운로드용 프로모션 대상고객 일괄등록 리스트를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getPromotionObjectCustomersForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getPromotionObjectCustomersForExcelDownload(dto);
    }

    @ApiOperation(value = "프로모션 대상고객 일괄등록 관리 엑셀 업로드", notes = "프로모션 대상고객 일괄등록 관리 엑셀 업로드한다.")
    @PostMapping("/excel-upload")
    public UploadRes savePromotionObjectCustomersExcelUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return service.savePromotionObjectCustomersExcelUpload(file);
    }

    @ApiOperation(value = "프로모션 대상고객 일괄등록 관리 - 등록/수정", notes = "그리드 내에서 수정한 프로모션 대상고객 일괄등록 리스트를 등록/수정한다.")
    @PutMapping
    public SaveResponse savePromotionObjectCustomers(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveReq> dtos) {

        return SaveResponse.builder().processCount(service.savePromotionObjectCustomers(dtos)).build();
    }

    @ApiOperation(value = "프로모션 대상고객 일괄등록 관리 - 삭제", notes = "그리드 내에서 선택한 프로모션 대상고객 일괄등록 리스트를 삭제한다.")
    @DeleteMapping
    public SaveResponse removePromotionObjectCustomers(
        @RequestBody
        @Valid
        @NotEmpty
        List<RemoveReq> dtos) {
        return SaveResponse.builder().processCount(service.removePromotionObjectCustomers(dtos)).build();
    }
}
