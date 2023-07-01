package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SavePartnerReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.OrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncManagementCstRglvlService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WSNC] 관리고객 급지관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/manage-customer-rglvl")
@Validated
public class WsncManagementCstRglvlController {
    private final WsncManagementCstRglvlService service;

    @ApiOperation(value = "관리고객 급지 및 배정담당자 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "manageYm", value = "관리년월", paramType = "query", example = "202201"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "고객구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "exceptWellsManagerYn", value = "웰스매니저 미관리 제외", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "addressZipFrom", value = "우편번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "addressZipTo", value = "우편번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "localGroupCd", value = "지역단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "branchOfficeCd", value = "지점", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerNo", value = "매니저", paramType = "query", example = "ALL"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getManagementCustomerRglvls(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getManagementCustomerRglvls(dto, pageInfo);
    }

    @ApiOperation(value = "관리고객 급지 및 배정담당자 조회 (엑셀 다운로드)", notes = "관리고객 급지 및 배정담당자를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getManagementCustomerRglvlsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getManagementCustomerRglvlsForExcelDownload(dto);
    }

    @ApiOperation(value = "관리고객 급지 및 배정담당자 정보 갱신", notes = "관리고객 급지 및 배정담당자 정보를 변경")
    @PutMapping("/partner-info")
    public SaveResponse savePartnerInfoAndMngerRglvlDvCd(
        @Valid
        @RequestBody
        List<SavePartnerReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(service.savePartnerInfoAndMngerRglvlDvCd(dtos))
            .build();
    }

    @ApiOperation(value = "사용자 조직 정보 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ogId", value = "조직 아이디", paramType = "path", example = ""),
    })
    @GetMapping("/organization-info/{ogId}")
    public OrganizationRes getOrganizationInfo(
        @PathVariable
        String ogId
    ) {
        return service.getOrganizationInfo(ogId);
    }
}
