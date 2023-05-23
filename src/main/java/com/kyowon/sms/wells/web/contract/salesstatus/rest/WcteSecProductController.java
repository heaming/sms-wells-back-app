package com.kyowon.sms.wells.web.contract.salesstatus.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;
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
    public PagingResult<SearchReservationRes> getReservationPages(
        @Valid
        WcteSecProductDto.SearchReservationReq dto,
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
    public List<SearchReservationRes> getReservationsForExcelDownload(
        @Valid
        WcteSecProductDto.SearchReservationReq dto
    ) {
        return service.getReservationsForExcelDownload(dto);
    }

    @ApiOperation(value = "확정일 페이징 조회", notes = "generated by LT")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtdt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "enddt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sppBzsOrdId", value = "삼성주문번호", paramType = "query"),
        @ApiImplicitParam(name = "pdctIdno", value = "시리얼넘버", paramType = "query"),
    })
    @GetMapping("/confirm-days/paging")
    public PagingResult<SearchConfirmRes> getConfirmPages(
        @Valid
        SearchConfirmReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getConfirmPages(dto, pageInfo);
    }

    @ApiOperation(value = "확정일 엑셀 다운로드", notes = "generated by LT")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtdt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "enddt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sppBzsOrdId", value = "삼성주문번호", paramType = "query"),
        @ApiImplicitParam(name = "pdctIdno", value = "시리얼넘버", paramType = "query"),
    })
    @GetMapping("/confirm-days/excel-download")
    public List<SearchConfirmRes> getConfirms(
        @Valid
        SearchConfirmReq dto
    ) {
        return service.getConfirms(dto);
    }

    @ApiOperation(value = "확정일 다건 저장", notes = "확정일 엑셀 업로드")
    @PostMapping("/confirm-days/excel-upload")
    public SaveResponse createConfirms(
        @RequestBody
        @Valid
        List<CreateConfirmReq> list
    ) {
        return SaveResponse.builder().processCount(service.createConfirms(list)).build();
    }

    @ApiOperation(value = "NotInstalled 페이징 조회", notes = "generated by LT")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strtdt", value = "시작일", paramType = "query", required = true),
        @ApiImplicitParam(name = "enddt", value = "종료일", paramType = "query", required = true),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sppBzsOrdId", value = "삼성주문번호", paramType = "query"),
        @ApiImplicitParam(name = "pdctIdno", value = "시리얼넘버", paramType = "query"),
    })
    @GetMapping("/not-installs/paging")
    public PagingResult<SearchNotInstalledRes> getNotInstalledPages(
        @Valid
        SearchNotInstalledReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getNotInstalledPages(dto, pageInfo);
    }
}
