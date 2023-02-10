package com.kyowon.sms.wells.web.contract.risk.rest;

import static com.kyowon.sms.wells.web.contract.risk.dto.WcteSalesLimitDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.contract.risk.service.WcteSalesLimitService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTE] wells 사업자 가입제한 대상 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/sales-limits/business-partners")
public class WcteSalesLimitController {

    private final WcteSalesLimitService service;

    @ApiOperation(value = "wells 사업자 가입제한 대상 관리 페이징 조회", notes = "wells 사업자 가입제한 대상 관리를 페이징 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellLmBzrno", value = "사업자번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "dlpnrNm", value = "상호명", paramType = "query"),
        @ApiImplicitParam(name = "sellLmOcStm", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellLmOcDtm", value = "종료일자", paramType = "query", required = true),

    })
    @GetMapping("/paging")
    public PagingResult<SearchEntrpJLmOjRes> getEntrepreneurJoinLmOjssPages(
        SearchEntrpJLmOjReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEntrepreneurJoinLmOjssPages(dto, pageInfo);
    }

    @ApiOperation(value = "wells 사업자 가입제한 대상 관리 엑셀 다운로드", notes = "wells 사업자 가입제한 대상 관리를 전체 조회 후 엑셀을 다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellLmBzrno", value = "사업자번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "dlpnrNm", value = "상호명", paramType = "query"),
        @ApiImplicitParam(name = "sellLmOcStm", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellLmOcDtm", value = "종료일자", paramType = "query", required = true),

    })
    @GetMapping("/excel-download")
    public List<SearchEntrpJLmOjRes> getSalesLimitsForExcelDownload(
        SearchEntrpJLmOjReq dto
    ) {
        return service.getEntrepreneurJoinLmOjssExcelDownload(dto);
    }

    @ApiOperation(value = "wells 사업자 가입제한 대상 저장", notes = "wells 사업자 가입제한 대상 관리를 저장한다.")
    @PostMapping
    public SaveResponse saveEntrepreneurJoinLmOjss(
        @RequestBody
        List<SaveEntrpJLmOjReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.saveEntrepreneurJoinLmOjss(dtos))
            .build();
    }

    @ApiOperation(value = "wells 사업자 가입제한 대상 삭제", notes = "wells 사업자 가입제한 대상 관리를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeEntrepreneurJoinLmOjss(
        @RequestParam
        String[] sellLmIds
    ) {
        return SaveResponse.builder()
            .processCount(service.removeEntrepreneurJoinLmOjss(sellLmIds))
            .build();
    }

}
