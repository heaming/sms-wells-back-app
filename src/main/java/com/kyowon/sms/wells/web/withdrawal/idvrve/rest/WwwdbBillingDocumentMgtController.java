package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchDtlsReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchDtlsRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchFwReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchFwRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbBillingDocumentMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwwdbBillingDocumentMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 청구서 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WithdrawalConst.REST_URL_V1 + WithdrawalConst.REST_URL_IDVRVE + "/billing-document-orders")
public class WwwdbBillingDocumentMgtController {

    private final WwwdbBillingDocumentMgtService service;

    @ApiOperation(value = "청구서 관리 목록 조회", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBillingDocumentMgtPages(SearchReq dto, PageInfo pageInfo) {
        return service.getBillingDocumentMgtPages(dto, pageInfo);
    }

    @ApiOperation(value = "청구서 관리 엑셀다운로드", notes = " 검색조건을 받아 청구서 관리 엑셀다운로드을 한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getBillingDocumentMgtExcels(SearchReq dto) {
        return service.getBillingDocumentMgtExcels(dto);
    }

    @ApiOperation(value = "청구서 관리 목록 삭제", notes = " 청구서 관리 목록을 삭제한다.")
    @PutMapping
    public SaveResponse removeBillingDocumentMgts(
        @RequestBody
        @Valid
        List<RemoveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeBillingDocumentMgts(dto))
            .build();
    }

    @ApiOperation(value = "청구서 관리 목록 조회", notes = "청구서 관리 목록을 조회한다.")
    @GetMapping("/details")
    public List<SearchDtlsRes> getBillingDocumentDetails(SearchDtlsReq dto) {
        return service.getBillingDocumentDetails(dto);
    }

    @ApiOperation(value = "청구서 관리 목록 저장", notes = "RDS 적요 청구서 관리 등록한다.")
    @PostMapping("/details")
    public SaveResponse saveBillingDocumentMgts(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentMgts(dto))
            .build();
    }

    @ApiOperation(value = "청구서 발송 목록 조회", notes = " 검색조건을 받아 청구서 관리 목록을 조회한다.")
    @GetMapping("/forwardings")
    public List<SearchFwRes> getBillingDocumentForwardings(SearchFwReq dto) {
        return service.getBillingDocumentForwardings(dto);
    }

    @ApiOperation(value = "청구서 발송", notes = "청구서 발송을 한다.")
    @PostMapping("/forwardings")
    public SaveResponse saveBillingDocumentForwarding(
        @RequestBody
        @Valid
        SaveFwReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveBillingDocumentForwarding(dto))
            .build();
    }

}
