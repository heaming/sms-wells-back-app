package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveIntegrationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbGiroDepositMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 지로 입금관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/giro-deposits")
public class WwdbGiroDepositMgtController {

    private final WwdbGiroDepositMgtService service;

    /**
     * 지로 입금관리 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @ApiOperation(value = "지로 입금관리", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBillingDocumentMgtPages(SearchReq dto, PageInfo pageInfo) {
        return service.getBillingDocumentMgtPages(dto, pageInfo);
    }

    /**
     * 청구서 관리 합계 조회
     * @param dto
     * @return SearchSumRes
     */
    @ApiOperation(value = "지로 입금관리 합계", notes = " 검색조건을 받아 청구서 관리 합계 조회한다.")
    @GetMapping()
    public SearchSumRes getGiroDepositSum(SearchReq dto) {
        return service.getGiroDepositSum(dto);
    }

    /**
     * 청구서 관리 목록 조회 / 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    @ApiOperation(value = "지로 입금관리", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {
        return service.getBillingDocumentMgtExcels(dto);
    }

    /**
     * 지로 대사여부 확인
     * @param dto
     * @return int
     */
    @ApiOperation(value = "지로 대사여부 확인", notes = "검색조건 - 입금일자를 받아 대사여부를 체크한다.")
    @GetMapping("/check")
    public int getBillingFntDtChk(SearchChkReq dto) {
        return service.getBillingFntDtChk(dto);
    }

    /**
     * 지로 입금관리 업로드
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "지로 입금관리 업로드")
    @PostMapping("/{date}")
    public SaveResponse saveBillingDocumentMgt(
        @RequestBody @Valid
        List<SaveReq> dto,
        @PathVariable(name = "date")
        String date
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentMgt(dto, date))
            .build();
    }

    /**
     * 지로 입금관리 생성
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "지로 입금관리 생성")
    @PostMapping("/create")
    public SaveResponse saveBillingCreateDocument(
        @RequestBody @Valid
        SaveIntegrationReq dto
    ) {
        return service.saveBillingCreateDocument(dto);
    }

    /**
     * 지로 입금관리 에러 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchErrosRes>
     */
    @ApiOperation(value = "지로 입금관리 에러 조회", notes = " 검색조건을 받아 지로 입금관리 에러를 조회한다.")
    @GetMapping("/errors")
    public PagingResult<SearchErrosRes> getBillingDocumentErrorsPages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getBillingDocumentErrorsPages(dto, pageInfo);
    }

    /**
     * 지로 입금관리 에러 엑셀다운로드
     * @param dto
     * @return List<SearchErrosRes>
     */
    @ApiOperation(value = "지로 입금관리 에러 엑셀다운로드", notes = " 검색조건을 받아 지로 입금관리 에러를 엑셀다운로드 한다.")
    @GetMapping("/errors/excel-download")
    public List<SearchErrosRes> getBillingDocumentErrorsExcels(
        SearchReq dto
    ) {
        return service.getBillingDocumentErrorsExcels(dto);
    }

    /**
     * 지로 입금관리 에러 저장
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "지로 입금관리 에러 저장")
    @PostMapping("/errors")
    public SaveResponse saveBillingDocumentErrors(
        @RequestBody @Valid
        List<SaveErrosReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentErrors(dto))
            .build();
    }

    /**
     * 지로 입금관리 원장 내역 조회
     * @param dto
     * @return SearchLedgerItemizationRes
     */
    @ApiOperation(value = "지로 입금관리 원장 내역 조회", notes = " 검색조건을 받아 지로 입금관리 원장 내역 목록을 조회한다.")
    @PostMapping("/ledg-iz")
    public SearchLedgerItemizationRes getBillingDocumentMgtLedgerItemization(
        @RequestBody @Valid
        List<SearchLedgerItemizationReq> dto
    ) {
        return service.getBillingDocumentMgtLedgerItemization(dto);
    }

    /**
     * 지로 입금관리 실적일자 조회
     * @param dto
     * @return List<WwdbGiroDepositSaveDvo>
     */
    @ApiOperation(value = "지로 입금관리 실적일자 조회", notes = " 검색조건을 받아 지로 입금관리 실적일자 조회 한다.")
    @PostMapping("/date-chk")
    public List<WwdbGiroDepositSaveDvo> getGiroPerfDt(
        @RequestBody @Valid
        List<SaveReq> dto
    ) {
        return service.getGiroPerfDt(dto);
    }
}
