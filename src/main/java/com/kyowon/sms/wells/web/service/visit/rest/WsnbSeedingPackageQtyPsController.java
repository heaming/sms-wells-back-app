package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSeedingPackageQtyPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSeedingPackageQtyPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbSeedingPackageQtyPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/seeding-package-qty")
@Api(tags = "[WSNB] 모종패키지 수량 현황 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbSeedingPackageQtyPsController {
    private final WsnbSeedingPackageQtyPsService service;

    @ApiOperation(value = "모종패키지 수량현황 조회", notes = "모종패키지 수량현황 집계 리스트를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getSeedingPackageQtyPs(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getSeedingPackageQtyPs(dto, pageInfo);
    }

    @ApiOperation(value = "모종패키지 수량현황 조회 (엑셀 다운로드)", notes = "모종패키지 수량현황 집계 리스트를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getSeedingPackageQtyPs(
        @Valid
        SearchReq dto
    ) {
        return service.getSeedingPackageQtyPs(dto);
    }
}
