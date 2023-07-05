package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncCompanyInstallationDto.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncCompanyInstallationService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 회사설치 현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/company-ist-state")
@Slf4j
public class WsncCompanyInstallationController {

    private final WsncCompanyInstallationService service;

    @ApiOperation(value = "회사설치 현황 - 전체탭 조회", notes = "전체 현황 조회")
    @GetMapping("/all/paging")
    public PagingResult<SearchAllRes> getCompanyInstallationAll(
        SearchMainReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyInstallationAll(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 현황 - 전체탭 엑셀 다운로드", notes = "전체 엑셀 다운로드")
    @GetMapping("/all/excel-download")
    public List<SearchAllRes> getCompanyInstallationAll(
        SearchMainReq dto
    ) {
        return service.getCompanyInstallationAll(dto);
    }

    @ApiOperation(value = "회사설치 현황 - 현황탭 조회", notes = "현황 조회")
    @GetMapping("/state/paging")
    public PagingResult<SearchPsRes> getCompanyInstallationPs(
        SearchPsReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyInstallationPs(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 현황 - 현황탭 엑셀 다운로드", notes = "현황 엑셀 다운로드")
    @GetMapping("/state/excel-download")
    public List<SearchPsRes> getCompanyInstallationPs(
        SearchPsReq dto
    ) {
        return service.getCompanyInstallationPs(dto);
    }

    @ApiOperation(value = "회사설치 현황 - 필터탭 조회", notes = "필터 조회")
    @GetMapping("/filter/paging")
    public PagingResult<SearchFltrSubMatRes> getCompanyInstallationFltr(
        SearchMainReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyInstallationFltrOrSubMat(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 현황 - 필터탭 엑셀 다운로드", notes = "필터 엑셀 다운로드")
    @GetMapping("/filter/excel-download")
    public List<SearchFltrSubMatRes> getCompanyInstallationFltr(
        SearchMainReq dto
    ) {
        return service.getCompanyInstallationFltrOrSubMat(dto);
    }

    @ApiOperation(value = "회사설치 현황 - 부자재탭 조회", notes = "부자재 조회")
    @GetMapping("/sub-material/paging")
    public PagingResult<SearchFltrSubMatRes> getCompanyInstallationSubMat(
        SearchMainReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCompanyInstallationFltrOrSubMat(dto, pageInfo);
    }

    @ApiOperation(value = "회사설치 현황 - 부자재탭 엑셀 다운로드", notes = "부자재 엑셀 다운로드")
    @GetMapping("/sub-material/excel-download")
    public List<SearchFltrSubMatRes> getCompanyInstallationSubMat(
        SearchMainReq dto
    ) {
        return service.getCompanyInstallationFltrOrSubMat(dto);
    }
}
