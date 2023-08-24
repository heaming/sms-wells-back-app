package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbCenterCustomerAgrgByProductDto.SearchReq;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbCenterCustomerAgrgByProductDto.SearchRes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbCenterCustomerAgrgByProductService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/center-customer-agrg-by-product")
@Api(tags = "[WSNB] 제품별 센터 관리고객 집계 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsnbCenterCustomerAgrgByProductController {

    private final WsnbCenterCustomerAgrgByProductService service;

    @ApiOperation(value = "제품별 센터 관리고객 집계 조회", notes = "제품별 센터 관리고객 집계를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getCenterCustomerAgrgByProduct(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getCenterCustomerAgrgByProduct(dto, pageInfo);
    }

    @ApiOperation(value = "제품별 센터 관리고객 집계 조회 (엑셀 다운로드)", notes = "제품별 센터 관리고객 집계를 엑셀 다운로드한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getCenterCustomerAgrgByProduct(
        @Valid
        SearchReq dto
    ) {
        return service.getCenterCustomerAgrgByProduct(dto);
    }

}
