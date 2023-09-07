package com.kyowon.sms.wells.web.service.allocate.rest;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsAccountByProductDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsAccountByProductDto.SearchRes;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.service.WsncBsAccountByProductService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/bs-account-by-product")
@Api(tags = "[WSNC] 배정관리 집계 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsncBsAccountByProductController {

    private final WsncBsAccountByProductService service;

    @ApiOperation(value = "상품별 bs계정 현황 조회")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getTotalCustomers(
        SearchReq dto, PageInfo pageInfo
    ) {
        return service.getBsAccountByProduct(dto, pageInfo);
    }

    @ApiOperation(value = "상품별 bs계정 현황 조회 Excel Download")
    @GetMapping("/excel-download")
    public List<SearchRes> getTotalCustomersExcelDownload(
        SearchReq dto
    ) {
        return service.getBsAccountByProduct(dto);
    }

}
