package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbIntegrationDepositNumberInquiryDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbIntegrationDepositNumberInquiryDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwwdbIntegrationDepositNumberInquiryService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 통합입금번호조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/integration-deposit-number-inquiry")
public class WwwdbIntegrationDepositNumberInquiryController {

    private final WwwdbIntegrationDepositNumberInquiryService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rveCd", value = "수납코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "dpStartDtm", value = "임급일시-시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "dpEndDtm", value = "임급일시-종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "dpTpCd", value = "입금유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "acnoEncr", value = "계좌번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "판매자번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "crcdnoEncr", value = "카드번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "crdcdAprno", value = "승인번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "통합입금번호 목록 조회", notes = " 검색조건을 받아 통합입금번호 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getIntegrationDepositNumberInquiryPages(SearchReq dto, PageInfo pageInfo) {
        return service.getIntegrationDepositNumberInquiryPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "rveCd", value = "수납코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "dpStartDtm", value = "임급일시-시작", paramType = "query", required = false),
        @ApiImplicitParam(name = "dpEndDtm", value = "임급일시-종료", paramType = "query", required = false),
        @ApiImplicitParam(name = "dpTpCd", value = "입금유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "acnoEncr", value = "계좌번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "sellPrtnrNo", value = "판매자번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "crcdnoEncr", value = "카드번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "crdcdAprno", value = "승인번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "통합입금번호 목록 엑셀 다운로드", notes = " 검색조건을 받아 통합입금번호 목록을 엑셀 다운한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getIntegrationDepositNumberInquiryExcels(SearchReq dto) {
        return service.getIntegrationDepositNumberInquiryExcels(dto);
    }
}
