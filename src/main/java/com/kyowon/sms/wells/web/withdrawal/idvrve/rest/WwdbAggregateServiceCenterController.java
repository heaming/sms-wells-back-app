package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateEngineerOgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAggregateServiceCenterDto.SearchAggregateServiceCenterTotalRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbAggregateServiceCenterService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 입금집계현황_서비스센터 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/aggregate-service-centers")
public class WwdbAggregateServiceCenterController {

    private final WwdbAggregateServiceCenterService service;

    @ApiOperation(value = "입금집계현황-서비스센터 목록", notes = "입금집계현황-서비스센터 목록")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "strtSvDt", value = "서비스일자시작", required = true),
        @ApiImplicitParam(name = "endSvDt", value = "서비스일자종료", required = true),
        @ApiImplicitParam(name = "svCnr", value = "서비스센터", required = false),
        @ApiImplicitParam(name = "prtnrKnm", value = "엔지니어 이름", required = false)
    })
    @GetMapping("/paging")
    public PagingResult<SearchAggregateServiceCenterRes> getAggregateServiceCenters(
        SearchAggregateServiceCenterReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAggregateServiceCenters(req, pageInfo);
    }

    @ApiOperation(value = "입금집계현황-서비스센터 목록 - 합계", notes = "입금집계현황-서비스센터 목록 - 합계")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "strtSvDt", value = "서비스일자시작", required = true),
        @ApiImplicitParam(name = "endSvDt", value = "서비스일자종료", required = true),
        @ApiImplicitParam(name = "svCnr", value = "서비스센터", required = false),
        @ApiImplicitParam(name = "prtnrKnm", value = "엔지니어 이름", required = false)
    })
    @GetMapping("/total")
    public SearchAggregateServiceCenterTotalRes getAggregateServiceCentersTotal(
        SearchAggregateServiceCenterReq req
    ) {
        return service.getAggregateServiceCentersTotal(req);
    }

    @ApiOperation(value = "입금집계현황_서비스센터 엑셀 다운로드", notes = "입금집계현황_서비스센터 엑셀 다운로드")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "strtSvDt", value = "서비스일자시작", required = true),
        @ApiImplicitParam(name = "endSvDt", value = "서비스일자종료", required = true),
        @ApiImplicitParam(name = "svCnr", value = "서비스센터", required = false),
        @ApiImplicitParam(name = "prtnrKnm", value = "엔지니어 이름", required = false)
    })
    @GetMapping("/excel-download")
    public List<SearchAggregateServiceCenterRes> getAggregateServiceCentersExcels(
        SearchAggregateServiceCenterReq req
    ) {
        return service.getAggregateServiceCentersExcels(req);
    }

    @ApiOperation(value = "엔지니어 조직 센터 조회", notes = "엔지니어 조직 센터 조회")
    @GetMapping("/service-center")
    public List<SearchAggregateEngineerOgRes> getServiceCenters(
        SearchAggregateServiceCenterReq req
    ) {
        return service.getServiceCenters(req);
    }
}
