package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchConsumablesRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchConsumablesRefundRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SearchContractInfoRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbConsumablesRefundService;
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
@Api(tags = "[입금관리 - 개별수납] 소모품환불관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/consumables-refunds")
public class WwdbConsumablesRefundController {

    private final WwdbConsumablesRefundService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rveDvCd", value = "업무구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "startDay", value = "처리일자-시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "endDay", value = "처리일자-종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNoSN", value = "계약상세번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "소모품환불관리 목록", notes = "소모품환불관리 조회")
    @GetMapping("/paging")
    public PagingResult<SearchConsumablesRefundRes> getConsumablesRefundPages(
        @Valid
        SearchConsumablesRefundReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getConsumablesRefundPages(req, pageInfo);
    }

    @ApiOperation(value = "소모품환불관리 엑셀 다운로드", notes = "소모품환불관리 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchConsumablesRefundRes> getConsumablesRefundExcels(
        SearchConsumablesRefundReq req
    ) {
        return service.getConsumablesRefundExcels(req);
    }

    @ApiOperation(value = "소모품환불관리 삭제", notes = " 소모품환불관리 삭제")
    @DeleteMapping
    public SaveResponse removeConsumablesRefunds(
        @RequestBody
        @Valid
        List<RemoveReq> req
    ) throws Exception {
        log.info("req:" + req);
        return SaveResponse
            .builder()
            .processCount(service.removeConsumablesRefunds(req))
            .build();
    }

    @ApiOperation(value = "고객 정보 조회", notes = "소모품 환불 등록되는 고객 정보 조회")
    @GetMapping("/contract-info")
    public SearchContractInfoRes getContractInfo(
        @RequestParam
        String cntrNo
    ) {
        return service.getContractInfo(cntrNo);
    }

    @ApiOperation(value = "환불 은행사 조회", notes = "지급 구분에 따른 환불 카드사 조회")
    @GetMapping("/card")
    public List<SearchCardRes> getConsumablesRefundCard() {
        return service.getConsumablesRefundCard();
    }

    @ApiOperation(value = "환불 카드사 조회", notes = "지급 구분에 따른 환불 은행사 조회")
    @GetMapping("/bank")
    public List<SearchBankRes> getConsumablesRefundBank() {
        return service.getConsumablesRefundBank();
    }

    @ApiOperation(value = "소모품환불관리 등록", notes = " 소모품환불관리 등록")
    @PostMapping
    public SaveResponse saveConsumablesRefund(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        log.info("req:" + dto);
        return SaveResponse
            .builder()
            .processCount(service.saveConsumablesRefund(dto))
            .build();
    }
}
