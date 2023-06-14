package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositAggregateMaterialDto.SearchDepositAggregateMaterialTotalRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbDepositAggregateMaterialService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDB] 입금집계자료 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/deposit-aggregate-materials")
public class WwdbDepositAggregateMaterialController {

    private final WwdbDepositAggregateMaterialService service;

    @ApiOperation(value = "입금집계자료 목록", notes = "입금집계자료 목록")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dvCd", value = "업무구분", required = false),
        @ApiImplicitParam(name = "dpTpCd", value = "입금유형", required = false),
        @ApiImplicitParam(name = "searchDt", value = "기준년월", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchDepositAggregateMaterialRes> getDepositAggregateMaterialPages(
        @Valid
        SearchDepositAggregateMaterialReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getDepositAggregateMaterialPages(req, pageInfo);
    }

    @ApiOperation(value = "입금집계자료 Total ", notes = "입금집계자료 Total 값")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dvCd", value = "업무구분", required = false),
        @ApiImplicitParam(name = "dpTpCd", value = "입금유형", required = false),
        @ApiImplicitParam(name = "searchDt", value = "기준년월", required = false),
    })
    @GetMapping("/total")
    public SearchDepositAggregateMaterialTotalRes getDepositAggregateMaterialTotal(
        SearchDepositAggregateMaterialReq req
    ) {
        return service.getDepositAggregateMaterialTotal(req);
    }

    @ApiOperation(value = "입금집계자료 엑셀 다운로드", notes = "입금집계자료 엑셀 다운로드")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dvCd", value = "업무구분", required = false),
        @ApiImplicitParam(name = "dpTpCd", value = "입금유형", required = false),
        @ApiImplicitParam(name = "searchDt", value = "기준년월", required = false),
    })
    @GetMapping("/excel-download")
    public List<SearchDepositAggregateMaterialRes> getDepositAggregateMaterialExcels(
        SearchDepositAggregateMaterialReq req
    ) {
        return service.getDepositAggregateMaterialExcels(req);
    }
}
