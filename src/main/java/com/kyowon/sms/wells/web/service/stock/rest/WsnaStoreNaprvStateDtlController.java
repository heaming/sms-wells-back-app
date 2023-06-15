package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto;
import com.kyowon.sms.wells.web.service.stock.service.WsnaStoreNaprvStateDtlService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = SnServiceConst.REST_URL_V1 + "/store-naprv-state-dtl")
@Api(tags = "[WSNA] 입고 미승인상세현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnaStoreNaprvStateDtlController {

    private final WsnaStoreNaprvStateDtlService service;

    @ApiOperation(value = "입고 미승인상세현황", notes = "조회조건에 일치하는 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목번호", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", example = ""),
        @ApiImplicitParam(name = "startDate", value = "적용시작일자", paramType = "query", example = "20230821"),
        @ApiImplicitParam(name = "endDate", value = "적용종료일자", paramType = "query", example = "20230821"),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분", paramType = "query", example = "1"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getStoreNaprvStateDtl(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getStoreNaprvStateDtl(dto, pageInfo);
    }

    @ApiOperation(value = "입고 미승인상세현황", notes = "조회조건에 일치하는 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "strWareNo", value = "입고창고번호", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "itmPdCd", value = "품목번호", paramType = "query", example = "", required = true),
        @ApiImplicitParam(name = "strTpCd", value = "입고유형", paramType = "query", example = ""),
        @ApiImplicitParam(name = "startDate", value = "적용시작일자", paramType = "query", example = "20230821"),
        @ApiImplicitParam(name = "endDate", value = "적용종료일자", paramType = "query", example = "20230821"),
        @ApiImplicitParam(name = "ostrWareDvCd", value = "출고창고구분", paramType = "query", example = "1"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getStoreNaprvStateDtlExcelDownload(
        SearchReq dto
    ) {
        return this.service.getStoreNaprvStateDtl(dto);
    }

    @ApiOperation(value = "입고 미승인상세현황", notes = "입고 확인 상세정보를 업데이트한다.")
    @PostMapping("/str-confirm-detail")
    public SaveResponse saveStoreNaprvStateDtl(
        @Valid
        @RequestBody
        List<WsnaStoreNaprvStateDtlDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveStoreNaprvStateDtl(dtos))
            .build();
    }
}
