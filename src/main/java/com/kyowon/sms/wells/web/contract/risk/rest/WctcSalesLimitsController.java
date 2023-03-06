package com.kyowon.sms.wells.web.contract.risk.rest;

import static com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistRes;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.FindBlacklistRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.service.WctcSalesLimitsService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] 접수제한 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/sales-limits")
public class WctcSalesLimitsController {

    private final WctcSalesLimitsService service;

    @ApiOperation(value = "접수제한 관리-블랙리스트 페이징 조회", notes = "조회조건에 맞는 블랙리스트 목록을 페이징 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
    })
    @GetMapping("/blacklists")
    public FindBlacklistRes getBlacklistPages(
        @RequestParam
        String cntrNo,
        @RequestParam
        int cntrSn
    ) {
        return service.getBlacklistInfos(cntrNo, cntrSn);
    }

    @ApiOperation(value = "접수제한 관리-블랙리스트 페이징 조회", notes = "조회조건에 맞는 블랙리스트 목록을 페이징 조회한다")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "adrCl", value = "주소/우편번호", paramType = "query"),
        @ApiImplicitParam(name = "adr", value = "주소/우편번호", paramType = "query"),
        @ApiImplicitParam(name = "cralLocaraTno", value = "전화번호1", paramType = "query"),
        @ApiImplicitParam(name = "mexnoEncr", value = "전화번호2", paramType = "query"),
        @ApiImplicitParam(name = "cralIdvTno", value = "전화번호3", paramType = "query"),
        @ApiImplicitParam(name = "prtnrInfo", value = "파트너정보", paramType = "query"),
    })
    @GetMapping("/blacklists/paging")
    public PagingResult<SearchBlacklistRes> getBlacklistPages(
        @Valid
        SearchBlacklistReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getBlacklistPages(dto, pageInfo);
    }

    @ApiOperation(value = "접수제한 관리-블랙리스트 엑셀 다운로드", notes = "조회조건에 맞는 블랙리스트 목록을 엑셀 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "주소/우편번호", paramType = "query"),
        @ApiImplicitParam(name = "tno", value = "전화번호", paramType = "query"),
        @ApiImplicitParam(name = "prtnrInfo", value = "판매자정보", paramType = "query"),
    })
    @GetMapping("/blacklists/excel-download")
    public List<SearchBlacklistRes> getBlacklistsForExcelDownload(
        @Valid
        SearchBlacklistReq dto
    ) {
        return service.getBlacklistsForExcelDownload(dto);
    }

    @ApiOperation(value = "접수제한관리-블랙리스트 저장", notes = "수정한 값으로 판매제한대상내역 테이블에  insert 한다.\n 판매제한ID 값이 있는경우 수정으로 해당 row를 update한다.")
    @PostMapping("/blacklists")
    public SaveResponse saveBlacklists(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveBlacklistReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveBlacklists(dtos)).build();
    }

    @ApiOperation(value = "접수제한관리-블랙리스트 삭제", notes = "선택한 row의 판매제한ID 를 key로 판매제한대상 테이블에서 삭제한 후 결과값을 반환한다.")
    @DeleteMapping("/blacklists")
    public SaveResponse removeBlacklists(
        @RequestBody
        @Valid
        @NotEmpty
        List<String> sellLmIds
    ) {
        return SaveResponse.builder().processCount(service.removeBlacklists(sellLmIds)).build();
    }
}
