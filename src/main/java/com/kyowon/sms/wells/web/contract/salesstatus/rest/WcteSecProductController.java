package com.kyowon.sms.wells.web.contract.salesstatus.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.*;

import com.kyowon.sms.wells.web.contract.salesstatus.service.WcteSecProductService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTE] 삼성전자 주문 정보 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/sales-status/sec-product-management")
public class WcteSecProductController {

    private final WcteSecProductService service;

    @ApiOperation(value = "삼성전자 주문 정보 관리 페이징 조회", notes = "삼성전자 주문 정보 관리 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtdt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "enddt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
    })
    @GetMapping("/reservation-days/paging")
    public PagingResult<SearchRes> getReservationPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getReservationPages(dto, pageInfo);
    }

    @ApiOperation(value = "삼성전자 주문 정보 관리 엑셀 다운로드", notes = "삼성전자 주문 정보 관리 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtdt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "enddt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
    })
    @GetMapping("/reservation-days/excel-download")
    public List<SearchRes> getReservationsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getReservationsForExcelDownload(dto);
    }
}
