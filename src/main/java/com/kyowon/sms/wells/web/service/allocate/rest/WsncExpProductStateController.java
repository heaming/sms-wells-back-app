package com.kyowon.sms.wells.web.service.allocate.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncExpProductStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 예정부품현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/exp-product-state")
public class WsncExpProductStateController {

    private final WsncExpProductStateService service;

    @ApiOperation(value = "예정부품현황 조회", notes = "예정부품현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "배정기준일", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "ogId", value = "서비스센터조직ID", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getExpProductStateStates(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getExpProductStateStates(dto, pageInfo);
    }

}
