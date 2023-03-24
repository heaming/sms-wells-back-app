package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveErrosReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchErrosRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchLedgerItemizationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchLedgerItemizationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchRes;
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

    @ApiOperation(value = "지로 입금관리", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBillingDocumentMgtPages(SearchReq dto, PageInfo pageInfo) {
        return service.getBillingDocumentMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "지로 입금관리", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {
        return service.getBillingDocumentMgtExcels(dto);
    }

    @ApiOperation(value = "지로 입금관리 업로드")
    @PostMapping
    public SaveResponse saveBillingDocumentMgt(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentMgt(dto))
            .build();
    }

    @ApiOperation(value = "지로 입금관리 생성")
    @PostMapping("/create")
    public SaveResponse saveBillingCreateDocument(SearchReq dto) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingCreateDocument(dto))
            .build();
    }

    @ApiOperation(value = "지로 입금관리 에러 조회", notes = " 검색조건을 받아 지로 입금관리 에러를 조회한다.")
    @GetMapping("/errors")
    public PagingResult<SearchErrosRes> getBillingDocumentErrorsPages(SearchReq dto, PageInfo pageInfo) {
        return service.getBillingDocumentErrorsPages(dto, pageInfo);
    }

    @ApiOperation(value = "지로 입금관리 에러 엑셀다운로드", notes = " 검색조건을 받아 지로 입금관리 에러를 엑셀다운로드 한다.")
    @GetMapping("/errors/excel-download")
    public List<SearchErrosRes> getBillingDocumentErrorsExcels(SearchReq dto) {
        return service.getBillingDocumentErrorsExcels(dto);
    }

    @ApiOperation(value = "지로 입금관리 에러 저장")
    @PostMapping("/errors")
    public SaveResponse saveBillingDocumentErrors(
        @RequestBody
        @Valid
        List<SaveErrosReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentErrors(dto))
            .build();
    }

    @ApiOperation(value = "지로 입금관리 원장 내역 조회", notes = " 검색조건을 받아 지로 입금관리 원장 내역 목록을 조회한다.")
    @PostMapping("/ledg-iz")
    public SearchLedgerItemizationRes getBillingDocumentMgtLedgerItemization(
        @RequestBody
        @Valid
        List<SearchLedgerItemizationReq> dto
    ) {
        return service.getBillingDocumentMgtLedgerItemization(dto);
    }
}
