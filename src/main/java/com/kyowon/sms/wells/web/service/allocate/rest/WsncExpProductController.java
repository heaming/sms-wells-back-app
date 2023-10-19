package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncExpProductDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncExpProductService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 예정부품")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/exp-product")
public class WsncExpProductController {

    private final WsncExpProductService service;

    @ApiOperation(value = "예정부품 조회", notes = "예정부품을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<SearchRes> getExpProducts(SearchReq dto) {
        return service.getExpProducts(dto);
    }

}
