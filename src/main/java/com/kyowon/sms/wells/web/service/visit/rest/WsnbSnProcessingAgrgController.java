package com.kyowon.sms.wells.web.service.visit.rest;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchReq;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchRgrpRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingAgrgDto.SearchSnRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbSnProcessingAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/sn-processing-agrg")
@Api(tags = "[WSNB]  S/N 처리 집계 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbSnProcessingAgrgController {

    private final WsnbSnProcessingAgrgService service;

    @GetMapping("/by-sn/paging")
    public PagingResult<SearchSnRes> getSnProcessingAgrgBySn(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getSnProcessingAgrgBySn(dto, pageInfo);
    }

    @GetMapping("/by-sn/excel-download")
    public List<SearchSnRes> getSnProcessingPsCustomersExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getSnProcessingAgrgBySn(dto);
    }

    @GetMapping("/by-rgrp/paging")
    public PagingResult<SearchRgrpRes> selectSnProcessingAgrgByRgrp(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getSnProcessingAgrgByRgrp(dto, pageInfo);
    }

    @GetMapping("/by-rgrp/excel-download")
    public List<SearchRgrpRes> getSnProcessingPsPuPartProducts(SearchReq dto) {
        return this.service.getSnProcessingAgrgByRgrp(dto);
    }
}
