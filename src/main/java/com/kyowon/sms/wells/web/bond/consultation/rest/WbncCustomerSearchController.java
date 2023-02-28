package com.kyowon.sms.wells.web.bond.consultation.rest;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchReq;
import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerSearchDto.SearchRes;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.bond.consultation.service.WbncCustomerSearchService;
import com.kyowon.sms.wells.web.bond.zcommon.constants.BnBondConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WBNC] 채권상담 고객찾기")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(BnBondConst.REST_URL_V1 + "/customer-search")
public class WbncCustomerSearchController {
    public final WbncCustomerSearchService service;

    @ApiOperation(value = "채권상담 고객찾기", notes = "검색 조건을 입력 받아 채권상담 고객 정보를 조회 한다.")
    @GetMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "bndClctnPrpDvCd", value = "채권속성코드", paramType = "query"),
        @ApiImplicitParam(name = "bndClctnPrpRsonCd", value = "속성사유코드", paramType = "query"),
        @ApiImplicitParam(name = "clctamPrtnrNo", value = "집급담당파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cntrCralLocaraTno", value = "계약처휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrMexnoEncr", value = "계약처휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cntrCralIdvTno", value = "계약처휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "istCralLocaraTno", value = "설치처휴대지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "istMexnoEncr", value = "설치처휴대전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "istCralIdvTno", value = "설치처휴대개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrLocaraTno", value = "계약처지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrExnoEncr", value = "계약처전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "cntrIdvTno", value = "계약처개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "istLocaraTno", value = "설치처지역전화번호", paramType = "query"),
        @ApiImplicitParam(name = "istExnoEncr", value = "설치처전화국번호암호화", paramType = "query"),
        @ApiImplicitParam(name = "istIdvTno", value = "설치처개별전화번호", paramType = "query"),
        @ApiImplicitParam(name = "adr", value = "계약처주소", paramType = "query"),
    })
    public List<SearchRes> getCustomers(SearchReq dto) {
        return service.getCustomers(dto);
    }
}
