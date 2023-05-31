package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncManagementCstRglvlService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api(tags = "[WSNC] 관리고객 급지관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/manage-customer-rglvl")
public class WsncManagementCstRglvlController {
    private final WsncManagementCstRglvlService service;

    @ApiOperation(value = "관리고객 급지 및 배정담당자 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "manageYm", value = "관리년월", paramType = "query", example = "202201"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "고객구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "selectDiv", value = "조회구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "exceptWellsManagerYn", value = "웰스매니저 미관리 제외", paramType = "query", example = "Y"),
        @ApiImplicitParam(name = "addressZipFrom", value = "우편번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "addressZipTo", value = "우편번호", paramType = "query", example = ""),
        @ApiImplicitParam(name = "executiveGroup", value = "총괄단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "localGroup", value = "지역단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "branchOffice", value = "지점", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerNo", value = "매니저", paramType = "query", example = "ALL"),
    })
    @GetMapping("/paging")
    public PagingResult<WsncManagementCstRglvlDto.SearchRes> getBsPeriodCustomerPages(
        WsncManagementCstRglvlDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getManagementCustomerRglvlPages(dto, pageInfo);
    }

    @ApiOperation(value = "관리고객 급지 및 배정담당자 정보 갱신", notes = "관리고객 급지 및 배정담당자 정보를 변경")
    @PutMapping("/partner-info")
    public SaveResponse savePartnerInfoAndMngerRglvlDvCd(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsncManagementCstRglvlDto.SavePartnerReq> dtos
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
    public WsncManagementCstRglvlDto.OrganizationRes getOrganizationInfo(
        @PathVariable
        String ogId
    ) {
        return service.getOrganizationInfo(ogId);
    }
}
