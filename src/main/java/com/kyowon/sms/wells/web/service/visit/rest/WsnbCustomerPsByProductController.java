package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerPsByProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerPsByProductDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerPsByProductService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/customer-ps-by-product")
@Api(tags = "[WSNB] 상품별 고객현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbCustomerPsByProductController {

    private final WsnbCustomerPsByProductService service;

    @GetMapping("/paging")
    public PagingResult<SearchRes> getProductServices(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getCustomerPsByProduct(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<SearchRes> getProductServicesForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getCustomerPsByProduct(dto);
    }

}
