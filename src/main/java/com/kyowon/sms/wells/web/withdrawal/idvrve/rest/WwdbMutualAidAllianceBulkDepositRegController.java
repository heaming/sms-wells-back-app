package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbMutualAidAllianceBulkDepositRegService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto.UploadRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 상조제휴 일괄입금 등록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/mutual-alliance-bulk-deposit")
public class WwdbMutualAidAllianceBulkDepositRegController {

    private final WwdbMutualAidAllianceBulkDepositRegService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lifSpptYm", value = "라이프지원년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "lifAlncDvCd", value = "라이프제휴구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "상조제휴 일괄입금 등록 목록 조회", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getMutualAidAllianceBulkDepositRegPages(SearchReq dto, PageInfo pageInfo) {
        return service.getMutualAidAllianceBulkDepositRegPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lifSpptYm", value = "라이프지원년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "lifAlncDvCd", value = "라이프제휴구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "상조제휴 일괄입금 등록 목록 엑셀다운로드", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getMutualAidA7llianceBulkDepositRegExcels(SearchReq dto) {
        return service.getMutualAidAllianceBulkDepositRegExcels(dto);
    }

    @ApiOperation(value = "상조제휴 일괄입금 등록 엑셀 업로드", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 엑셀 업로드한다.")
    @PostMapping("/excel-upload")
    public UploadRes saveMutualAidAllianceBulkDepositRegExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        service.saveMutualAidAllianceBulkDepositRegExcelUpload(file);
        return UploadRes.builder().status("S").build();
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lifSpptYm", value = "라이프지원년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "lifAlncDvCd", value = "라이프제휴구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "상조제휴 일괄입금 등록 입금내역 조회", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 입금내역 목록을 조회한다.")
    @GetMapping
    public SearchSumRes getMutualAidAllianceBulkDepositRegsSum(SearchSumReq dto) {
        return service.getMutualAidAllianceBulkDepositRegsSum(dto);
    }

    @ApiOperation(value = "상조제휴 일괄입금 등록 생성", notes = "상조제휴 일괄입금 등록 생성한다.")
    @PostMapping
    public SaveResponse saveMutualAidAllianceBulkDepositRegsSum(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveMutualAidAllianceBulkDepositRegs(dto))
            .build();
    }
}
