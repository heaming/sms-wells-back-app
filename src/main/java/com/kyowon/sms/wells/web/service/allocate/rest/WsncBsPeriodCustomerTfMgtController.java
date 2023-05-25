package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.ManagersAndEngineersRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.BranchsAndServiceCentersRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncBsPeriodCustomerTfMgtService;
import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 정기BS 고객이관 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/before-service-period-customer")
public class WsncBsPeriodCustomerTfMgtController {

    private final WsncBsPeriodCustomerTfMgtService service;

    @ApiOperation(value = "정기BS 고객 컨택 이력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "assignYm", value = "배정년월", paramType = "query", example = "202201"),
        @ApiImplicitParam(name = "organizationId", value = "소속", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerName", value = "성명", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerNo", value = "사번", paramType = "query", example = "1712337"),
        @ApiImplicitParam(name = "transferStatusCode", value = "이관상태", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "emdName", value = "읍면동명", paramType = "query", example = "중동"),
        @ApiImplicitParam(name = "addressZip", value = "우편번호", paramType = "query", example = "28601"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBsPeriodCustomerPages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getBsPeriodCustomerPages(dto, pageInfo);
    }

    @ApiOperation(value = "정기BS 소속 조회")
    @GetMapping("/organizations")
    public List<BranchsAndServiceCentersRes> getBranchsAndServiceCenters() {
        return service.getBranchsAndServiceCenters();
    }

    @ApiOperation(value = "정기BS 파트너 조회")
    @GetMapping("/organizations/{ogId}/partners")
    public List<ManagersAndEngineersRes> getManagersAndEngineers(
        @PathVariable
        String ogId
    ) {
        return service.getManagersAndEngineers(ogId);
    }
}
