package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SaveUploadReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbMutualAidAllianceBulkDepositRegDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbMutualAidAllianceBulkDepositRegService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
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

    /**
     * 상조제휴 일괄입금 등록 목록 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lifSpptYm", value = "라이프지원년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "lifAlncDvCd", value = "라이프제휴구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "상조제휴 일괄입금 등록 목록 조회", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getMutualAidAllianceBulkDepositRegPages(SearchReq dto, PageInfo pageInfo) {
        return service.getMutualAidAllianceBulkDepositRegPages(dto, pageInfo);
    }

    /**
     * 상조제휴 일괄입금 등록 목록 조회 / 엑셀다운로드
     * @param dto
     * @return List<SearchRes>
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lifSpptYm", value = "라이프지원년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "lifAlncDvCd", value = "라이프제휴구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "상조제휴 일괄입금 등록 목록 엑셀다운로드", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 엑셀다운로드 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getMutualAidA7llianceBulkDepositRegExcels(SearchReq dto) {
        return service.getMutualAidAllianceBulkDepositRegExcels(dto);
    }

    /**
     * 상조제휴 일괄입금 등록 엑셀 업로드
     * @param lifAlncDvCd 라이프제휴구분코드
     * @param lifSpptYm 라이프지원년월
     * @param file 업로드 파일
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "상조제휴 일괄입금 등록 엑셀 업로드", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 엑셀 업로드한다.")
    @PostMapping("{lifAlncDvCd}/{lifSpptYm}/excel-upload")
    public SaveResponse saveMutualAidAllianceBulkDepositRegExcelUpload(
        @PathVariable("lifAlncDvCd")
        String lifAlncDvCd,
        @PathVariable("lifSpptYm")
        String lifSpptYm,
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveMutualAidAllianceBulkDepositRegExcelUpload(lifAlncDvCd, lifSpptYm, file))
            .build();
    }

    /**
     * 상조제휴 일괄입금 등록 입금내역 조회
     * @param dto
     * @return SearchSumRes
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "lifSpptYm", value = "라이프지원년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "lifAlncDvCd", value = "라이프제휴구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "상조제휴 일괄입금 등록 입금내역 조회", notes = " 검색조건을 받아 상조제휴 일괄입금 등록 입금내역 목록을 조회한다.")
    @GetMapping
    public SearchSumRes getMutualAidAllianceBulkDepositRegsSum(SearchSumReq dto) {
        return service.getMutualAidAllianceBulkDepositRegsSum(dto);
    }

    /**
     * 상조제휴 일괄입금 등록 생성
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "상조제휴 일괄입금 등록 생성", notes = "상조제휴 일괄입금 등록 생성한다.")
    @PostMapping
    public SaveResponse saveMutualAidAllianceBulkDepositRegsSum(
        @RequestBody
        @Valid
        List<SaveUploadReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveMutualAidAllianceBulkDepositReg(dto))
            .build();
    }

    /**
     * 상조제휴 일괄입금 등록 생성
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "상조제휴 일괄입금 등록 생성", notes = "상조제휴 일괄입금 등록 생성한다.")
    @PostMapping("/create")
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
