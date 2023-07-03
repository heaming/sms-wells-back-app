package com.kyowon.sms.wells.web.contract.changeorder.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractChangeMgtService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
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
@RequestMapping(CtContractConst.REST_URL_V1 + "/changeorder/changes")
public class WctbContractChangeMgtController {

    private final WctbContractChangeMgtService service;

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
        SearchContractChangeReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getContractChangePages(dto, pageInfo);
    }

    @ApiOperation(value = "계약변경관리-파트너 변경(조회)", notes = "계약변경관리-파트너 변경(조회)")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/partners")
    public WctbContractChangeMngtDto.FindPartnerRes getPartnerByCntrNo(
        @RequestParam
        String cntrNo,
        @RequestParam
        String cntrSn
    ) {
        return service.getPartnerByCntrNo(cntrNo, cntrSn);
    }

    @ApiOperation(value = "계약변경관리-파트너 변경(저장)", notes = "계약변경관리-파트너 변경(저장)")
    @PutMapping("/partners")
    public int editPartner(
        @Valid
        @RequestBody
        WctbContractChangeMngtDto.EditPartnerReq dto
    ) {
        return service.editPartner(dto);
    }
}
