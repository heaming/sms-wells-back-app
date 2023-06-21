package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchSumRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwwdbDepositAggregateDtlServiceCenterDto.SearchCodeRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwwdbDepositAggregateDtlServiceCenterService;
import com.sds.sflex.system.config.datasource.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbVirtualAccountDto;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Api(tags = "[입금관리 - 개별수납] 입금집계상세현황 서비스 센터")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/deposit-aggregate-service")
public class WwwdbDepositAggregateDtlServiceCenterController {

    private final WwwdbDepositAggregateDtlServiceCenterService service;

    @ApiOperation(value = "입금집계상세현황 서비스 조회", notes = "입금집계상세현황 서비스 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ogId", value = "센터코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "startDt", value = "청구시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "청구종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "useYn", value = "대사처리", paramType = "query", required = false),
        @ApiImplicitParam(name = "stlmDvCd", value = "결제유형", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getDepositAggregateServiceCenterPages(
        SearchReq req, PageInfo pageInfo
    ) {
        return service.getDepositAggregateServiceCenterPages(req, pageInfo);
    }

    @ApiOperation(value = "입금집계상세현황 서비스 조회", notes = "입금집계상세현황 서비스 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ogId", value = "센터코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "startDt", value = "청구시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "청구종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "useYn", value = "대사처리", paramType = "query", required = false),
        @ApiImplicitParam(name = "stlmDvCd", value = "결제유형", paramType = "query", required = false),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getDepositAggregateServiceCenterExcels(
        SearchReq req
    ) {
        return service.getDepositAggregateServiceCenterExcels(req);
    }

    @ApiOperation(value = "입금집계상세현황 서비스 조회", notes = "입금집계상세현황 서비스 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ogId", value = "센터코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "startDt", value = "청구시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "청구종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "useYn", value = "대사처리", paramType = "query", required = false),
        @ApiImplicitParam(name = "stlmDvCd", value = "결제유형", paramType = "query", required = false),
    })
    @GetMapping("/center-code")
    public List<SearchCodeRes> getCenterCodes(
        SearchReq req
    ) {
        return service.getCenterCodes(req);
    }

    @ApiOperation(value = "입금집계상세현황 서비스 합계 조회", notes = "입금집계상세현황 서비스 합계 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ogId", value = "센터코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "startDt", value = "청구시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "청구종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "useYn", value = "대사처리", paramType = "query", required = false),
        @ApiImplicitParam(name = "stlmDvCd", value = "결제유형", paramType = "query", required = false),
    })
    @GetMapping("/total-sum")
    public SearchSumRes getDepositAggregateServiceCentersTotal(
        SearchReq req
    ) {
        return service.getDepositAggregateServiceCentersTotal(req);
    }

}
