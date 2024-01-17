package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto.SearchCntrs;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto.SearchPuPartPdReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto.SearchRatio;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.service.WsnbSnProcessingPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/sn-processing-ps")
@Api(tags = "[WSNB]  S/N 처리 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbSnProcessingPsController {

    private final WsnbSnProcessingPsService service;

    @GetMapping("/customers/paging")
    public PagingResult<SearchCntrs> getSnProcessingPsCustomersPaging(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getSnProcessingPsCustomers(dto, pageInfo);
    }

    @GetMapping("/customers/excel-download")
    public List<SearchCntrs> getSnProcessingPsCustomersExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getSnProcessingPsCustomers(dto);
    }

    @GetMapping("/ratio")
    public List<SearchRatio> getSnProcessingPsRatio(
        @Valid
        SearchReq dto
    ) {
        return this.service.getSnProcessingPsRatio(dto);
    }

    @GetMapping("/pu-products")
    public List<WsnbSnProcessingPsDto.SearchPuPartPdRes> getSnProcessingPsPuPartProducts(SearchPuPartPdReq dto) {
        return this.service.selectSnProcessingPuPartPds(dto);
    }

    @GetMapping("/cstSignCn")
    public WsnbSnProcessingPsDto.SearchCstSignCn getSnProcessingCstSignCn(String cstSvAsnNo) {
        return this.service.getSnProcessingcstSignCn(cstSvAsnNo);
    }
}
