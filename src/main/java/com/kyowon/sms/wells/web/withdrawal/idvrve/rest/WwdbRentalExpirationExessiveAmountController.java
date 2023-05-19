package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRentalExpirationExcessiveAmountDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRentalExpirationExcessiveAmountService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WDB] 렌탈만료초과금현황")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/rental-exn-examt")
public class WwdbRentalExpirationExessiveAmountController {

    private final WwdbRentalExpirationExcessiveAmountService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dpDt", value = "입금일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "copnDvCd", value = "계약구분코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "계약상품종료일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrDtlStatCd", value = "종료구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "렌탈만료초과금현황 조회", notes = "렌탈 상품이 만료되어 초과금이 발생되는 정보를 검색한다.")
    @GetMapping("/paging")
    public PagingResult<WwdbRentalExpirationExcessiveAmountDto.SearchRes> getRentalExpirationExcessiveAmountPage(
        @ApiParam
        @Valid
        WwdbRentalExpirationExcessiveAmountDto.SearchReq req, PageInfo pageInfo
    ) {
        return service.getRentalExpirationExcessiveAmountPage(req, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dpDt", value = "입금일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "copnDvCd", value = "계약구분코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrPdEnddt", value = "계약상품종료일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrDtlStatCd", value = "종료구분코드", paramType = "query", required = false),
    })
    @ApiOperation(value = "렌탈만료초과금현황 엑셀다운로드", notes = "렌탈 상품이 만료되어 초과금이 발생되는 정보를 검색하여 엑셀다운로드한다.")
    @GetMapping("/excel-download")
    public List<WwdbRentalExpirationExcessiveAmountDto.SearchRes> getRentalExpirationExcessiveForExcelDownload(
        @ApiParam
        @Valid
        WwdbRentalExpirationExcessiveAmountDto.SearchReq req
    ) {
        return service.getRentalExpirationExcessiveForExcelDownload(req);
    }

}
