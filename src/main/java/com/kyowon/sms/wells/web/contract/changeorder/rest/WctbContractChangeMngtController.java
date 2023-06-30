package com.kyowon.sms.wells.web.contract.changeorder.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractChangeMngtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTB] 계약변경")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorders/changes")
public class WctbContractChangeMngtController {

    private final WctbContractChangeMngtService service;

    @ApiOperation(value = "계약변경관리-조회", notes = "변경할 계약번호를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형코드", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "계약자명", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmDtmFr", value = "계약시작접수일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrCnfmDtmTo", value = "계약종료접수일자", paramType = "query"),

    })
    @GetMapping
    public PagingResult<SearchContractChangeRes> getContractChangePages(
        @Valid
        SearchContractChangeReq dto
    ) {
        return service.getContractChangePages(dto);
    }
}
