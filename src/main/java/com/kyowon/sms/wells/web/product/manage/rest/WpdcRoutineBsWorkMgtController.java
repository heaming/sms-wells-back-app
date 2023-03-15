package com.kyowon.sms.wells.web.product.manage.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.service.WpdcRoutineBsWorkMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WPDC] 상품(Wells) - 상품운영관리 - 방문서비스(필터교체관리)")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/bs-works")
@RequiredArgsConstructor
@Validated
public class WpdcRoutineBsWorkMgtController {

    private final WpdcRoutineBsWorkMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "path", example = "EP01200001"),
    })
    @ApiOperation(value = "정기 B/S 투입 방문 작업 조회", notes = "상품코드 기준으로 조회한다.")
    @GetMapping("/{pdCd}")
    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkRes> getRoutineBsWorks(
        @PathVariable
        @Valid
        @NotBlank
        String pdCd,
        WpdcRoutineBsWorkMgtDto.SearchReq dto
    ) {
        if (StringUtils.isEmpty(dto.pdCd())) {
            return service.getRoutineBsWorks(WpdcRoutineBsWorkMgtDto.SearchReq.builder().pdCd(pdCd).build());
        } else {
            return service.getRoutineBsWorks(dto);
        }
    }
}
