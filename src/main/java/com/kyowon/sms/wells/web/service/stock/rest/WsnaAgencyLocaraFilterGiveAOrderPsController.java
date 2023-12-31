package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchAgrgRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAgencyLocaraFilterGiveAOrderPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaAgencyLocaraFilterGiveAOrderPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNA] 대리점지역별 필터발주현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/agency-locara-filter-give-a-order-ps")
public class WsnaAgencyLocaraFilterGiveAOrderPsController {

    private final WsnaAgencyLocaraFilterGiveAOrderPsService service;

    @ApiOperation(value = "대리점지역별 필터발주현황(집계)", notes = "조회조건에 일치하는 대리점지역별 필터발주현황(집계_ 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "기준년월 시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "기준년월 종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "bfsvcBzsDvCd", value = "BS업체구분코드", paramType = "query", required = true),
    })
    @GetMapping("/agrg")
    public List<SearchAgrgRes> getAgencyLocaraFilterGiveAOrderPsAgrgs(
        @Valid
        SearchReq dto
    ) {
        return service.getAgencyLocaraFilterGiveAOrderPsAgrgs(dto);
    }

    @ApiOperation(value = "대리점지역별 필터발주현황", notes = "조회조건에 일치하는 대리점지역별 필터발주현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "기준년월 시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "기준년월 종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "bfsvcBzsDvCd", value = "BS업체구분코드", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getAgencyLocaraFilterGiveAOrderPss(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAgencyLocaraFilterGiveAOrderPsPages(dto, pageInfo);
    }

    @ApiOperation(value = "대리점지역별 필터발주현황 엑셀 다운로드", notes = "조회조건에 일치하는 대리점지역별 필터발주현황 엑셀 다운로드 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "기준년월 시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "기준년월 종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "bfsvcBzsDvCd", value = "BS업체구분코드", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getAgencyLocaraFilterGiveAOrderPssForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getAgencyLocaraFilterGiveAOrderPssForExcelDownload(dto);
    }

}
