package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaContractService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] Contract 공통")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/contracts")
public class WctaContractController {

    private final WctaContractService service;

    @ApiOperation(value = "확정승인기준 리스트 - 승인 요청 구분 조회", notes = "기준일자로 유효시작, 종료일시 에 존재하며, 데이터삭제여부가 Y가 아닌확정승인기준 조회를 한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-request-standards")
    public List<SearchRes> getApprovalAskDivides(
        @RequestParam
        String standardDt
    ) {
        return service.getApprovalAskDivides(standardDt);
    }

    @ApiOperation(value = "확정승인기준 리스트 - 승인 요청 리스트 삭제", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @DeleteMapping("/approval-request-standards")
    public int removeApprovalAskDivides(
        @RequestBody
        @NotEmpty
        List<RemoveReq> dtos
    ) {
        return service.removeApprovalAskDivides(dtos);
    }

    @ApiOperation(value = "확정승인 요청내역 - 확정 승인 요청 내역", notes = "계약승인요청구분코드, 계약확정승인발송이력, ,계약확정승인내역 을 이용하여 확정승인 요청내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @GetMapping("/approval-requests")
    public List<SearchConfirmAprPsicAksRes> getConfirmAprPsicAks(
        @RequestParam
        String cntrNo
    ) {
        return service.getConfirmAprPsicAks(cntrNo);
    }

    @ApiOperation(value = "확정승인 요청내역 - 확정 승인 구매 내역", notes = "계약번호에 따른 교원키를 가지고 해당 교원키로 구매목록을 조회한다. 계약정보, 상품정보, 렌탈, 연체정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
    })
    @GetMapping("/approval-requests/order-histories")
    public List<SearchConfirmAprPsicPrchssRes> getConfirmAprPsicPrchss(
        @RequestParam
        String cntrNo
    ) {
        return service.getConfirmAprPsicPrchss(cntrNo);
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 확정 승인 기준 관리 조회", notes = "화면에서 선택한 유효값 체크시 기준일자가 포함되고 체크해제시 기준일자를 제외하고 전체 조회한다. 스크롤 페이징을 이용한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrAprAkDvCd", value = "승인요청구분", paramType = "query"),
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-standards/paging")
    public PagingResult<SearchConfirmApprovalBaseRes> getConfirmApprovalBasePages(
        SearchConfirmApprovalBaseReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getConfirmApprovalBasePages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 확정 승인 기준 관리 엑셀", notes = "화면에서 선택한 유효값 체크시 기준일자가 포함되고 체크해제시 기준일자를 제외하고 전체조회 후 엑셀을 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrAprAkDvCd", value = "승인요청구분", paramType = "query"),
        @ApiImplicitParam(name = "standardDt", value = "기준일자", paramType = "query"),
    })
    @GetMapping("/approval-standards/excel-download")
    public List<SearchConfirmApprovalBaseRes> getConfirmApprovalBasesExcelDownload(
        SearchConfirmApprovalBaseReq dto
    ) {
        return service.getConfirmApprovalBasesExcelDownload(dto);
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 확정 승인 기준 관리 저장", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @PostMapping("/approval-standards")
    public SaveResponse saveConfirmApprovalBases(
        @RequestBody
        @NotEmpty
        List<SaveConfirmApprovalBaseReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveConfirmApprovalBases(dtos)).build();
    }

    @ApiOperation(value = "wells 확정 승인 기준 관리 - 승인 요청 리스트 삭제", notes = "계약약승인요청구분코드, 유효시작일시 존재여부 체크 후 삭제한다.")
    @DeleteMapping("/approval-standards")
    public SaveResponse removeConfirmApprovalBases(
        @RequestBody
        @NotEmpty
        List<RemoveConfirmApprovalBaseReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.removeConfirmApprovalBases(dtos)).build();
    }

    @ApiOperation(value = "렌탈 계정 관리 현황 - 상품별 조회", notes = "렌탈 계정 관리 현황 - 상품별 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "istStartDt", value = "설치시작년월", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "istEndDt", value = "설치끝년월", paramType = "query", example = "20221231"),
        @ApiImplicitParam(name = "pdMclsfId", value = "상품군", paramType = "query"),
        @ApiImplicitParam(name = "basePdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "고객구분", paramType = "query"),
    })
    @GetMapping("/rental-accounts/products")
    public List<SearchBpdRentalAccMgtPssRes> getBpdRentalAccMgtPss(
        SearchBpdRentalAccMgtPssReq dto
    ) {
        return service.getBpdRentalAccMgtPss(dto);
    }

    @ApiOperation(value = "렌탈 계정 관리 현황 - 조직별 조회", notes = "렌탈 계정 관리 현황 - 조직별 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "srchGbn", value = "조회구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "istStartDt", value = "설치시작년월", paramType = "query", example = "20220101"),
        @ApiImplicitParam(name = "istEndDt", value = "설치끝년월", paramType = "query", example = "20221231"),
        @ApiImplicitParam(name = "dgr1LevlOgNm", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgNm", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "고객구분", paramType = "query"),
    })
    @GetMapping("/rental-accounts/organizations")
    public List<SearchByoRentalAccMgtInqrsRes> getByoRentalAccMgtInqrs(
        SearchByoRentalAccMgtInqrsReq dto
    ) {
        return service.getByoRentalAccMgtInqrs(dto);
    }
}
