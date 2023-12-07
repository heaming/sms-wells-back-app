package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappyCallAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappyCallAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbMobileHappyCallAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/mobile-happy-call-agrg")
@Api(tags = "[WSNB] 모바일해피콜 집계")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbMobileHappyCallAgrgController {
    private final WsnbMobileHappyCallAgrgService service;

    @ApiOperation(value = "모바일해피콜 집계 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svBizHclsfCd", value = "서비스유형", paramType = "query", example = "2"),
        @ApiImplicitParam(name = "searchDateFrom", value = "조회년월", paramType = "query", example = "20230912"),
        @ApiImplicitParam(name = "searchDateTo", value = "조회년월", paramType = "query", example = "20230912"),
        @ApiImplicitParam(name = "ogId", value = "조직id", paramType = "query", example = "OGO202000010295"),
        @ApiImplicitParam(name = "engId", value = "엔지니어id", paramType = "query", example = "36680"),
        @ApiImplicitParam(name = "rgsnYn", value = "퇴사자여부", paramType = "query", example = "Y")
    })
    @GetMapping("/get-mobile-happy-call-agrg")
    public List<SearchRes> getMobileHappyCallAgrgs(SearchReq dto) {
        return service.getMobileHappyCallAgrgs(dto);
    }
}
