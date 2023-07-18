package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchCustomerVisitIzRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchManagementCstInqrRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdInqrDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdInqrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 개인별 방문주기 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/individual-visit-prd-inqr")
public class WsnbIndividualVisitPrdInqrController {

    private final WsnbIndividualVisitPrdInqrService service;

    @ApiOperation(value = "개인별 방문주기 조회 - 고객 방문 내역 조회 ", notes = "조회조건에 일치하는 고객 방문 내역 조회 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true)
    })
    @GetMapping("/customer-visit-izs")
    public List<SearchCustomerVisitIzRes> getCustomerVisitIzs(
        @Valid
        SearchReq dto
    ) {
        return service.getCustomerVisitIzs(dto);
    }

    @ApiOperation(value = "개인별 방문주기 조회 - 관리 고객 조회", notes = "조회조건에 일치하는 관리 고객 조회 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true)
    })
    @GetMapping("/management-csts")
    public List<SearchManagementCstInqrRes> getManagementCstInqrs(
        @Valid
        SearchReq dto
    ) {
        return service.getManagementCstInqrs(dto);
    }
}
